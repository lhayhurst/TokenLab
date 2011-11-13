package net.sozinsoft.tokenlab.ui;

import net.sozinsoft.tokenlab.Config;
import net.sozinsoft.tokenlab.ResourceManager;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class ExitFrame extends JFrame {
    public ExitFrame( Config config) {
        final Config myConfig = config;
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                ResourceManager.cleanupTmpFiles();
                myConfig.save();
                System.exit(0); //calling the method is a must
            }
        });
    }
}