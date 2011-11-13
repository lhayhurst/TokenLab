package net.sozinsoft.tokenlab.ui;

import net.sozinsoft.tokenlab.ResourceManager;

import javax.swing.*;
import java.io.File;
import java.io.IOException;


public class IconCreator {

    public static ImageIcon createImageIcon(String path,
                                            String description) throws IOException {
        File imagePath = ResourceManager.getResouceByName(path).getAbsoluteFile();
        if (imagePath != null) {
            return new ImageIcon(imagePath.getAbsolutePath(), description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
