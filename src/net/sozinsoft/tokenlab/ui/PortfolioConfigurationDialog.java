package net.sozinsoft.tokenlab.ui;

import net.sozinsoft.tokenlab.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class PortfolioConfigurationDialog extends JDialog {
    private JPanel contentPanel;
    private JTextField pogDirectoryField;
    private JTextField outputDirectoryField;
    private JButton selectPortraitDirectoryButton;
    private JButton selectPogDirectoryButton;
    private JButton selectTokenOutputDirectoryButton;
    private JTextField portraitDirectoryField;
    private JButton buttonOK;
    private JButton buttonCancel;
    private Config config;
    private String outputTokenDirectory;
    private String portraitDirectory;
    private String pogDirectory;
    private boolean okPressed;

    public PortfolioConfigurationDialog(Config config) {
        this.config = config;

        setContentPane(contentPanel);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        okPressed = false;

        outputTokenDirectory = config.getOutputTokenDirectory();
        pogDirectory = config.getPogDirectory();
        portraitDirectory = config.getPortraitDirectory();


        outputDirectoryField.setText(outputTokenDirectory);
        pogDirectoryField.setText(pogDirectory);
        portraitDirectoryField.setText(portraitDirectory);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                onCancel();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                onCancel();
            }
        });
        contentPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        selectPortraitDirectoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                onSelectPortraitDirectory();
            }
        });

        selectPogDirectoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                onSelectPogDirectory();
            }
        });

        selectTokenOutputDirectoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                onSelectTokenOutputDirectory();
            }
        });
    }

    private String selectDirectoryWithAWT(String selectionType, String currentDirectory) {
        JFrame frame = new JFrame();
        System.setProperty("apple.awt.fileDialogForDirectories", "true");
        FileDialog dialog = new FileDialog(frame, "Select " + selectionType + " Directory");

        dialog.setDirectory(currentDirectory);
        dialog.setModal(true);
        dialog.setVisible(true);

        System.setProperty("apple.awt.fileDialogForDirectories", "false");

        // TODO: What happens if this is a file?
        return dialog.getDirectory() + dialog.getFile();
    }

    private String selectDirectoryWithSwing(String selectionType, String currentDirectory) {
        JFileChooser fileChooser = new JFileChooser(currentDirectory);
        fileChooser.setDialogTitle("Select " + selectionType + " Directory");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            return selectedFile.getPath();
        }

        return null;
    }
    
    private String selectDirectory(String selectionType, String currentDirectory) {
        String osName = System.getProperty("os.name").toLowerCase();
        boolean isMacOs = osName.startsWith("mac os x");
        if (isMacOs) {
            return selectDirectoryWithAWT(selectionType, currentDirectory);
        } else {
            return selectDirectoryWithSwing(selectionType, currentDirectory);
        }
    }

    private void onSelectTokenOutputDirectory() {
        String directory = selectDirectory("Token", config.getOutputTokenDirectory()) ;

        if (directory != null) {
            outputTokenDirectory = directory;
        }

        outputDirectoryField.setText(outputTokenDirectory);
    }

    private void onSelectPogDirectory() {
        String directory = selectDirectory("Pog", config.getPogDirectory());

        if (directory != null) {
           pogDirectory = directory;
        }

        pogDirectoryField.setText(pogDirectory);
    }

    private void onSelectPortraitDirectory() {
        String directory = selectDirectory("Portrait", config.getPortraitDirectory());

        if (directory != null) {
           portraitDirectory = directory;
        }

        portraitDirectoryField.setText(portraitDirectory);
    }

    private void onCancel() {
        dispose();
    }

    private void onOK() {
        okPressed = true;
        dispose();
    }

    public String getOutputTokenDirectory() {
        return outputTokenDirectory;
    }

    public String getPortraitDirectory() {
        return portraitDirectory;
    }

    public String getPogDirectory() {
        return pogDirectory;
    }

    public boolean isOkPressed() {
        return okPressed;
    }
}
