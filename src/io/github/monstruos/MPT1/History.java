package io.github.monstruos.MPT1;

import javax.swing.*;
import java.awt.*;

public class History extends JFrame {
    private JList<String> historyList;
    private JPanel rootPanel;
    private JButton clearButton;

    private DefaultListModel<String> model = new DefaultListModel<>();

    public History() {
        setMinimumSize(new Dimension(200, 200));
        setContentPane(rootPanel);
        setVisible(false);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        historyList.setModel(model);
        clearButton.addActionListener(e -> clear());
    }

    public void addEntry(String entry) {
        model.addElement(entry);
    }

    public void clear() {
        model.clear();
    }
}
