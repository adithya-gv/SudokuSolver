import java.util.*;
import java.io.*;

public class Client {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner console = new Scanner(System.in);
        getInput(console, args);

    }

    public static void getInput(Scanner console, String[] args) throws FileNotFoundException {
        System.out.print("Would you like to generate or solve a Sudoku?: ");
        String input = console.next();
        execute(console, input, args);
    }

    public static void execute (Scanner console, String input, String[] args)  throws FileNotFoundException {
        if (input.contains("generate")) {
            SudokuGenerator a = new SudokuGenerator();
            a.main(args);
        } else if (input.contains("solve")) {
            SudokuSolver a = new SudokuSolver();
            a.main(args);
        } else {
            System.out.println("Invalid Input!");
            getInput(console, args);
        }
    }

}
