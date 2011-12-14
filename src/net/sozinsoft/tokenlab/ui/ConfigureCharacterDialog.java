package net.sozinsoft.tokenlab.ui;

import net.sozinsoft.tokenlab.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.prefs.Preferences;

public class ConfigureCharacterDialog extends JDialog {
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
    private final Preferences prefs;
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

        if ( setPogImageButton.isEnabled() == false &&
             setPortraitImageButton.isEnabled() == false &&
             setTokenLocationButton.isEnabled() == false ) {
            buttonOK.setEnabled(true);
        }
    }


    public ConfigureCharacterDialog( JList targetList, final Config.ConfigEntry configEntry, final Preferences prefs ) {
        this.configEntry = configEntry;
        this.targetList = targetList;
        this.prefs  = prefs;
        imageChooser       = new JFileChooser( this.prefs.get(Config.IMAGE_DIR, "" ) );
        tokenOutputChooser = new JFileChooser( this.prefs.get(Config.TOKEN_DIR, "" ) );
        setImageFileChooserFilters(imageChooser);
        setTokenFileChooserFilters(tokenOutputChooser);
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
                 int returnVal = imageChooser.showOpenDialog(null);
                 if (returnVal == JFileChooser.APPROVE_OPTION) {
                     File imageFile = imageChooser.getSelectedFile();
                     prefs.put(Config.IMAGE_DIR, imageFile.getParent());
                     configEntry.setPortraitFilePath(imageFile.getAbsolutePath());
                     setPortraitImageButton.setEnabled(false);
                     postFieldUpdate(configEntry);
                 }
            }
        });
        setPogImageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnVal = imageChooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                     File imageFile = imageChooser.getSelectedFile();
                     prefs.put(Config.IMAGE_DIR, imageFile.getParent());
                     configEntry.setImageFilePath(imageFile.getAbsolutePath());
                     setPogImageButton.setEnabled(false);
                     postFieldUpdate(configEntry);
                 }
            }
        });
        setTokenLocationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)  {

                JFrame frame = new JFrame();
                System.setProperty("apple.awt.fileDialogForDirectories", "true");
                FileDialog dialog = new FileDialog(frame, "Save Token As", FileDialog.SAVE);

                dialog.setFile(configEntry.getTokenFileName());
                dialog.setDirectory(configEntry.getTokenFileDirectory());

                dialog.setVisible(true);

                if (dialog.getFile() != null) {
                    configEntry.setTokenFileName(dialog.getFile());
                    configEntry.setTokenFileDirectory(dialog.getDirectory());
                    prefs.put(Config.TOKEN_DIR, configEntry.getTokenFileDirectory());
                    setTokenLocationButton.setEnabled(false);
                    postFieldUpdate(configEntry);
                }
            }
        });
    }

    private void updateFields(Config.ConfigEntry configEntry) {
        portraitImageField.setText(configEntry.getPortraitFilePath());
        pogImageField.setText(configEntry.getImageFilePath());
        tokenOutputField.setText(configEntry.getOutputTokenTo());
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
