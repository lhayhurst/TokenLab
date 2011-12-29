package net.sozinsoft.tokenlab.ui;

import net.sozinsoft.tokenlab.Config;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

/**
 * Adding some common directory/file selection functionality to simplify our dialogs.
 * (Should probably prefer delegation over inheritance, but right now this is easier.)
 */
public class FileSelectionDialog extends JDialog {
    public static final FileFilter IMAGE_FILE_FILTER = new FileFilter() {
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            } else {
                String name = f.getName();
                return Config.IMAGE_EXTENSION_PREDICATE.evaluate(name);
            }
        }

        @Override
        public String getDescription() {
            return "Image files";
        }
    };




    private String selectWithAWT(String dialogTitle, String directory, String fileName, boolean selectDirectory, int dialogType) {
        JFrame frame = new JFrame();
        
        if (selectDirectory) {
            System.setProperty("apple.awt.fileDialogForDirectories", "true");
        }
        FileDialog dialog = new FileDialog(frame, dialogTitle, dialogType);

        dialog.setDirectory(directory);
        dialog.setFile(fileName);
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

    private String selectPath(String dialogTitle, String currentDirectory, String fileName, boolean selectDirectory, boolean saveDialog) {
        if (isMacOS()) {
            return selectWithAWT(dialogTitle, currentDirectory, fileName, selectDirectory, saveDialog ? FileDialog.SAVE : FileDialog.LOAD);
        } else {
            return selectWithSwing(dialogTitle, currentDirectory, selectDirectory);
        }
    }

    protected String selectDirectory(String selectionType, String directory) {
        return selectPath("Select " + selectionType + " Directory", directory, null, true, false);
    }

    protected String selectFile(String selectionType, String directory) {
        return selectPath("Select " + selectionType, directory, null, false, false);
    }

    protected String selectFile(String selectionType, String directory, String fileName) {
        return selectPath("Select " + selectionType, directory, fileName, false, false);
    }
    
    protected String saveFileAs(String selectionType, String directory, String fileName) {
        return selectPath("Save " + selectionType + " as", directory, fileName, false, true);
    }

    protected boolean isMacOS() {
        String osName = System.getProperty("os.name").toLowerCase();
        return osName.startsWith("mac os x");
    }
}
