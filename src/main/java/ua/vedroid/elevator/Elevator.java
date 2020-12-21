package ua.vedroid.elevator;

import static ua.vedroid.elevator.Direction.DOWN;
import static ua.vedroid.elevator.Direction.UP;

import java.util.LinkedList;
import java.util.List;

public class Elevator {
    public static final int GROUND_FLOOR = 0;
    private static final int MAX_CAPACITY = 5;
    
    private List<Passenger> passengers;
    private List<Floor> floors;
    private Floor currentFloor;
    private Direction direction;
    
    public Elevator(List<Floor> floors) {
        passengers = new LinkedList<>();
        this.floors = floors;
        currentFloor = floors.get(GROUND_FLOOR);
        direction = UP;
    }
    
    public void start() {
    
    }
    
    private boolean floorsIsEmpty() {
        return true;
    }
    
    private void stopAtFloor() {
        dropOffPassengers();
        addPassengers();
    }
    
    private void dropOffPassengers() {
        passengers.removeIf(passenger ->
                passenger.getRequiredFloor() == currentFloor.getFloorNumber());
    }
    
    private void addPassengers() {
        Passenger passenger = currentFloor.getNextPassenger();
        if (passenger == null) {
            return;
        }

    }
    
    private void nextFloor() {
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
