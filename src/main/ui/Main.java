package ui;

import model.Event;
import model.EventLog;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new SkiResortApp();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Event log:");
                EventLog eventLog = EventLog.getInstance();

                for (Event event : eventLog) {
                    System.out.println(event.toString());
                }
            }));
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
