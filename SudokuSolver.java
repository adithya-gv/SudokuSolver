import java.util.*;
import java.io.*;

public class SudokuSolver {

    public static void main(String[] args) throws FileNotFoundException {

        File f = new File("sudoku.txt");
        Scanner input = new Scanner(f);
        int[][] sudoku = new int[9][9];

        for (int i = 0; i < 9; i++) {
           for (int j = 0; j < 9; j++) {
               sudoku[i][j] = input.nextInt();
           }
        }

        System.out.println("Unsolved Sudoku:");
        for (int i = 0; i < 9; i++) {
            System.out.println(Arrays.toString(sudoku[i]));
        }
        int moveCount = 0;
        long startTime = System.currentTimeMillis();

        ArrayList<Coordinate> coords = getUnsolved(sudoku);
        System.out.println();
        for (int i = 0; i < coords.size(); i++) {
            Coordinate a = coords.get(i);
            int x = a.getX();
            int y = a.getY();
            boolean check = false;
            for (int j = (sudoku[x][y]) + 1 ; j <= 9; j++) {
                if (solveCoord(sudoku, a, j)) {
                    sudoku[x][y] = j;
                    check = true;
                    moveCount++;
                    break;
                }
            }
            if (!check && i > 0) {
                sudoku[x][y] = 0;
                i = i - 2;
                moveCount++;
            }
        }

        long endTime = System.currentTimeMillis();

        double solveTime = (double)(endTime - startTime);

        System.out.println("Solved Sudoku:");
        for (int i = 0; i < 9; i++) {
            System.out.println(Arrays.toString(sudoku[i]));
        }
        System.out.println();
        System.out.println("Solved in " + solveTime + " ms, using " + moveCount + " moves.");

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

}
