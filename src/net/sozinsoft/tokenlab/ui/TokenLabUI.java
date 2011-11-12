package net.sozinsoft.tokenlab.ui;

import net.sozinsoft.tokenlab.*;
import net.sozinsoft.tokenlab.Character;
import org.xml.sax.SAXException;


import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class TokenLabUI {
    public static final String HEROLABS_XML_DIR = "HEROLABS_XML_DIR";
    public static final String CONFIG_DIR = "CONFIG_DIR";
    JButton importButton;
    JToolBar toolbar;
    JList herolabsCharacterList;
    JButton exportAllButton;
    JButton exportSelectedButton;

    JFileChooser configChooser ;
    JFileChooser herolabsXMLChooser ;

    JPanel panel;
    JButton configButton;
    private JProgressBar progressBar;
    Config config;
    Preferences prefs;

    public static void main(String[] args) {
        try {

            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    createAndShowGUI();
                }
            });
        } finally {
            ResourceManager.cleanupTmpFiles();
        }
    }

    private static void createAndShowGUI() {

        JFrame frame = new JFrame("TokenLabUI");

        TokenLabUI ui = new TokenLabUI();
        ui.setDefaults();

        frame.setContentPane(ui.panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.pack();

        frame.setVisible(true);
    }

    public void setDefaults() {

        prefs = Preferences.userNodeForPackage(this.getClass());
        configChooser = new JFileChooser( prefs.get( CONFIG_DIR, "" ) );
        herolabsXMLChooser = new JFileChooser( prefs.get( HEROLABS_XML_DIR, "" ) );

        importButton.setEnabled(false);
        exportAllButton.setEnabled(false);
        exportSelectedButton.setEnabled(false);

        setChooserFileFilter(configChooser);
        setChooserFileFilter(herolabsXMLChooser);
;
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setValue( 0 );
        progressBar.setVisible(true);


    }

    private void setChooserFileFilter( JFileChooser chooser ) {
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().endsWith("xml") || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return null;
            }
        });
    }


    public TokenLabUI() {
        importButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnVal = herolabsXMLChooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {


                    File mostRecentOutputDirectory = herolabsXMLChooser.getSelectedFile();
                    prefs.put(HEROLABS_XML_DIR, mostRecentOutputDirectory.getAbsolutePath());

                    File xmlFile = herolabsXMLChooser.getSelectedFile();
                    HerolabsDigester dig = new HerolabsDigester();


                    try {

                        dig.parse(xmlFile);


                        final int count = dig.getCharacters().size();
                        progressBar.setValue( 0);

                        int i = 0;
                        for(Character c : dig.getCharacters() ) {
                            dig.processCharacter( config,c );
                            i++;

                            final int percent = i;
                            try {
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        progressBar.setValue( ( percent / count ) * 100 );
                                    }
                                });
                                java.lang.Thread.sleep(1000);
                            } catch (InterruptedException ie) {
                                ;
                            }
                        }

                        DefaultListModel model = new DefaultListModel();

                        for (net.sozinsoft.tokenlab.Character c : dig.getCharacters()) {
                            model.addElement(c);
                        }
                        herolabsCharacterList.setModel(model);
                        herolabsCharacterList.validate();

                    } catch (IOException e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (SAXException e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }

        });
        configButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                                                  int returnVal = configChooser.showOpenDialog(null);
                        for (int i = 0; i <= 10; i++) {
      final int percent = i;
      try {
        SwingUtilities.invokeLater(new Runnable() {
          public void run() {
            progressBar.setValue( percent * 10 );
          }
        });
        java.lang.Thread.sleep(1000);
      } catch (InterruptedException ae) {
        ;
      }
    }

                if (returnVal == JFileChooser.APPROVE_OPTION) {

                    File mostRecentOutputDirectory = configChooser.getSelectedFile();
                    prefs.put(CONFIG_DIR, mostRecentOutputDirectory.getAbsolutePath());
                    File configFile = configChooser.getSelectedFile();
                    config = new Config(configFile.getAbsolutePath());

                    try {
                        config.parseConfigFile();
                        importButton.setEnabled(true);
                    } catch (IOException e1) {
                        e1.printStackTrace();  //todo: create a dialog
                    } catch (SAXException e1) {
                        e1.printStackTrace();  //same here
                    }
                }

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
