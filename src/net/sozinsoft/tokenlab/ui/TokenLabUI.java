package net.sozinsoft.tokenlab.ui;

import net.sozinsoft.tokenlab.*;
import net.sozinsoft.tokenlab.dtd.Character;
import org.xml.sax.SAXException;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.prefs.Preferences;

public class TokenLabUI {
    public static final String HEROLABS_XML_DIR = "HEROLABS_XML_DIR";
    JButton importButton;
    JToolBar toolbar;
    JList herolabsCharacterList;
    JButton exportAllButton;
    JButton exportSelectedButton;
    JFileChooser herolabsXMLChooser ;

    JPanel panel;
    private JButton configureSelectedButton;
    Config config;
    Preferences prefs;

    public static void main(String[] args) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    createAndShowGUI();
                }
            });
    }


    private static void createAndShowGUI()  {

        JFrame frame = null;
        try {
            TokenLabUI ui = new TokenLabUI();
            frame = new ExitFrame( ui.config );
            Dimension preferredSize = new Dimension( 320, 500 );
            frame.setPreferredSize( preferredSize );
            ui.setDefaults();
            frame.setContentPane(ui.panel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.pack();
            frame.setVisible(true);
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(frame,
             "Something bad happened! " + e.getMessage(),
             "Fatal error",
             JOptionPane.ERROR_MESSAGE);
        }
    }



    public void setDefaults() throws IOException {

        herolabsXMLChooser = new JFileChooser( prefs.get( HEROLABS_XML_DIR, "" ) );


        importButton.setEnabled(true);
        exportAllButton.setEnabled(false);
        exportSelectedButton.setEnabled(false);
        configureSelectedButton.setEnabled(false);


        setXMLChooserFileFilter(herolabsXMLChooser);

        IconListRenderer listRenderer = new IconListRenderer( config, this );
        herolabsCharacterList.setCellRenderer(listRenderer);

    }



    private void setXMLChooserFileFilter( JFileChooser chooser  ) {
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().endsWith("xml");
            }

            @Override
            public String getDescription() {
                return "Herolabs xml output file";
            }
        });
    }


    public TokenLabUI() throws IOException {
        prefs = Preferences.userNodeForPackage(this.getClass());
        config = new Config( prefs );

        importButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnVal = herolabsXMLChooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {


                    File mostRecentOutputDirectory = herolabsXMLChooser.getSelectedFile();
                    prefs.put(HEROLABS_XML_DIR, mostRecentOutputDirectory.getAbsolutePath());

                    File xmlFile = herolabsXMLChooser.getSelectedFile();

                    if ( ! xmlFile.exists() ) {
                        errorDialog("File not found", "File " + xmlFile + " does not exists, please select accept valid file\",");
                        return;
                    }

                    HerolabsDigester dig = new HerolabsDigester();

                    try {

                        dig.parse(xmlFile);
                        DefaultListModel model = new DefaultListModel();
                        herolabsCharacterList.setModel(model);

                        for (Character c : dig.getCharacters()) {
                            model.addElement(c);
                        }

                        herolabsCharacterList.validate();

                    } catch (JAXBException je) {
                        je.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }

        });

        configureSelectedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //get the selected item
                Object[] selectedValues = herolabsCharacterList.getSelectedValues();
                for (Object object : selectedValues) {

                    Character character = (Character) object;
                    Config.ConfigEntry entry = config.get(character.getName());
                    if (entry == null) {
                        entry = config.addConfigEntry(character.getName());
                    }
                    ConfigureCharacterDialog dialog = new ConfigureCharacterDialog(herolabsCharacterList, entry, prefs);
                    dialog.pack();
                    dialog.setVisible(true);

                }
            }
        });
        herolabsCharacterList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                configureSelectedButton.setEnabled(true);
                exportSelectedButton.setEnabled(true);
            }
        });
        exportSelectedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Character c = (Character) herolabsCharacterList.getModel().getElementAt(herolabsCharacterList.getSelectedIndex());
                Config.ConfigEntry ce = config.get(c.getName());
                if (ce != null && ce.isOk()) {
                    exportCharacter();
                } else {
                    JOptionPane.showMessageDialog(panel, "The character you selected hasn't been configured yet.  Please configure " +
                            "and try again.", "Export Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        exportAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //first select only valid characters
                ListModel lm = herolabsCharacterList.getModel();
                LinkedList<Integer> selectedIndices = new LinkedList<Integer>();
                for ( int i = 0; i < lm.getSize(); i++ )
                {
                    Character c = (Character)lm.getElementAt(i);
                    Config.ConfigEntry ce = config.get(c.getName());
                    if ( ce != null && ce.isOk() ) {
                        selectedIndices.add(i);
                    }
                }
                int[] index = new int[selectedIndices.size()];
                for ( int i = 0; i < selectedIndices.size(); i++ ) {
                    index[i] = selectedIndices.get( i );
                }

                herolabsCharacterList.setSelectedIndices( index );
                exportCharacter();
            }
        });
    }

    private void errorDialog(String title, String error) {
        JOptionPane.showMessageDialog(panel, error, title, JOptionPane.ERROR_MESSAGE);
    }

    private void exportCharacter() {

        Object [] selectedValues = herolabsCharacterList.getSelectedValues();
        HerolabsDigester dig = new HerolabsDigester();
        boolean success = true;
        for ( Object object : selectedValues ) {

            Character character = (Character) object;
            try {
                dig.saveCharacter(config, character);

            } catch (IOException io) {
                success = false;
                errorDialog( io.getMessage(), "Something bad happened:\\n\\n" + io.getStackTrace() );

            } catch (SAXException saxe) {
                success = false;
                errorDialog( saxe.getMessage(), "Something bad happened:\\n\\n" + saxe.getStackTrace() );
            }
            catch (Exception e ) {
                success = false;
                errorDialog( e.getMessage(), "Something bad happened:\\n\\n" + e.getStackTrace() );
            }
        }
        if ( success ) {
            JOptionPane.showMessageDialog(panel, "Successfully exported your selected Maptools token(s).");
        }

    }

    public class IconListRenderer extends DefaultListCellRenderer {

        public static final String NOTOK = "notok";
        public static final String OK = "ok";
        public static final String CHECK_ICON = "res/check.png" ;
        public static final String X_ICON = "res/button_play_red.png";
        private Map<Object, Icon> icons = null;
        private Config config;
        TokenLabUI ui;

        public IconListRenderer( Config config, TokenLabUI ui ) throws IOException {
            this.ui = ui;
            this.config = config;
            icons = new HashMap<Object, Icon>();
            icons.put(OK, IconCreator.createImageIcon(CHECK_ICON, OK));
            icons.put(NOTOK, IconCreator.createImageIcon(X_ICON, NOTOK));
        }

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            // Get the renderer component from parent class

            JLabel label =
                    (JLabel) super.getListCellRendererComponent(list,
                            value, index, isSelected, cellHasFocus);

            // Get icon to use for the list item value
            Character character = (Character)value;
            Config.ConfigEntry ce = config.get( character.getName());
            Icon icon = null;

            if ( ce == null ) {
                icon = icons.get(NOTOK);
                label.setToolTipText( "Press to enter configuration information for this character");
            } else {
                if ( ce.isOk() ) {
                    icon = icons.get(OK);
                    ui.exportAllButton.setEnabled(true);
                }
                else {
                    icon = icons.get(NOTOK);
                }
            }
            // Set icon to display for value

            label.setIcon(icon);
            return label;
        }


    }
}
