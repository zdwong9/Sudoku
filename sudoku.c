#include <stdbool.h>
#include <stdio.h>
#define GRID_SIZE 9

void printboard(int board[][GRID_SIZE]);
bool isvalidrow(int board[][GRID_SIZE], int row, int num);
bool isvalidcolumn(int board[][GRID_SIZE], int col, int num);
bool isvalidbox(int board[][GRID_SIZE], int col, int row, int num);
bool isvalidplacement(int board[][GRID_SIZE], int col, int row, int num);
bool solveboard(int board[][GRID_SIZE]);

int main(void) {
    // int board[9][9] = {
    //     {4, 0, 0, 7, 3, 1, 0, 0, 2},
    //     {0, 0, 6, 0, 5, 0, 1, 0, 0},
    //     {8, 0, 0, 0, 0, 0, 0, 0, 3},
    //     {0, 0, 4, 6, 0, 7, 5, 0, 8},
    //     {0, 8, 0, 0, 0, 0, 0, 2, 0},
    //     {0, 0, 2, 5, 8, 3, 4, 0, 0},
    //     {2, 0, 0, 0, 0, 0, 0, 0, 6},
    //     {0, 0, 8, 0, 7, 0, 2, 0, 0},
    //     {1, 0, 0, 3, 0, 2, 0, 0, 9},
    // };
    // int board[9][9] = {
    //     {0, 0, 0, 4, 0, 0, 2, 0, 0},
    //     {0, 0, 2, 0, 0, 0, 0, 1, 8},
    //     {5, 0, 6, 9, 0, 0, 0, 3, 0},
    //     {0, 6, 9, 0, 0, 0, 3, 0, 0},
    //     {0, 5, 0, 0, 0, 0, 0, 2, 1},
    //     {8, 0, 0, 1, 5, 7, 6, 0, 9},
    //     {0, 0, 0, 0, 3, 0, 9, 6, 0},
    //     {9, 0, 0, 6, 0, 2, 0, 5, 0},
    //     {0, 0, 0, 0, 0, 0, 7, 0, 2},
    // };
    // int board[9][9] = {
    //     {0, 0, 0, 9, 2, 0, 0, 0, 0},
    //     {0, 0, 9, 0, 0, 1, 0, 0, 0},
    //     {2, 0, 6, 0, 0, 0, 8, 0, 0},
    //     {9, 1, 0, 0, 0, 0, 0, 0, 7},
    //     {0, 0, 7, 5, 0, 8, 0, 0, 0},
    //     {6, 8, 0, 0, 9, 0, 0, 5, 0},
    //     {0, 3, 0, 0, 0, 5, 0, 7, 0},
    //     {0, 0, 0, 0, 3, 0, 0, 0, 2},
    //     {0, 0, 0, 0, 1, 0, 0, 0, 0},
    // };
    // int board[9][9] = {
    //     {0, 9, 0, 0, 0, 0, 0, 0, 0},
    //     {7, 0, 0, 0, 2, 9, 0, 0, 1},
    //     {0, 4, 0, 6, 5, 0, 0, 0, 0},
    //     {0, 0, 3, 0, 0, 7, 4, 5, 0},
    //     {4, 8, 0, 0, 0, 0, 0, 9, 6},
    //     {0, 7, 9, 5, 0, 0, 3, 0, 0},
    //     {0, 0, 0, 0, 7, 5, 0, 3, 0},
    //     {1, 0, 0, 8, 6, 0, 0, 0, 9},
    //     {0, 0, 0, 0, 0, 0, 0, 06, 0},
    // };
    int board[9][9] = {
        {0, 0, 8, 0, 0, 7, 0, 0, 0},
        {0, 0, 2, 9, 0, 0, 6, 0, 0},
        {1, 0, 7, 6, 0, 0, 0, 5, 0},
        {4, 0, 0, 2, 0, 0, 0, 9, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {8, 0, 0, 3, 0, 0, 2, 0, 0},
        {0, 3, 0, 5, 0, 0, 0, 0, 0},
        {6, 0, 5, 0, 3, 0, 4, 0, 0},
        {0, 0, 9, 0, 0, 0, 0, 0, 8},
    };
    printboard(board);

    if (solveboard(board)) {
        printf("Congrats\n");
    } else {
        printf("Nay\n");
    }
    printboard(board);
}
void printboard(int board[][GRID_SIZE]) {
    printf("\nYour sudoku:\n");
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 8; j++) {
            printf("%d|", board[i][j]);
            if (j % 3 == 2) {
                printf("|");
            }
        }
        printf("%d\n", board[i][8]);
        if (i % 3 == 2 && i != 8) {
            printf("-----------------\n");
        }
    }
    printf("\n");
}
bool isvalidrow(int board[][GRID_SIZE], int row, int num) {
    for (int i = 0; i < 9; i++) {
        if (board[row][i] == num) {
            return true;
        }
    }
    return false;
}
bool isvalidcolumn(int board[][GRID_SIZE], int col, int num) {
    for (int i = 0; i < 9; i++) {
        if (board[i][col] == num) {
            return true;
        }
    }
    return false;
}
bool isvalidbox(int board[][GRID_SIZE], int col, int row, int num) {
    int localboxrow = row - row % 3;
    int localboxcolum = col - col % 3;
    for (int i = localboxrow; i < localboxrow + 3; i++) {
        for (int j = localboxcolum; j < localboxcolum + 3; j++) {
            if (board[i][j] == num) {
                return true;
            }
        }
    }
    return false;
}
bool isvalidplacement(int board[][GRID_SIZE], int col, int row, int num) {
    return !isvalidbox(board, col, row, num) && !isvalidcolumn(board, col, num) && !isvalidrow(board, row, num);
}

bool solveboard(int board[][GRID_SIZE]) {
    for (int row = 0; row < GRID_SIZE; row++) {
        for (int colummn = 0; colummn < GRID_SIZE; colummn++) {
            if (board[row][colummn] == 0) {
                for (int numbertotry = 1; numbertotry <= 9; numbertotry++) {
                    if (isvalidplacement(board, colummn, row, numbertotry)) {
                        board[row][colummn] = numbertotry;
                        if (solveboard(board)) {
                            return true;
                        } else {
                            board[row][colummn] = 0;
                        }
                    }
                }
                return false;
            }
        }
    }
    return true;
}