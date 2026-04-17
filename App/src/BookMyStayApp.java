import java.util.*;

/**
 * Book My Stay - Hotel Booking Management System
 *
 * Use Case 8: Booking History & Reporting
 */
public class BookMyStayApp {

    // Reservation class (same structure as earlier use cases)
    static class Reservation {
        String guestName;
        String roomType;

        Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }
    }

    // Booking History (List preserves insertion order)
    private List<Reservation> bookingHistory = new ArrayList<>();

    /**
     * Add confirmed booking to history
     */
    public void addToHistory(String guestName, String roomType) {
        bookingHistory.add(new Reservation(guestName, roomType));
    }

    /**
     * Generate booking report
     */
    public void generateReport() {
        System.out.println("Booking History and Reporting\n");
        System.out.println("Booking History Report");

        for (Reservation r : bookingHistory) {
            System.out.println("Guest: " + r.guestName + ", Room Type: " + r.roomType);
        }
    }

    public static void main(String[] args) {

        BookMyStayApp system = new BookMyStayApp();

        // Simulating confirmed bookings (from previous use cases)
        system.addToHistory("Abhi", "Single");
        system.addToHistory("Subha", "Double");
        system.addToHistory("Vanmathi", "Suite");

        // Generate report
        system.generateReport();
    }
}