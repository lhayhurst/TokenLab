package net.sozinsoft.tokenlab.ui;

import net.sozinsoft.tokenlab.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class ConfigureCharacterDialog extends FileSelectionDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton setPortraitImageButton;
    private JButton setPogImageButton;
    private JButton setTokenLocationButton;
    private JTextField portraitImageField;
    private JTextField pogImageField;
    private JTextField tokenOutputField;
    private JList targetList;

    private final Config config;
    private Config.ConfigEntry entry;
    private String portraitPath;
    private String pogPath;
    private String tokenPath;

    private void setImageFileChooserFilters(JFileChooser chooser) {
        chooser.setFileFilter(IMAGE_FILE_FILTER);
    }

    private void setTokenFileChooserFilters(JFileChooser chooser ) {
       chooser.setFileFilter(new FileFilter() {
           @Override
           public boolean accept(File f) {
               if (f.isDirectory()) {
                   return true;
               } else {
                   String name = f.getName();
                   return name.endsWith("tok") || name.endsWith("pog");
               }
           }

           @Override
           public String getDescription() {
               return "Token files";
           }
       });
    }

    public ConfigureCharacterDialog( JList targetList, final Config.ConfigEntry configEntry, final Config config) {
        this.targetList = targetList;
        this.entry = configEntry;
        this.config = config;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.setTitle("Configure " + configEntry.getCharacterName());

        portraitPath = entry.getPortraitFilePath();
        pogPath = entry.getPogFilePath();
        tokenPath = entry.getTokenFilePath();

        updateFields();

        // for now - have to select using button
        portraitImageField.setEnabled(false);
        pogImageField.setEnabled(false);
        tokenOutputField.setEnabled(false);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        setPortraitImageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedFile = selectFile("Portrait for " + configEntry.getCharacterName(), config.getPortraitDirectory());
                if (selectedFile != null) {
                    portraitPath = selectedFile;
                    updateFields();
                }
            }
        });
        setPogImageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedFile = selectFile("Pog for " + configEntry.getCharacterName(), config.getPogDirectory());
                if (selectedFile != null) {
                    pogPath = selectedFile;
                    setPogImageButton.setEnabled(false);
                    updateFields();
                }
            }
        });
        setTokenLocationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)  {
                onSelectTokenLocation(configEntry);
            }
        });
    }

    private void onSelectTokenLocation(Config.ConfigEntry configEntry) {
        String newFilePath = saveFileAs("Token", entry.getTokenFileDirectory(), entry.getTokenFileName());
        if (newFilePath != null) {
            tokenPath = newFilePath;
            setTokenLocationButton.setEnabled(false);
            updateFields();
        }
    }

    private void updateFields() {
        portraitImageField.setText(portraitPath);
        pogImageField.setText(pogPath);
        tokenOutputField.setText(tokenPath);
    }

    private void onOK() {
        updateConfigEntry();
        targetList.repaint();
        dispose();
    }

    private void updateConfigEntry() {
        entry.setPortraitFilePath(portraitPath);
        if (config.getPortraitDirectory() == null) {
            config.setPortraitDirectory(entry.getPortraitDirectory());
        }

        entry.setPogFilePath(pogPath);
        if (config.getPogDirectory() == null) {
            config.setPogDirectory(entry.getPogDirectory());
        }

        entry.setTokenFilePath(tokenPath);
        if (config.getOutputTokenDirectory() == null) {
            config.setOutputTokenDirectory(entry.getTokenFileDirectory());
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        ConfigureCharacterDialog dialog = new ConfigureCharacterDialog( null, null, null);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
