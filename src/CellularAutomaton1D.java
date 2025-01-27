import java.util.Arrays;

/**
 * Class representing a one-dimensional cellular automaton.
 * This automaton evolves over time according to specific rules applied
 * to each cell based on the states of its immediate neighbors.
 */
public class CellularAutomaton1D extends CellularAutomaton {

    /**
     * The specific rule used to determine the evolution of the cells.
     * The rule is an integer representing the binary combination of the states of neighbors.
     */
    private int rule;

    /**
     * Constructor for the {@code CellularAutomaton1D} class.
     *
     * @param cols         The number of columns (cells) in the one-dimensional automaton.
     * @param rule         The rule used to determine the evolution of cells.
     * @param initialState The initial state of the one-dimensional cellular automaton.
     */
    public CellularAutomaton1D(int cols, int rule, int[] initialState) {
        super(1, cols); // Initialize a 1D automaton with 1 row and specified number of columns.
        this.rule = rule;
        this.state[0] = Arrays.copyOf(initialState, initialState.length); // Set the initial state.
    }

    /**
     * Randomly initializes the state of the one-dimensional cellular automaton.
     * Each cell is randomly set to either 0 or 1 with equal probability.
     */
    @Override
    public void initializeRandomState() {
        for (int i = 0; i < state[0].length; i++) {
            state[0][i] = (Math.random() < 0.5) ? 0 : 1; // Randomly assign 0 or 1.
        }
    }

    /**
     * Evolves the one-dimensional cellular automaton over a specified number of steps.
     *
     * @param numSteps The number of steps to evolve the automaton.
     */
    @Override
    public void evolve(int numSteps) {
        for (int step = 0; step < numSteps; step++) {
            System.out.println(Arrays.toString(state[0])); // Print the current state.
            applyRules(); // Apply the evolution rules to compute the next state.
        }
    }

    /**
     * Applies the specific rules of the one-dimensional cellular automaton
     * to evolve the state of the cells.
     */
    @Override
    protected void applyRules() {
        int[] newState = Arrays.copyOf(state[0], state[0].length); // Copy current state for updates.

        for (int i = 0; i < state[0].length; i++) {
            int[] neighborhood = getNeighborhood(i); // Get the neighborhood of the current cell.
            newState[i] = applyRuleToCell(neighborhood); // Apply the rule to determine the next state.
        }

        state[0] = newState; // Update the state with the new computed state.
    }

    /**
     * Prints the current state of the one-dimensional cellular automaton.
     */
    @Override
    protected void printState() {
        System.out.println(Arrays.toString(state[0])); // Print the state as an array.
    }

    /**
     * Applies the specified rule to a cell based on its neighborhood.
     *
     * @param neighborhood The neighborhood of the cell (e.g., left, center, right states).
     * @return The new state of the cell after applying the rule.
     */
    private int applyRuleToCell(int[] neighborhood) {
        // Compute the binary index based on the neighborhood's states.
        int index = (4 * neighborhood[0]) + (2 * neighborhood[1]) + neighborhood[2];
        // Apply the rule by shifting the rule's bits and extracting the resulting state.
        return (rule >> index) & 1;
    }

    /**
     * Retrieves the neighborhood of a cell at a given position.
     *
     * @param index The position of the cell.
     * @return An array representing the neighborhood of the cell (left, center, right states).
     */
    private int[] getNeighborhood(int index) {
        int[] neighborhood = new int[3];

        for (int i = 0; i < 3; i++) {
            int neighborIndex = index - 1 + i; // Compute the neighbor's index.
            if (neighborIndex >= 0 && neighborIndex < state[0].length) {
                neighborhood[i] = state[0][neighborIndex]; // Valid neighbor state.
            } else {
                neighborhood[i] = 0; // Assume 0 for neighbors outside bounds.
            }
        }

        return neighborhood;
    }
}
