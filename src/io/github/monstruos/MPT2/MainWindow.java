package io.github.monstruos.MPT2;

import io.github.monstruos.MPT2.subforms.BasedWindow;
import io.github.monstruos.MPT2.subforms.ComplexWindow;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{
    private JTabbedPane tabs;
    private JPanel rootPanel;

    public MainWindow() {
        setMinimumSize(new Dimension(500, 400));
        setContentPane(rootPanel);
        setVisible(true);
        rootPanel.requestFocusInWindow();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        BasedWindow basedWindow = new BasedWindow();
        tabs.addTab("P-ичные числа", basedWindow.getContentPane());
        ComplexWindow complexWindow = new ComplexWindow();
        tabs.addTab("Комплексные числа", complexWindow.getContentPane());
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
