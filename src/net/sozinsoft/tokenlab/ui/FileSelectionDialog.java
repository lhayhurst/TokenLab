package net.sozinsoft.tokenlab.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Adding some common directory/file selection functionality to simplify our dialogs.
 * (Should probably prefer delegation over inheritance, but right now this is easier.)
 */
public class FileSelectionDialog extends JDialog {
    private String selectWithAWT(String dialogTitle, String currentDirectory, boolean selectDirectory) {
        JFrame frame = new JFrame();
        
        if (selectDirectory) {
            System.setProperty("apple.awt.fileDialogForDirectories", "true");
        }
        FileDialog dialog = new FileDialog(frame, dialogTitle);

        dialog.setDirectory(currentDirectory);
        dialog.setModal(true);
        dialog.setVisible(true);

        if (selectDirectory) {
            System.setProperty("apple.awt.fileDialogForDirectories", "false");
        }
        
        if (dialog.getFile() != null) {
            return dialog.getDirectory() + dialog.getFile();
        }

        return null;
    }

    private String selectWithSwing(String dialogTitle, String currentDirectory, boolean selectDirectory) {
        JFileChooser fileChooser = new JFileChooser(currentDirectory);
        
        if (selectDirectory) {
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
        }

        fileChooser.setDialogTitle(dialogTitle);
            
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            return selectedFile.getPath();
        }

        return null;
    }

    protected String selectDirectory(String selectionType, String currentDirectory) {
        String dialogTitle = "Select " + selectionType + " Directory";

        if (isMacOS()) {
            return selectWithAWT(selectionType, currentDirectory, true);
        } else {
            return selectWithSwing(selectionType, currentDirectory, true);
        }
    }

    protected String selectFile(String selectionType, String parentDirectory) {
        String dialogTitle = "Select " + selectionType;

        if (isMacOS()) {
            return selectWithAWT(dialogTitle, parentDirectory, false);
        } else {
            return selectWithSwing(dialogTitle, parentDirectory, false);
        }
    }
    
    protected boolean isMacOS() {
        String osName = System.getProperty("os.name").toLowerCase();
        return osName.startsWith("mac os x");
    }
}
