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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.prefs.Preferences;

public class TokenLabUI {
    public static final String HEROLABS_XML_DIR = "HEROLABS_XML_DIR";
    JButton importButton;
    JToolBar toolbar;
    JList herolabsCharacterList;
    JButton exportAllButton;
    JButton exportSelectedButton;
    JFileChooser herolabsXMLChooser ;
    boolean contextMenuEnabled;

    JPanel panel;
    private JButton configurePortfolioButton;
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
        configurePortfolioButton.setEnabled(true);
        exportAllButton.setEnabled(false);
        exportSelectedButton.setEnabled(false);

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

                    HeroLabPathfinderDigester dig = new HeroLabPathfinderDigester();


                    try {

                        dig.parse(xmlFile);
                        DefaultListModel model = new DefaultListModel();
                        herolabsCharacterList.setModel(model);

                        for (Character c : dig.getCharacters()) {
                            model.addElement(c);
                        }

                        herolabsCharacterList.validate();

                    } catch (JAXBException je) {
                        je.printStackTrace();
                    }
                }
            }

        });

        configurePortfolioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PortfolioConfigurationDialog dialog = new PortfolioConfigurationDialog(config);
                dialog.pack();
                dialog.setVisible(true);

                updateTokens();
            }
        });

        herolabsCharacterList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                contextMenuEnabled = true;
                exportSelectedButton.setEnabled(true);
            }
        });

        herolabsCharacterList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    configureSelectedCharacters();
                }
            }

            public void mouseReleased(MouseEvent mouseEvent) {
                handleRightClick(mouseEvent);
            }

            public void mousePressed(MouseEvent mouseEvent) {
                handleRightClick(mouseEvent);
            }

            private void handleRightClick(MouseEvent mouseEvent) {
                if (mouseEvent.isPopupTrigger() && mouseEvent.getClickCount() == 1) {
                    herolabsCharacterList.setSelectedIndex(herolabsCharacterList.locationToIndex(mouseEvent.getPoint()));
                    showContextMenu(herolabsCharacterList, mouseEvent);
                }
            }
        });

        exportSelectedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportSelectedCharacters();
            }
        });

        exportAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //first select only valid characters
                ListModel lm = herolabsCharacterList.getModel();
                LinkedList<Integer> selectedIndices = new LinkedList<Integer>();
                for (int i = 0; i < lm.getSize(); i++) {
                    Character c = (Character) lm.getElementAt(i);
                    Config.ConfigEntry ce = config.get(c.getName());
                    if (ce != null && ce.isOk()) {
                        selectedIndices.add(i);
                    }
                }
                int[] index = new int[selectedIndices.size()];
                for (int i = 0; i < selectedIndices.size(); i++) {
                    index[i] = selectedIndices.get(i);
                }

                herolabsCharacterList.setSelectedIndices(index);
                exportCharacters();
            }
        });
    }

    private void showContextMenu(JList characterList, MouseEvent mouseEvent) {
        if (contextMenuEnabled) {
            JPopupMenu menu = new JPopupMenu();
            JMenuItem menuItem;

            menuItem = new JMenuItem("Configure character...");
            menuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    configureSelectedCharacters();
                }
            });
            menu.add(menuItem);

            menuItem = new JMenuItem("Export character...");
            menuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    exportSelectedCharacters();
                }
            });
            menu.add(menuItem);

            menu.show(characterList, mouseEvent.getX(), mouseEvent.getY());
        }
    }

    private void exportSelectedCharacters() {
        if (herolabsCharacterList.getSelectedIndex() >= 0 ) {
            Character c = (Character) herolabsCharacterList.getModel().getElementAt(herolabsCharacterList.getSelectedIndex());
            Config.ConfigEntry ce = config.get(c.getName());
            if (ce != null && ce.isOk()) {
                exportCharacters();
            } else {
                JOptionPane.showMessageDialog(panel, "The character you selected hasn't been configured yet.  Please configure " +
                        "and try again.", "Export Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void configureSelectedCharacters() {
        //get the selected item
        for (Config.ConfigEntry entry : getSelectedCharacters()) {
            ConfigureCharacterDialog dialog = new ConfigureCharacterDialog(herolabsCharacterList, entry, prefs);
            dialog.pack();
            dialog.setVisible(true);
        }
    }

    private Collection<Config.ConfigEntry> getSelectedCharacters() {
        final Collection<Config.ConfigEntry> entries = new ArrayList<Config.ConfigEntry>();
        
        Object[] selectedValues = herolabsCharacterList.getSelectedValues();
        for (Object object : selectedValues) {
            entries.add(config.getOrCreate(((Character) object).getName()));
        }

        return entries;
    }

    private void updateTokens() {
        System.out.println("Would be updatin'");
        config.defaultConfigEntries();
    }

    private void errorDialog(String title, String error) {
        JOptionPane.showMessageDialog(panel, error, title, JOptionPane.ERROR_MESSAGE);
    }


    private void exportCharacters() {
        Object [] selectedValues = herolabsCharacterList.getSelectedValues();
        HeroLabPathfinderDigester dig = new HeroLabPathfinderDigester();
        boolean success = true;
        ArrayList<Character> notExported = new ArrayList<Character>();
        
        for ( Object object : selectedValues ) {
            Character character = (Character) object;
            try {
                if (!dig.saveCharacter(config, character) ) {
                    notExported.add(character);
                }
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
            String message = "Successfully exported " + (selectedValues.length - notExported.size()) + " out of " + selectedValues.length + " Maptools token(s).";
            if (!notExported.isEmpty()) {
                message += "  " + notExported.size() + " tokens were not exported as they are not fully configured.";
                // TODO: do something more useful with this collection
            }
                    
            JOptionPane.showMessageDialog(panel, message);
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
                label.setToolTipText( "Double-click to enter configuration information for this character, right-click for more options");
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
