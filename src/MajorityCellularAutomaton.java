import java.util.Arrays;

/**
 * Class representing a cellular automaton based on the majority rule.
 * A cell's new state is determined by the majority state within its neighborhood.
 */
public class MajorityCellularAutomaton extends CellularAutomaton {

    private int neighborhoodSize; // The size of the neighborhood used for the majority rule.

    /**
     * Constructor for the {@code MajorityCellularAutomaton} class.
     *
     * @param rows             The number of rows in the grid.
     * @param cols             The number of columns in the grid.
     * @param neighborhoodSize The size of the neighborhood used for the majority rule.
     */
    public MajorityCellularAutomaton(int rows, int cols, int neighborhoodSize) {
        super(rows, cols); // Initialize the grid with the specified dimensions.
        this.neighborhoodSize = neighborhoodSize;
    }

    /**
     * Randomly initializes the state of the grid.
     * Each cell is randomly assigned a state of 0 or 1.
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
     * Evolves the cellular automaton over a specified number of steps.
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
     * Applies the majority rule to update the grid state.
     * A cell takes on the majority value within its neighborhood.
     */
    @Override
    protected void applyRules() {
        int[][] newState = new int[state.length][state[0].length]; // Temporary grid for the next state.

        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                int[] neighborhood = getNeighborhood(i, j); // Get the neighborhood of the current cell.
                newState[i][j] = applyMajorityRuleToCell(neighborhood); // Apply the majority rule.
            }
        }

        state = newState; // Update the grid with the new computed state.
    }

    /**
     * Retrieves the neighborhood of a cell at a given position.
     * The neighborhood includes the cells within a square region centered on the target cell.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @return An array representing the neighborhood states.
     */
    private int[] getNeighborhood(int row, int col) {
        int[] neighborhood = new int[neighborhoodSize * neighborhoodSize];
        int index = 0;

        // Loop through the square region surrounding the cell.
        for (int i = row - neighborhoodSize / 2; i <= row + neighborhoodSize / 2; i++) {
            for (int j = col - neighborhoodSize / 2; j <= col + neighborhoodSize / 2; j++) {
                if (i >= 0 && i < state.length && j >= 0 && j < state[0].length) {
                    neighborhood[index++] = state[i][j]; // Add the neighbor's state.
                } else {
                    neighborhood[index++] = 0; // Assume 0 for neighbors outside bounds.
                }
            }
        }

        return neighborhood;
    }

    /**
     * Applies the majority rule to determine a cell's new state based on its neighborhood.
     *
     * @param neighborhood The neighborhood states of the cell.
     * @return The new state of the cell (1 if the majority of neighbors are 1, otherwise 0).
     */
    private int applyMajorityRuleToCell(int[] neighborhood) {
        int sum = Arrays.stream(neighborhood).sum(); // Sum up the states in the neighborhood.
        // Return 1 if the majority are 1, otherwise return 0.
        return (sum > neighborhood.length / 2) ? 1 : 0;
    }

    /**
     * Prints the current state of the grid.
     * Uses "+" to represent cells with state 1 and "-" for cells with state 0.
     */
    @Override
    protected void printState() {
        for (int[] row : state) {
            for (int cell : row) {
                System.out.print((cell == 1) ? "+ " : "- "); // "+" for 1, "-" for 0.
            }
            System.out.println(); // Newline for the next row.
        }
        System.out.println(); // Extra newline for better readability between steps.
    }
}
