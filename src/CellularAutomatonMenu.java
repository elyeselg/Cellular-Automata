import java.util.Scanner;

/**
 * Class representing the main menu for selecting and executing different cellular automata.
 */
public class CellularAutomatonMenu {

    /**
     * Main method for the menu.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display the menu header
        System.out.println("********************************************");
        System.out.println("*       Welcome to the Automaton Menu      *");
        System.out.println("********************************************");
        System.out.println("Choose a cellular automaton:");
        System.out.println("1. Cellular Automaton 1D");
        System.out.println("2. Forest Fire");
        System.out.println("3. Game of Life");
        System.out.println("4. Majority Cellular Automaton");
        System.out.println("********************************************");

        int choice = getUserChoice(scanner); // Get the user's choice

        // Execute the selected automaton
        switch (choice) {
            case 1:
                // Example of running a 1D cellular automaton
                runAutomaton(new CellularAutomaton1D(30, choice, new int[]{1, 0, 1}), scanner);
                break;
            case 2:
                // Example of running the Forest Fire automaton
                runAutomaton(new ForestFire(10, 10, 0.5, 0.3), scanner);
                break;
            case 3:
                // Example of running the Game of Life automaton
                runAutomaton(new GameOfLife(10, 10), scanner);
                break;
            case 4:
                // Example of running the Majority Cellular Automaton
                runAutomaton(new MajorityCellularAutomaton(10, 10, 3), scanner);
                break;
            default:
                System.out.println("Invalid choice."); // Handle invalid input
        }

        scanner.close(); // Close the scanner
    }

    /**
     * Prompts the user to enter their choice from the menu.
     *
     * @param scanner The scanner used for user input.
     * @return The user's choice as an integer.
     */
    private static int getUserChoice(Scanner scanner) {
        System.out.print("Enter your choice: ");
        while (!scanner.hasNextInt()) { // Check if the input is a valid integer
            System.out.println("Invalid input. Please enter a number.");
            System.out.print("Enter your choice: ");
            scanner.next(); // Consume the invalid input
        }
        return scanner.nextInt(); // Return the valid input
    }

    /**
     * Executes the selected cellular automaton.
     *
     * @param automaton The cellular automaton to execute.
     * @param scanner   The scanner used for user input.
     */
    private static void runAutomaton(CellularAutomaton automaton, Scanner scanner) {
        // Display the header for the selected automaton
        System.out.println("********************************************");
        System.out.println("*        " + automaton.getClass().getSimpleName() + "             *");
        System.out.println("********************************************");

        automaton.initializeRandomState(); // Randomly initialize the automaton's state

        // Prompt the user for the number of steps to evolve the automaton
        System.out.print("Enter the number of steps: ");
        int numSteps = scanner.nextInt();

        automaton.evolve(numSteps); // Evolve the automaton for the specified number of steps

        // Display the footer
        System.out.println("********************************************");
    }
}
