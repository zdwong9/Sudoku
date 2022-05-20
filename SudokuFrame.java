import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Line2D.*;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

import javax.swing.border.Border;

public class SudokuFrame extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JButton solveButton;
    private JButton clearButton;
    private JTextField[][] grid = new JTextField[9][9];

    public JTextField[][] getGrid() {
        return grid;
    }

    public JPanel getPanel() {
        return mainPanel;
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

        initaliseGrid();

        this.add(mainPanel);

        buttonPanel.add(clearButton);
        buttonPanel.add(solveButton);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void initaliseGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = new JTextField("");
                grid[i][j].setLayout(new GridLayout(1, 1));
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

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == solveButton) {
            JOptionPane.showMessageDialog(this, "Solving...", "Solve?", JOptionPane.INFORMATION_MESSAGE);
            try {

                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            if (!SudokuHelper.fixAndCheckGrid(this) || !SudokuHelper.checkGrid(this)) {
                JOptionPane.showMessageDialog(null, "Invalid grid ah sial", "Invalid Grid",
                        JOptionPane.INFORMATION_MESSAGE);
                SudokuHelper.makeGridEditable(this);
                System.out.println("okoko");
                return;
            }

            String[][] oldGrid = SudokuHelper.saveGrid(grid);

            if (SudokuHelper.solveGrid(this)) {
                JOptionPane.showMessageDialog(this, "Solved your puzzle YAY...", "Solved",
                        JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "Cant solve your puzzle.\nPlease enter a new puzzle",
                        "Invalid Board", JOptionPane.INFORMATION_MESSAGE);

                SudokuHelper.revertGrid(oldGrid, grid);
                SudokuHelper.makeGridEditable(this);
            }

        } else if (e.getSource() == clearButton) {
            JOptionPane.showMessageDialog(this, "Clearing...", "Clear?", JOptionPane.INFORMATION_MESSAGE);
            try {

                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            SudokuHelper.clearGrid(this);
            SudokuHelper.makeGridEditable(this);

        }
    }

    public static void main(String[] args) {
        new SudokuFrame();
        
    }
}
