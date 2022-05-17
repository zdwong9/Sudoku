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

    public void initaliseButtons() {
        buttonPanel = new JPanel();

        solveButton = new JButton();
        solveButton.setText("Solve");

        clearButton = new JButton();
        clearButton.setText("Clear");

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

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        // if (e.getSource() == button && textField.getText().equals("Correct")) {
        // JOptionPane.showMessageDialog(null, textField.getText());
        // } else {
        // JOptionPane.showMessageDialog(null, "Type Wrong liao");
        // button.setEnabled(false);
        // textField.setEditable(false);
        // }
    }

    public static void main(String[] args) {
        new SudokuFrame();
    }
}
