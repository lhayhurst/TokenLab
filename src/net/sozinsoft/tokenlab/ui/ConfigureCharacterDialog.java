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
    private Config.ConfigEntry  configEntry;
    private final Config config;
    private JFileChooser imageChooser;
    private JFileChooser tokenOutputChooser;
    private FileDialog tokenOutputDialog;
    private JList targetList;


    private void setImageFileChooserFilters(JFileChooser chooser) {
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String name = f.getName();
                    return name.endsWith("tiff") || name.endsWith("tif") || name.endsWith("gif") ||
                            name.endsWith("bmp") || name.endsWith("jpg") || name.endsWith("jpeg") ||
                            name.endsWith("png");
                }
            }

            @Override
            public String getDescription() {
                return "Image files";
            }
        });
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

    private void postFieldUpdate(Config.ConfigEntry configEntry) {
        updateFields(configEntry);

        if ( !setPogImageButton.isEnabled() &&
                !setPortraitImageButton.isEnabled() &&
                !setTokenLocationButton.isEnabled()) {
            buttonOK.setEnabled(true);
        }
    }


    public ConfigureCharacterDialog( JList targetList, final Config.ConfigEntry configEntry, final Config config) {
        this.configEntry = configEntry;
        this.targetList = targetList;
        this.config = config;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.setEnabled(false);

        updateFields(configEntry);

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
                String portraitFile = selectFile("portrait", config.getPortraitDirectory());
                if (portraitFile != null) {
                    configEntry.setPortraitFilePath(portraitFile);
                    if (config.getPortraitDirectory() == null) {
                        config.setPortraitDirectory(configEntry.getPortraitDirectory());
                    }

                    setPortraitImageButton.setEnabled(false);
                    postFieldUpdate(configEntry);
                }
//                 int returnVal = imageChooser.showOpenDialog(null);
//                 if (returnVal == JFileChooser.APPROVE_OPTION) {
//                     File imageFile = imageChooser.getSelectedFile();
//                     config.put(Config.IMAGE_DIR, imageFile.getParent());
//                     configEntry.setPortraitFilePath(imageFile.getAbsolutePath());
//                     setPortraitImageButton.setEnabled(false);
//                     postFieldUpdate(configEntry);
//                 }
            }
        });
        setPogImageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pogFile = selectFile("Pog", config.getPogDirectory());
                if (pogFile != null) {
                    configEntry.setPogFilePath(pogFile);
                    if (config.getPogDirectory() == null) {
                        config.setPogDirectory(configEntry.getPogDirectory());
                    }
                    
                    setPogImageButton.setEnabled(false);
                    postFieldUpdate(configEntry);
                }
                
//                int returnVal = imageChooser.showOpenDialog(null);
//                if (returnVal == JFileChooser.APPROVE_OPTION) {
//                     File imageFile = imageChooser.getSelectedFile();
//                     config.put(Config.IMAGE_DIR, imageFile.getParent());
//                     configEntry.setPogFilePath(imageFile.getAbsolutePath());
//                     setPogImageButton.setEnabled(false);
//                     postFieldUpdate(configEntry);
//                 }
            }
        });
        setTokenLocationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)  {
                onSelectTokenLocation(configEntry);
            }
        });
    }

    private void onSelectTokenLocation(Config.ConfigEntry configEntry) {
        JFrame frame = new JFrame();
        System.setProperty("apple.awt.fileDialogForDirectories", "true");
        FileDialog dialog = new FileDialog(frame, "Save Token As", FileDialog.SAVE);

        dialog.setFile(configEntry.getTokenFileName());
        dialog.setDirectory(configEntry.getTokenFileDirectory());
        dialog.setModal(true);
        dialog.setVisible(true);

        System.setProperty("apple.awt.fileDialogForDirectories", "false");

        if (dialog.getFile() != null) {
            configEntry.setTokenFilePath(dialog.getDirectory() + dialog.getFile());
            if (config.getOutputTokenDirectory() == null) {
                config.setOutputTokenDirectory(configEntry.getTokenFileDirectory());
            }
            setTokenLocationButton.setEnabled(false);
            postFieldUpdate(configEntry);
        }
    }

    private void updateFields(Config.ConfigEntry configEntry) {
        portraitImageField.setText(configEntry.getPortraitFilePath());
        pogImageField.setText(configEntry.getPogFilePath());
        tokenOutputField.setText(configEntry.getOutputTokenPath());
    }

    private void onOK() {
        targetList.repaint();
        dispose();
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
