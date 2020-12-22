package ua.vedroid.elevator;

import java.util.List;
import java.util.Scanner;

public class ApplicationStarter {
    private static boolean print;
    
    public static void main(String[] args) {
        FloorService floorService = new FloorService();
        List<Floor> floors = floorService.generateFloors();
        
        System.out.println("Do you wanna see the queues at the each floor? Y - yes, N - no");
        Scanner scanner = new Scanner(System.in);
        print = scanner.nextLine().equalsIgnoreCase("Y");
        scanner.close();
        if (print) {
            floorService.printPassengersOnFloors(floors);
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        new Elevator(floors).start();
        if (print) {
            floorService.printPassengersOnFloors(floors);
        }
    }
    
    public static boolean print() {
        return print;
    }
}
