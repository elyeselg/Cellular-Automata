/**
 * Class representing a cellular automaton simulating a forest fire.
 */
public class ForestFire extends CellularAutomaton {
    private static final int EMPTY = 0;     // State representing an empty cell.
    private static final int TREE = 1;     // State representing a tree.
    private static final int BURNING = 2;  // State representing a burning tree.

    private double treeDensity;            // Probability of a cell being initialized as a tree.
    private double ignitionProbability;    // Probability of a tree catching fire spontaneously.

    /**
     * Constructor for the {@code ForestFire} class.
     *
     * @param rows                Number of rows in the forest grid.
     * @param cols                Number of columns in the forest grid.
     * @param treeDensity         Density of trees in the forest (0 to 1).
     * @param ignitionProbability Probability of a tree spontaneously catching fire (0 to 1).
     */
    public ForestFire(int rows, int cols, double treeDensity, double ignitionProbability) {
        super(rows, cols);
        this.treeDensity = treeDensity;
        this.ignitionProbability = ignitionProbability;
    }

    /**
     * Randomly initializes the forest grid based on the specified tree density.
     * Each cell has a probability of being a tree determined by {@code treeDensity}.
     */
    @Override
    public void initializeRandomState() {
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                state[i][j] = (Math.random() < treeDensity) ? TREE : EMPTY;
            }
        }
    }

    /**
     * Evolves the forest fire simulation over a specified number of steps.
     *
     * @param numSteps The number of steps to evolve the simulation.
     */
    @Override
    public void evolve(int numSteps) {
        for (int step = 0; step < numSteps; step++) {
            System.out.println("Step " + (step + 1)); // Display the current step number.
            printState(); // Print the current state of the forest.
            applyRules(); // Apply the forest fire rules to compute the next state.
        }
    }

    /**
     * Applies the specific rules of the forest fire simulation to update the state of the forest.
     */
    @Override
    protected void applyRules() {
        int[][] newForest = new int[state.length][state[0].length]; // Temporary grid for the next state.

        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                if (state[i][j] == BURNING) {
                    newForest[i][j] = EMPTY; // A burning tree becomes empty after burning out.
                } else if (state[i][j] == TREE) {
                    if (hasBurningNeighbor(i, j) || Math.random() < ignitionProbability) {
                        newForest[i][j] = BURNING; // A tree catches fire if a neighbor is burning or spontaneously ignites.
                    } else {
                        newForest[i][j] = TREE; // A tree remains unchanged.
                    }
                } else {
                    newForest[i][j] = EMPTY; // An empty cell remains unchanged.
                }
            }
        }

        state = newForest; // Update the forest state with the new computed state.
    }

    /**
     * Checks if a cell has any burning neighbors.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @return {@code true} if a burning neighbor is found; otherwise, {@code false}.
     */
    private boolean hasBurningNeighbor(int row, int col) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < state.length && j >= 0 && j < state[0].length && !(i == row && j == col)) {
                    if (state[i][j] == BURNING) {
                        return true; // Return true if a burning neighbor is found.
                    }
                }
            }
        }
        return false; // No burning neighbors found.
    }

    /**
     * Prints the current state of the forest fire simulation.
     * Different characters represent different cell states:
     * - {@code "="} for empty cells.
     * - {@code "+"} for trees.
     * - {@code "!"} for burning trees.
     */
    @Override
    protected void printState() {
        for (int[] row : state) {
            for (int cell : row) {
                if (cell == EMPTY) {
                    System.out.print("= "); // Empty cell
                } else if (cell == TREE) {
                    System.out.print("+ "); // Tree
                } else {
                    System.out.print("! "); // Burning tree
                }
            }
            System.out.println(); // Newline for the next row.
        }
        System.out.println(); // Extra newline for better readability between steps.
    }
}
