package ui;

import model.Review;
import model.Trail;
import model.Trails;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


// Ski resort application for admin to monitor trails
public class SkiResortApp {
    private Trails trails;
    private final Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/skiResort.json";

    // EFFECTS: runs the ski resort application
    public SkiResortApp() throws FileNotFoundException {
        trails = new Trails();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
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
        } else if (command.equals("w")) {
            writeReview();
        } else if (command.equals("k")) {
            saveTrails();
        } else if (command.equals("l")) {
            loadTrails();
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
        System.out.println("\tw -> write a review");
        System.out.println("\tk -> save trails to file");
        System.out.println("\tl -> load trails from file");
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
        if (difficulty.equals("e")) {
            difficulty = "Easy";
        } else if (difficulty.equals("i")) {
            difficulty = "Intermediate";
        } else {
            difficulty = "Advanced";
        }
        trails.addTrail(new Trail(name, difficulty));
        System.out.println("\nTrail added successfully.");
    }

    // MODIFIES: this
    // EFFECTS: displays a list of all trails in the resort
    private void viewTrails() {
        if (0 == trails.numOfTrails()) {
            System.out.println("There are no trails in the resort!");
        } else {
            System.out.println("\nAll trails: ");
            for (String trail : trails.getAllTrailNames()) {
                System.out.println("- " + trail);
            }
            viewTrailDetails();
        }
    }

    // MODIFIES: this
    // EFFECTS: displays detailed view of trail
    private void viewTrailDetails() {
        System.out.println("Select a trail to view it in detail:");
        String name = input.next();
        if (null == trails.findTrail(name)) {
            System.out.println("Trail not found.");
        } else {
            Trail trail = trails.findTrail(name);
            System.out.println(trail.toString());
            viewTrailReviews(trail);
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to view the reviews of selected trail
    private void viewTrailReviews(Trail trail) {
        System.out.println("Would you like to view this trail's reviews?");
        String choice = "";
        while (!(choice.equals("y") || choice.equals("n"))) {
            System.out.println("Enter 'y' for yes, 'n' for no: ");
            choice = input.next().toLowerCase();
        }
        if (choice.equals("y")) {
            if (trail.getReviews().isEmpty()) {
                System.out.println("There are no reviews yet!");
            } else {
                for (Review review : trail.getReviews()) {
                    System.out.println("- " + review.toString());
                }
            }
        } else {
            System.out.println("Closing trail details now.");
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

            trail.setStatus(status.equals("o"));

            System.out.println("Status of " + name + " set as " + (status.equals("o") ? "open." : "closed."));
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts the removal of a trail
    private void removeTrail() {
        System.out.println("Enter name of the trail to remove");
        String name = input.next();

        if (null == trails.findTrail(name)) {
            System.out.println("Trail not found.");
        } else {
            Trail trailToRemove = trails.findTrail(name);
            trails.removeTrail(trailToRemove);
            System.out.println(name + " successfully removed.");
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts writing a review of a trail
    private void writeReview() {
        System.out.println("Enter trail name: ");
        String name = input.next();

        if (null == trails.findTrail(name)) {
            System.out.println("Trail not found.");
        } else {
            Trail trailToReview = trails.findTrail(name);
            System.out.println("Enter your review: ");
            String description = input.next();
            System.out.println("Enter your name: ");
            String author = input.next();

            int rating = 0;
            while (! ((0 < rating) && (rating < 6))) {
                System.out.println("Enter you rating from 1-5 (1 = would not recommend, 5 = highly recommend): ");
                rating = input.nextInt();
            }

            Review review = new Review(rating, description, author);
            trailToReview.addReview(review);
            System.out.println("Review added successfully.");
        }
    }

    // EFFECTS: saves the entire ski resort to file
    private void saveTrails() {
        try {
            jsonWriter.open();
            jsonWriter.write(trails);
            jsonWriter.close();
            System.out.println("Saved all trails to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads trails from file
    private void loadTrails() {
        try {
            trails = jsonReader.read();
            System.out.println("Loaded ski resort from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}