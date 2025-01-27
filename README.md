## Overview

This project is a comprehensive implementation of various cellular automata in Java, offering simulations for well-known automaton models such as:

    One-Dimensional Cellular Automaton
    Forest Fire Simulation
    Conway's Game of Life
    Majority Cellular Automaton

Each automaton follows its own set of rules to evolve over time. The project also provides a user-friendly menu-driven interface for selecting and running these automata.


## Features
Core Features:

    Menu Interface:
        Select from four predefined automata.
        Specify the number of evolution steps for the simulation.

    Supported Cellular Automata:
        1D Cellular Automaton:
            Simulates a one-dimensional grid of cells.
            Evolves based on a customizable binary rule.
        Forest Fire Automaton:
            Simulates the spread of fire in a forest.
            Includes configurable parameters such as tree density and fire ignition probability.
        Conway's Game of Life:
            A classic two-dimensional cellular automaton.
            Evolves based on the rules of survival, birth, and overpopulation.
        Majority Cellular Automaton:
            A grid where each cell adopts the majority state of its neighborhood.

    Random Initialization:
        The initial states of the automata are generated randomly with equal probabilities for each possible state.

    Customizable Parameters:
        Number of rows and columns in the grid.
        Specific rules (e.g., binary rules for the 1D automaton).
        Neighborhood size (for the Majority Cellular Automaton).
        Forest density and ignition probability (for the Forest Fire automaton).



## How It Works
Cellular Automata Concepts

Cellular automata are grid-based simulations where each cell has a state that evolves based on a set of rules. The rules consider the states of neighboring cells, and the evolution is computed step by step.
Key Components

    CellularAutomaton (Abstract Class):
        A base class defining the core structure of any cellular automaton.
        Contains methods for initializing, evolving, applying rules, and printing the grid.

    Derived Automata Classes:
        CellularAutomaton1D:
            Evolves a single row of cells based on binary rules.
            Includes methods for computing neighborhoods and applying rules.
        ForestFire:
            Simulates a forest grid with states: empty, tree, and burning.
            Includes probabilities for tree growth and ignition.
        GameOfLife:
            Implements Conway's Game of Life rules.
            Counts neighbors to determine the next state of each cell.
        MajorityCellularAutomaton:
            Uses the majority rule within a defined neighborhood size.
            Allows neighborhood size customization.

    CellularAutomatonMenu:
        A console-based interface for selecting and running automata.
        Prompts users to input simulation parameters such as the number of steps.



## Installation and Setup

    Prerequisites:
        Java Development Kit (JDK) version 8 or higher installed on your system.
        A text editor or IDE (e.g., IntelliJ IDEA, Eclipse, or Visual Studio Code).

    Steps:
        Clone the repository: git clone https://github.com/elyeselg/Cellular-Automata.git
        
    Navigate to the project directory: cd Cellular-Automata
    
    Compile the Java files: javac *.java
    
    Run the menu interface: java CellularAutomatonMenu


## Usage

    Run the Program:
        Launch the application by running the CellularAutomatonMenu class.
        Follow the prompts to select an automaton and specify parameters.

    Menu Options:
        1: Run the 1D Cellular Automaton.
        2: Simulate the Forest Fire Automaton.
        3: Play Conway's Game of Life.
        4: Simulate the Majority Cellular Automaton.

    Input Parameters:
        Number of steps: Determines how many iterations the simulation will perform.
        For certain automata, additional parameters are preconfigured in the code (e.g., rules for the 1D automaton).


## Extensibility

This project is designed to be easily extensible. To add a new automaton:

    Create a new class that extends CellularAutomaton.
    Implement the abstract methods:
        initializeRandomState()
        applyRules()
        printState()
    Add the new automaton to the CellularAutomatonMenu.

## Known Limitations

    The menu parameters for automata (e.g., grid size, rule definitions) are currently hardcoded and must be modified in the source code.
    Large grid sizes may result in slower simulations due to console rendering.

## License

This project is licensed under the MIT License. Feel free to use, modify, and distribute it.
