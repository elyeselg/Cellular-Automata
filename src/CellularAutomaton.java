/**
 * Abstract class representing a cellular automaton.
 * Cellular automata are discrete models defined on a grid of cells,
 * where the state of each cell evolves over time based on specific rules.
 */
public abstract class CellularAutomaton {

    /**
     * 2D array representing the state of the cellular automaton.
     * Each element in the array corresponds to the state of an individual cell.
     * The dimensions of the array are rows x columns.
     */
    protected int[][] state;

    /**
     * Initializes a new instance of the {@code CellularAutomaton} class
     * with the specified number of rows and columns.
     *
     * @param rows The number of rows in the cellular automaton grid.
     * @param cols The number of columns in the cellular automaton grid.
     */
    public CellularAutomaton(int rows, int cols) {
        this.state = new int[rows][cols]; // Initialize the grid with the specified dimensions.
    }

    /**
     * Abstract method to randomly initialize the state of the cellular automaton.
     * Subclasses must implement this method to define how the cells' states
     * are randomly set (e.g., binary states, custom probabilities, etc.).
     */
    public abstract void initializeRandomState();

    /**
     * Abstract method to evolve the cellular automaton over a specified number of steps.
     * Subclasses must implement this method to define the specific rules
     * that dictate the evolution of the automaton's state over time.
     *
     * @param numSteps The number of steps to evolve the cellular automaton.
     */
    public abstract void evolve(int numSteps);

    /**
     * Abstract protected method to apply the rules of the cellular automaton.
     * Subclasses must implement this method to define the logic for updating
     * the cells' states according to the automaton's rules.
     */
    protected abstract void applyRules();

    /**
     * Abstract protected method to display the current state of the cellular automaton.
     * Subclasses must implement this method to define how the state is visualized
     * (e.g., console output, graphical interface, etc.).
     */
    protected abstract void printState();
}
