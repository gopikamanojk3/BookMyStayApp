import java.util.*;

/**
 * Book My Stay - Hotel Booking Management System
 *
 * Use Case 7: Add-On Service Selection
 */
public class BookMyStayApp {

    // Add-On Service class
    static class Service {
        String name;
        double cost;

        Service(String name, double cost) {
            this.name = name;
            this.cost = cost;
        }
    }

    // Map: Reservation ID → List of Services
    private Map<String, List<Service>> serviceMap = new HashMap<>();

    /**
     * Add service to reservation
     */
    public void addService(String reservationId, String serviceName, double cost) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(new Service(serviceName, cost));
    }

    /**
     * Calculate total add-on cost
     */
    public double calculateTotalCost(String reservationId) {
        double total = 0;

        List<Service> services = serviceMap.get(reservationId);

        if (services != null) {
            for (Service s : services) {
                total += s.cost;
            }
        }

        return total;
    }

    /**
     * Display result
     */
    public void displayServices(String reservationId) {
        System.out.println("Add-On Service Selection");
        System.out.println("Reservation ID: " + reservationId);

        double total = calculateTotalCost(reservationId);
        System.out.println("Total Add-On Cost: " + total);
    }

    public static void main(String[] args) {

        BookMyStayApp system = new BookMyStayApp();

        // Example reservation from previous use case
        String reservationId = "Single-1";

        // Add services
        system.addService(reservationId, "Breakfast", 500);
        system.addService(reservationId, "Spa", 1000);

        // Display output
        system.displayServices(reservationId);
    }
}