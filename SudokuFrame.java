import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Line2D.*;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import java.util.List;
import javax.swing.border.Border;

public class SudokuFrame extends JFrame implements ActionListener {
    JPanel mainPanel;
    JPanel buttonPanel;
    JButton solveButton;
    JButton clearButton;
    JTextField[][] grid = new JTextField[9][9];
    List<String> numbers = new ArrayList<>();
    {
        for (char c = '1'; c <= '9'; c++) {
            numbers.add(c + "");
        }
    }

    public void initaliseButtons() {
        buttonPanel = new JPanel();

        solveButton = new JButton();
        solveButton.setText("Solve");
        solveButton.addActionListener(this);

        clearButton = new JButton();
        clearButton.setText("Clear");
        clearButton.addActionListener(this);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(9, 9));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = new JTextField();
                grid[i][j].setLayout(new GridLayout(1, 1));
                int rightBorder = 0;
                int bottomBorder = 0;

                if (i % 3 == 2 || j % 3 == 2) {

                    Border border = null;
                    if (i % 3 == 2 && j % 3 == 2) {
                        border = new SudokuBorder(BorderEnum.RIGHT, BorderEnum.BOTTOM);
                    } else if (j % 3 == 2) {
                        border = new SudokuBorder(BorderEnum.RIGHT);
                    } else {
                        border = new SudokuBorder(BorderEnum.BOTTOM);
                    }
                    grid[i][j].setBorder(border);
                } else {
                    grid[i][j].setBorder(new SudokuBorder());
                }
                grid[i][j].setFont(new Font("Consolas", Font.BOLD, 50));
                grid[i][j].setHorizontalAlignment(JTextField.CENTER);
                grid[i][j].addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (((c < '1') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                            e.consume(); // ignore event
                        }
                    }
                });
                mainPanel.add(grid[i][j]);
            }
        }
        this.add(mainPanel);

        buttonPanel.add(clearButton);
        buttonPanel.add(solveButton);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public SudokuFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(1000, 800));
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        initaliseButtons();

        mainPanel = new JPanel();

        this.setVisible(true);
    }

    public void clearGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j].setText("");
            }
        }
    }

    public void makeGridEditable() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j].setEditable(true);
            }
        }
    }

    public void fixAndCheckGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j].setEditable(false);
                if (!numbers.contains(grid[i][j].getText())) {
                    JOptionPane.showMessageDialog(this, "Invalid grid ah sial", "Invalid Grid",
                            JOptionPane.INFORMATION_MESSAGE);
                    makeGridEditable();
                    return;
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == solveButton) {
            JOptionPane.showMessageDialog(this, "Solving...", "Solve?", JOptionPane.INFORMATION_MESSAGE);
            fixAndCheckGrid();
            // solveGrid();

        } else if (e.getSource() == clearButton) {
            JOptionPane.showMessageDialog(this, "Clearing...", "Clear?", JOptionPane.INFORMATION_MESSAGE);
            clearGrid();

        }
    }

    public static void main(String[] args) {
        new SudokuFrame();
    }
}
