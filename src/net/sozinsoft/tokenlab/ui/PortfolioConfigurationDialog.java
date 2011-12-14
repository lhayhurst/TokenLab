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

    public PortfolioConfigurationDialog(Config config) {
        this.config = config;

        setContentPane(contentPanel);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        outputDirectoryField.setText(config.getOutputTokenDirectory());
        pogDirectoryField.setText(config.getPogDirectory());
        portraitDirectoryField.setText(config.getPortraitDirectory());

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

    private String selectDirectory(String selectionType, String currentDirectory) {
        JFrame frame = new JFrame();
        System.setProperty("apple.awt.fileDialogForDirectories", "true");
        FileDialog dialog = new FileDialog(frame, "Select " + selectionType + " Directory");

        dialog.setDirectory(currentDirectory);
        dialog.setModal(true);
        dialog.setVisible(true);

        System.setProperty("apple.awt.fileDialogForDirectories", "false");

        return dialog.getDirectory() + dialog.getFile();
    }

    private void onSelectTokenOutputDirectory() {
        String directory = selectDirectory("Token", config.getOutputTokenDirectory());

        if (directory != null) {
            config.setOutputTokenDirectory(directory);
        }

        outputDirectoryField.setText(config.getOutputTokenDirectory());
    }

    private void onSelectPogDirectory() {
        String directory = selectDirectory("Pog", config.getPogDirectory());

        if (directory != null) {
            config.setPogDirectory(directory);
        }

        pogDirectoryField.setText(config.getPogDirectory());
    }

    private void onSelectPortraitDirectory() {
        String directory = selectDirectory("Portrait", config.getPortraitDirectory());

        if (directory != null) {
            config.setPortraitDirectory(directory);
        }

        portraitDirectoryField.setText(config.getPortraitDirectory());
    }

    private void onCancel() {
        dispose();
    }

    private void onOK() {
        // todo: calculate defaulting
//        targetList.repaint();
        dispose();
    }
}
