package ua.vedroid.elevator;

import static ua.vedroid.elevator.Direction.DOWN;
import static ua.vedroid.elevator.Direction.UP;

import java.util.LinkedList;
import java.util.List;

public class Elevator {
    private static final int MAX_CAPACITY = 5;
    private static final int GROUND_FLOOR = 0;
    
    private List<Passenger> passengers;
    private List<Floor> floors;
    private Floor currentFloor;
    private Direction direction;
    private int stopCounter;
    
    public Elevator(List<Floor> floors) {
        passengers = new LinkedList<>();
        this.floors = floors;
        currentFloor = floors.get(GROUND_FLOOR);
        direction = UP;
    }
    
    public void start() {
        while (!floorsIsEmpty() || !passengers.isEmpty()) {
            shouldStopAtTheFloor();
            nextFloor();
        }
    }
    
    private void shouldStopAtTheFloor() {
        if (passengersWantToGoOut(currentFloor.getNumber())
                || (passengers.size() < MAX_CAPACITY && (!currentFloor.isEmpty()
                && ((direction == UP && currentFloor.upButtonPressed())
                || (direction == DOWN && currentFloor.downButtonPressed()))))) {
            stopAtFloor();
        } else {
            System.out.println("'" + direction + "' ");
        }
    }
    
    private void stopAtFloor() {
        System.out.print(currentFloor.getNumber() + " floor! " + passengers);
        stopCounter++;
        dropOffPassengers();
        addPassengers();
        System.out.println(" -> " + passengers + "  In floor: " + currentFloor.getPassengers());
    }
    
    private void dropOffPassengers() {
        passengers.removeIf(passenger ->
                passenger.getRequiredFloor() == currentFloor.getNumber());
    }
    
    private void addPassengers() {
        List<Passenger> passengersInFloor = new LinkedList<>(currentFloor.getPassengers());
        for (Passenger passenger : passengersInFloor) {
            if (passengers.size() >= MAX_CAPACITY || currentFloor.isEmpty()) {
                return;
            }
            if ((direction.equals(UP)
                    && currentFloor.getNumber() < passenger.getRequiredFloor())
                    || (direction.equals(DOWN)
                    && currentFloor.getNumber() > passenger.getRequiredFloor())) {
                passengers.add(passenger);
                currentFloor.removePassenger(passenger);
            }
        }
    }
    
    private void nextFloor() {
        if (direction == UP) {
            up();
            if (currentFloor.getNumber() == floors.size() - 1) {
                changeDirection();
            }
        } else {
            down();
            if (currentFloor.getNumber() == 0) {
                changeDirection();
            }
        }
    }
    
    private void up() {
        currentFloor = floors.get(currentFloor.getNumber() + 1);
    }
    
    private void down() {
        currentFloor = floors.get(currentFloor.getNumber() - 1);
    }
    
    private void changeDirection() {
        direction = direction == UP ? DOWN : UP;
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
    
    public int getStopCounter() {
        return stopCounter;
    }
}
