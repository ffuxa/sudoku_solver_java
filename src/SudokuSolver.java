import java.lang.Math;

class SudokuSolver {
    private int[][] grid = new int[9][9];

    void updateGrid(int[][] new_grid) {
        grid = new_grid;
    }

    void printGrid() {
        System.out.println("  -----   -----   -----");

        for (int i = 0; i < 9; ++i) {
            System.out.print("| ");

            for (int j = 0; j < 9; ++j) {
                System.out.print(grid[i][j]);

                if ((j+1) % 3 == 0) {
                    System.out.print(" | ");
                } else {
                    System.out.print(" ");
                }
            }

            System.out.print("\n");


            if (i == 8) {
                System.out.println("  -----   -----   -----");
            }
            else if ((i+1) % 3 == 0) {
                System.out.println("  ----- | ----- | -----");
            }
        }
    }

    boolean solve() {
        return _solve(0, 0);
    }

    private int[] findUnassignedLocation(int row, int col) {
        boolean done = false;
        int cellCoordinates[] = new int[]{-1, -1};

        while (!done) {
            if (row == 9) {
                done = true;
            }
            else {
                if (grid[row][col] == 0) {
                    cellCoordinates[0] = row;
                    cellCoordinates[1] = col;
                    done = true;
                }
                else {
                    if (col < 8) {
                        col++;
                    }
                    else {
                        row++;
                        col = 0;
                    }
                }
            }
        }

        return cellCoordinates;
    }

    private boolean notUsedInRow(int row, int num) {
        for (int col = 0; col < 9; col++) {
            if (grid[row][col] == num) {
                return false;
            }
        }

        return true;
    }

    private boolean notUsedInCol(int col, int num) {
        for (int row = 0; row < 9; row++) {
            if (grid[row][col] == num) {
                return false;
            }
        }

        return true;
    }

    private boolean notUsedInBox(int row, int col, int num) {
        row = (int)Math.floor(row / 3) * 3;
        col = (int)Math.floor(col / 3) * 3;

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (grid[row + r][col + c] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean noConflicts(int row, int col, int num) {
        return notUsedInRow(row, num) &&
                notUsedInCol(col, num) &&
                notUsedInBox(row, col, num);
    }

    private boolean _solve(int row, int col) {
        int[] cellCoordinates = findUnassignedLocation(row, col);
        row = cellCoordinates[0];
        col = cellCoordinates[1];

        if (row == -1) {
            return true;
        }

        for (int num = 1; num <= 9; num++) {
            if (noConflicts(row, col, num)) {
                grid[row][col] = num;

                if (_solve(row, col)) {
                    return true;
                }

                grid[row][col] = 0;
            }
        }

        return false;
    }
}
