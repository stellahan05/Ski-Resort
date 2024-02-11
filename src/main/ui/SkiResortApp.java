package ui;

import model.Trail;
import model.Trails;

import java.util.Scanner;


// Ski resort application for admin to monitor trails
public class SkiResortApp {
    private final Trails trails;
    private final Scanner input;

    // EFFECTS: runs the ski resort application
    public SkiResortApp() {
        trails = new Trails();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        run();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void run() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nExiting Ski Resort Management System. Goodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addTrail();
        } else if (command.equals("v")) {
            viewTrails();
        } else if (command.equals("s")) {
            setTrailStatus();
        } else if (command.equals("r")) {
            removeTrail();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add trail");
        System.out.println("\tv -> view trails");
        System.out.println("\ts -> set trail status as");
        System.out.println("\tr -> remove trail");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: conducts addition of trail
    private void addTrail() {
        System.out.println("Enter trail name:");
        String name = input.next();
        String difficulty = "";

        while (!(difficulty.equals("e") || difficulty.equals("i") || difficulty.equalsIgnoreCase("a"))) {
            System.out.println("\nEnter difficulty level:");
            System.out.println("\ne for easy");
            System.out.println("\ni for intermediate");
            System.out.println("\na for advanced");
            difficulty = input.next();
            difficulty = difficulty.toLowerCase();
        }

        trails.addTrail(new Trail(name, difficulty));
        System.out.println("\nTrail added successfully.");
    }


    // EFFECTS: displays a list of all trails in the resort
    private void viewTrails() {
        if (0 == trails.numOfTrails()) {
            System.out.println("There are no trails in the resort!");
        } else {
            System.out.println("\nAll trails: ");
            for (String trail : trails.getAllTrailNames()) {
                System.out.println("- " + trail);
            }
            System.out.println("Select a trail to view it in detail:");
            String name = input.next();
            if (null == trails.findTrail(name)) {
                System.out.println("Trail not found.");
            } else {
                Trail trail = trails.findTrail(name);
                System.out.println(trail.toString());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to select a trail and desired status, and sets it accordingly
    private void setTrailStatus() {
        System.out.println("Enter trail name: ");
        String name = input.next();
        if (null == trails.findTrail(name)) {
            System.out.println("Trail not found.");
        } else {
            Trail trail = trails.findTrail(name);
            String status = "";

            while (!(status.equals("o") || status.equals("c"))) {
                System.out.println("o for open");
                System.out.println("c for closed");
                status = input.next();
                status = status.toLowerCase();
            }

            if (status.equals("o")) {
                trail.setStatus(true);
            } else {
                trail.setStatus(false);
            }
            System.out.println("Status of " + name + " set as " + (status.equals("o") ? "open." : "closed."));
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts the removal of a trail
    private void removeTrail() {
        System.out.println("Enter name of the trail to remove");
        String name = input.next();

        if (null == trails.findTrail(name)) {
            System.out.println("That trail does not exist...");
        } else {
            Trail trailToRemove = trails.findTrail(name);
            trails.removeTrail(trailToRemove);
            System.out.println(name + " successfully removed.");
        }
    }
}
