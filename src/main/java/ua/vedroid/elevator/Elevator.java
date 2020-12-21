package ua.vedroid.elevator;

import static ua.vedroid.elevator.Direction.DOWN;
import static ua.vedroid.elevator.Direction.UP;

import java.util.LinkedList;
import java.util.List;

public class Elevator {
    private static final int MAX_CAPACITY = 5;
    private static final int GROUND_FLOOR = 0;
    
    private final List<Passenger> passengers;
    private final List<Floor> floors;
    private Floor currentFloor;
    private Direction direction;
    
    public Elevator(List<Floor> floors) {
        passengers = new LinkedList<>();
        this.floors = floors;
        currentFloor = floors.get(GROUND_FLOOR);
        direction = UP;
    }
    
    public void start() {
        while (!floorsIsEmpty()) {
            if (passengers.size() < MAX_CAPACITY
                    || passengersWantToGoOut(currentFloor.getFloorNumber())
                    || (direction == UP && currentFloor.upButtonPressed())
                    || (direction == DOWN && currentFloor.downButtonPressed())) {
                stopAtFloor();
            } else {
                System.out.println("'" + direction + "' ");
            }
            nextFloor();
        }
        ApplicationStarter.printPassengersOnFloors(floors);
    }
    
    private boolean passengersWantToGoOut(int floorNumber) {
        for (Passenger passenger : passengers) {
            if (passenger.getRequiredFloor() == floorNumber) {
                return true;
            }
        }
        return false;
    }
    
    private boolean floorsIsEmpty() {
        for (Floor floor : floors) {
            if (!floor.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    private void stopAtFloor() {
        System.out.println(currentFloor.getFloorNumber() + " floor!");
        dropOffPassengers();
        addPassengers();
    }
    
    private void dropOffPassengers() {
        passengers.removeIf(passenger -> passenger.getRequiredFloor() == currentFloor.getFloorNumber());
    }
    
    private void addPassengers() {
        Passenger passenger = currentFloor.getNextPassenger();
        if (passenger == null) {
            return;
        }
        int requiredFloorNumber = passenger.getRequiredFloor();
        int currentFloorNumber = currentFloor.getFloorNumber();
        while (passengers.size() < MAX_CAPACITY) {
            if ((direction.equals(UP) && currentFloorNumber < requiredFloorNumber)
                    || (direction.equals(DOWN) && currentFloorNumber > requiredFloorNumber)) {
                passengers.add(passenger);
            }
        }
    }
    
    private void nextFloor() {
        if (direction == UP) {
            if (currentFloor.getFloorNumber() == floors.size() - 1) {
                changeDirection();
                down();
            } else {
                up();
            }
        } else {
            if (currentFloor.getFloorNumber() == 0) {
                changeDirection();
                up();
            } else {
                down();
            }
        }
    }
    
    private void up() {
        currentFloor = floors.get(currentFloor.getFloorNumber() + 1);
    }
    
    private void down() {
        currentFloor = floors.get(currentFloor.getFloorNumber() - 1);
    }
    
    private void changeDirection() {
        direction = direction == UP ? DOWN : UP;
    }
}
