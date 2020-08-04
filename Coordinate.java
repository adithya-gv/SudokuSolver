// The Coordinate class provides a convenient way to represent grid spaces in a Sudoku puzzle.

public class Coordinate {

    private int x; // X-Coordinate for a Coordinate in a Sudoku
    private int y; // Y-Coordinate for a Coordinate in a Sudoku

    public Coordinate() { // Empty Constructor
        x = 0; // set x and y to be 0 if no parameters through constructor
        y = 0;
    }

    public Coordinate(int indX, int indY) { // Constructor with given parameters
        x = indX; // set x and y to be their respective parameters
        y = indY;
    }

    public int getX() { // getter for x
        return x;
    }

    public int getY() { // getter for y
        return y;
    }

    public void setX(int indX) { // setter for x
        x = indX;
    }

    public void setY(int indY) { // setter for y
        y = indY;
    }

    public String toString() {
        return ("(" + this.getX() + ", " + this.getY() + ")");
    }

    public int[] getBoxNumber() {
        x = this.getX();
        y = this.getY();
        int[] box = new int[2];
        if (x >= 6 && y >= 6) {
            box[0] = 6;
            box[1] = 6;
            return box;
        } else if (x >= 6 && y >= 3) {
            box[0] = 6;
            box[1] = 3;
            return box;
        } else if (x >= 6 && y >= 0) {
            box[0] = 6;
            box[1] = 0;
            return box;
        } else if (x >= 3 && y >= 6) {
            box[0] = 3;
            box[1] = 6;
            return box;
        } else if (x >= 3 && y >= 3) {
            box[0] = 3;
            box[1] = 3;
            return box;
        } else if (x >= 3 && y >= 0) {
            box[0] = 3;
            box[1] = 0;
            return box;
        } else if (x >= 0 && y >= 6) {
            box[0] = 0;
            box[1] = 6;
            return box;
        } else if (x >= 0 && y >= 3) {
            box[0] = 0;
            box[1] = 3;
            return box;
        } else {
            box[0] = 0;
            box[1] = 0;
            return box;
        }
    }
}
