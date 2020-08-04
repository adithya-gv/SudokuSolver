import java.util.*;
import java.io.*;

public class SudokuGenerator {

    public static void main(String[] args) throws FileNotFoundException {

        int[][] sudoku = new int[9][9];

        long startTime = System.currentTimeMillis();

        int first = (int) (10 * Math.random());
        int second = (int) (10 * Math.random());

        sudoku[0][0] = first;
        sudoku[8][8] = second;

        ArrayList<Coordinate> coords = getUnsolved(sudoku);
        for (int i = 0; i < coords.size(); i++) {
            Coordinate a = coords.get(i);
            int x = a.getX();
            int y = a.getY();
            boolean check = false;
            for (int j = (sudoku[x][y]) + 1 ; j <= 9; j++) {
                if (solveCoord(sudoku, a, j)) {
                    sudoku[x][y] = j;
                    check = true;
                    break;
                }
            }
            if (!check && i > 0) {
                sudoku[x][y] = 0;
                i = i - 2;
            }
        }


        sudoku = randomScramble(sudoku, 3.0);
        PrintStream stdout = System.out;
        PrintStream output1 = new PrintStream("sudoku.txt");
        System.setOut(output1);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();

        long endTime = System.currentTimeMillis();

        double solveTime = (double)(endTime - startTime);

        System.setOut(stdout);
        System.out.println();
        System.out.println("Sudoku generated in " + solveTime + " ms.");

    }

    public static ArrayList<Coordinate> getUnsolved(int[][] sudoku) {
        ArrayList<Coordinate> unsolved1 = new ArrayList<Coordinate>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] == 0) {
                    Coordinate a = new Coordinate(i, j);
                    unsolved1.add(a);
                }
            }
        }
        return unsolved1;
    }

    public static boolean solveCoord(int[][] sudoku, Coordinate a, int i) {
        int row = a.getX();
        int col = a.getY();
        if (checkRow(sudoku, row, col, i)) {
            if (checkCol(sudoku, row, col, i)) {
                if (checkBox(sudoku, a, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkRow(int[][] sudoku, int row, int col, int q) {
        for (int i = 0; i < 9; i++) {
            if (i != col) {
                if (sudoku[row][i] == q) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkCol(int[][] sudoku, int row, int col, int q) {
        for (int i = 0; i < 9; i++) {
            if (i != row) {
                if (sudoku[i][col] == q) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkBox(int[][] sudoku, Coordinate a, int q) {
        int[] boxNumber = a.getBoxNumber();
        int row = a.getX();
        int col = a.getY();
        int rowStart = boxNumber[0];
        int colStart = boxNumber[1];
        for (int i = rowStart; i < rowStart + 3; i++) {
            for (int j = colStart; j < colStart + 3; j++) {
                if (j != col || i != row) {
                    if (sudoku[i][j] == q) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static int[][] randomScramble(int[][] sudoku, double difficulty) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                double scramble = Math.random();
                if (scramble < difficulty * 0.25) {
                    sudoku[i][j] = 0;
                }
            }
        }
        return sudoku;
    }

}
