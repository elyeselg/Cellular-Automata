/**
 * Class representing a cellular automaton simulating Conway's "Game of Life".
 */
public class GameOfLife extends CellularAutomaton {

    /**
     * Constructor for the {@code GameOfLife} class.
     *
     * @param rows The number of rows in the grid.
     * @param cols The number of columns in the grid.
     */
    public GameOfLife(int rows, int cols) {
        super(rows, cols); // Initialize the grid with the specified dimensions.
    }

    /**
     * Randomly initializes the state of the grid.
     * Each cell is randomly assigned a state of 0 (dead) or 1 (alive).
     */
    @Override
    public void initializeRandomState() {
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                state[i][j] = (Math.random() < 0.5) ? 0 : 1; // Randomly assign 0 or 1.
            }
        }
    }

    /**
     * Evolves the "Game of Life" simulation over a specified number of steps.
     *
     * @param numSteps The number of steps to evolve the simulation.
     */
    @Override
    public void evolve(int numSteps) {
        for (int step = 0; step < numSteps; step++) {
            System.out.println("Step " + (step + 1)); // Display the current step number.
            printState(); // Print the current state of the grid.
            applyRules(); // Apply the rules to compute the next state.
        }
    }

    /**
     * Applies the rules of Conway's "Game of Life" to update the grid state.
     * Rules:
     * 1. Any live cell with fewer than two live neighbors dies (underpopulation).
     * 2. Any live cell with two or three live neighbors survives.
     * 3. Any live cell with more than three live neighbors dies (overpopulation).
     * 4. Any dead cell with exactly three live neighbors becomes alive (reproduction).
     */
    @Override
    protected void applyRules() {
        int[][] newBoard = new int[state.length][state[0].length]; // Temporary grid for the next state.

        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                int neighbors = countLiveNeighbors(i, j); // Count the live neighbors of the cell.

                if (state[i][j] == 1) {
                    // Rule 1 and 2: A live cell survives with 2 or 3 live neighbors.
                    newBoard[i][j] = (neighbors == 2 || neighbors == 3) ? 1 : 0;
                } else {
                    // Rule 4: A dead cell becomes alive with exactly 3 live neighbors.
                    newBoard[i][j] = (neighbors == 3) ? 1 : 0;
                }
            }
        }

        state = newBoard; // Update the grid with the newly computed state.
    }

    /**
     * Counts the number of live neighbors around a specific cell.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @return The number of live neighbors.
     */
    private int countLiveNeighbors(int row, int col) {
        int count = 0;

        // Loop through the 3x3 grid surrounding the cell, excluding the cell itself.
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < state.length && j >= 0 && j < state[0].length && !(i == row && j == col)) {
                    count += state[i][j]; // Add the state of the neighbor (0 or 1).
                }
            }
        }

        return count;
    }

    /**
     * Prints the current state of the "Game of Life" grid.
     * Uses "+" to represent alive cells and "-" for dead cells.
     */
    @Override
    protected void printState() {
        for (int[] row : state) {
            for (int cell : row) {
                System.out.print((cell == 1) ? "+ " : "- "); // "+" for alive, "-" for dead.
            }
            System.out.println(); // Newline for the next row.
        }
        System.out.println(); // Extra newline for better readability between steps.
    }
}
