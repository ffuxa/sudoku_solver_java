import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String args[]) throws InterruptedException {
        System.out.println("Hello there friend... let's play Sudoku!");

        int[][] grid = new int[][]{
                {7,0,0,8,0,0,3,0,0},
                {0,0,1,0,0,9,0,0,0},
                {0,8,0,0,7,0,0,0,4},
                {4,0,0,1,0,0,0,6,0},
                {0,0,6,0,8,0,4,0,0},
                {0,5,0,0,0,2,0,0,8},
                {6,0,0,0,9,0,0,5,0},
                {0,0,0,3,0,0,9,0,0},
                {0,0,5,0,0,8,0,0,1},
        };

        SudokuSolver sudokuSolver = new SudokuSolver();
        sudokuSolver.updateGrid(grid);

        System.out.println("Grid before: ");
        sudokuSolver.printGrid();

        System.out.print("Solving");

        // Sleep for 3 seconds to appear as if solving is challenging
        for (int i = 0; i < 3; ++i) {
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("\n");

        if (!sudokuSolver.solve()) {
            System.out.println("Unable to solve puzzle. Exiting");
            return;
        }

        System.out.println("Puzzle Solved!\n\nGrid after: ");
        sudokuSolver.printGrid();
        System.out.println("\nGoodbye!");
    }
}
