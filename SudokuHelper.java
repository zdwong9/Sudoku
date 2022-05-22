import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Line2D.*;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import java.util.List;

public class SudokuHelper {

    static List<String> NUMBERS = new ArrayList<>();
    static int count = 0;
    static {
        for (char c = '1'; c <= '9'; c++) {
            NUMBERS.add(c + "");
        }
    }

    public static void makeGridEditable(SudokuFrame sudokuFrame) {
        JTextField[][] grid = sudokuFrame.getGrid();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j].setEditable(true);
            }
        }
    }

    public static boolean checkValid(JTextField[][] grid, int column, int row, int number) {
        for (int i = 0; i < 9; i++) {

            if (i != column && !grid[row][i].getText().equals("")) {
                if (number == Integer.parseInt(grid[row][i].getText())) {
                    return false;
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            if (i != row && !grid[i][column].getText().equals("")) {
                if (number == Integer.parseInt(grid[i][column].getText())) {
                    return false;
                }
            }
        }

        int rowDisplacement = (row / 3) * 3;
        int columnDisplacement = (column / 3) * 3;
        System.out.println(rowDisplacement + " " + columnDisplacement);
        for (int i = rowDisplacement; i < rowDisplacement + 3; i++) {
            for (int j = columnDisplacement; j < columnDisplacement + 3; j++) {
                if (i != row && j != column && !grid[i][j].getText().equals("")) {
                    if (number == Integer.parseInt(grid[i][j].getText())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean checkGrid(SudokuFrame sudokuFrame) {
        JTextField[][] grid = sudokuFrame.getGrid();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!grid[i][j].getText().equals("")
                        && !checkValid(grid, j, i, Integer.parseInt(grid[i][j].getText()))) {

                    return false;

                }
            }
        }
        return true;
    }

    public static boolean fixAndCheckGrid(SudokuFrame sudokuFrame) {
        JTextField[][] grid = sudokuFrame.getGrid();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j].setEditable(false);
                if (!grid[i][j].getText().equals("") && !NUMBERS.contains(grid[i][j].getText())) {

                    return false;
                }
            }
        }
        return true;
    }

    public static void clearGrid(SudokuFrame sudokuFrame) {
        JTextField[][] grid = sudokuFrame.getGrid();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j].setText("");
            }
        }
    }

    public static String[][] saveGrid(JTextField[][] grid) {
        String[][] savedGrid = new String[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                savedGrid[i][j] = grid[i][j].getText();
            }
        }
        return savedGrid;
    }

    public static void revertGrid(String[][] oldGrid, JTextField[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j].setText(oldGrid[i][j]);
            }
        }
    }

    public static boolean isvalidrow(JTextField[][] grid, int row, int num) {
        for (int i = 0; i < 9; i++) {

            int currentNumber = 0;
            if (!grid[row][i].getText().equals("")) {
                currentNumber = Integer.parseInt(grid[row][i].getText());
            }

            if (currentNumber == num) {
                return true;
            }
        }
        return false;
    }

    public static boolean isvalidcolumn(JTextField[][] grid, int col, int num) {
        for (int i = 0; i < 9; i++) {

            int currentNumber = 0;
            if (!grid[i][col].getText().equals("")) {
                currentNumber = Integer.parseInt(grid[i][col].getText());
            }

            if (currentNumber == num) {
                return true;
            }
        }
        return false;
    }

    public static boolean isvalidbox(JTextField[][] grid, int col, int row, int num) {
        int localboxrow = row - row % 3;
        int localboxcolum = col - col % 3;
        for (int i = localboxrow; i < localboxrow + 3; i++) {
            for (int j = localboxcolum; j < localboxcolum + 3; j++) {

                int currentNumber = 0;
                if (!grid[i][j].getText().equals("")) {
                    currentNumber = Integer.parseInt(grid[i][j].getText());
                }

                if (currentNumber == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isvalidplacement(JTextField[][] grid, int col, int row, int num) {
        return !isvalidbox(grid, col, row, num) && !isvalidcolumn(grid, col, num) && !isvalidrow(grid, row, num);
    }

    public static boolean solveGrid(SudokuFrame sudokuFrame) {
        JTextField[][] grid = sudokuFrame.getGrid();
        System.out.println("Solving" + ++count);
        for (int row = 0; row < 9; row++) {
            for (int colummn = 0; colummn < 9; colummn++) {
                int currentNumber = 0;
                if (!grid[row][colummn].getText().equals("")) {
                    currentNumber = Integer.parseInt(grid[row][colummn].getText());
                }
                if (currentNumber == 0) {
                    for (int numbertotry = 1; numbertotry <= 9; numbertotry++) {
                        if (isvalidplacement(grid, colummn, row, numbertotry)) {
                            grid[row][colummn].setText(numbertotry + "");
                            try {

                                TimeUnit.SECONDS.sleep(1);
                            } catch (InterruptedException ex) {
                                Thread.currentThread().interrupt();
                            }
                            grid[row][colummn].repaint();

                            if (solveGrid(sudokuFrame)) {
                                return true;
                            } else {
                                grid[row][colummn].setText("");

                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
