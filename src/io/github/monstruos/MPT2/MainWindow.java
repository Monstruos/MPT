package io.github.monstruos.MPT2;

import io.github.monstruos.MPT2.subforms.BasedWindow;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{
    private JTabbedPane tabs;
    private JPanel rootPanel;

    private BasedWindow basedWindow = new BasedWindow();

    public MainWindow() {
        setMinimumSize(new Dimension(500, 400));
        setContentPane(rootPanel);
        setVisible(true);
        rootPanel.requestFocusInWindow();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        tabs.addTab("P-ичные числа", basedWindow.getContentPane());
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
