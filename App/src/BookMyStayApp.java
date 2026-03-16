import java.util.LinkedList;
import java.util.Queue;

/**
 * Book My Stay - Hotel Booking Management System
 *
 * Use Case 5: Booking Request (First-Come-First-Served)
 *
 * This program demonstrates how booking requests are collected
 * and stored in a queue structure to preserve arrival order.
 * No room allocation or inventory updates happen at this stage.
 *
 * @author Student
 * @version 5.0
 */
public class BookMyStayApp {

    /**
     * Reservation represents a guest booking request
     */
    static class Reservation {
        String guestName;
        String roomType;

        Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public String toString() {
            return "Guest: " + guestName + " | Room Type: " + roomType;
        }
    }

    // Booking request queue (FIFO)
    private Queue<Reservation> bookingQueue = new LinkedList<>();

    /**
     * Add a booking request to the queue
     */
    public void addBookingRequest(String guestName, String roomType) {
        Reservation reservation = new Reservation(guestName, roomType);
        bookingQueue.add(reservation);
        System.out.println("Booking request received from " + guestName + " for " + roomType + " room.");
    }

    /**
     * Display all pending booking requests
     */
    public void displayBookingQueue() {
        System.out.println("\nPending Booking Requests (FIFO Order):");

        for (Reservation r : bookingQueue) {
            System.out.println(r);
        }
    }

    /**
     * Application entry point
     */
    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("Book My Stay - Booking Request Queue");
        System.out.println("Version 5.0");
        System.out.println("=====================================");

        BookMyStayApp system = new BookMyStayApp();

        // Simulate booking requests
        system.addBookingRequest("Alice", "Single");
        system.addBookingRequest("Bob", "Double");
        system.addBookingRequest("Charlie", "Suite");

        // Display queued requests
        system.displayBookingQueue();
    }
}