package ua.vedroid.elevator;

import static ua.vedroid.elevator.ApplicationStarter.print;
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
    
    public Elevator(List<Floor> floors) {
        passengers = new LinkedList<>();
        this.floors = floors;
        currentFloor = floors.get(GROUND_FLOOR);
        direction = UP;
    }
    
    public void start() {
        while (!floorsIsEmpty()) {
            if (passengersWantToGoOut(currentFloor.getFloorNumber())
                    || (passengers.size() < MAX_CAPACITY && (!currentFloor.isEmpty()
                    || (direction == UP && currentFloor.upButtonPressed())
                    || (direction == DOWN && currentFloor.downButtonPressed())))) {
                stopAtFloor();
            } else {
                if (print()) {
                    System.out.println("'" + direction + "' ");
                }
            }
            nextFloor();
        }
    }
    
    private void stopAtFloor() {
        if (print()) {
            System.out.print(currentFloor.getFloorNumber() + " floor! " + passengers);
        }
        dropOffPassengers();
        addPassengers();
        if (print()) {
            System.out.println(" -> " + passengers + "  In floor: " + currentFloor.getPassengers());
        }
    }
    
    private void dropOffPassengers() {
        passengers.removeIf(passenger ->
                passenger.getRequiredFloor() == currentFloor.getFloorNumber());
    }
    
    private void addPassengers() {
        while (true) {
            if (passengers.size() >= MAX_CAPACITY || currentFloor.isEmpty()) {
                return;
            }
            if (direction.equals(UP)) {
                Passenger passenger = currentFloor.checkPassengerFromTheEnd();
                if (passenger == null) {
                    return;
                }
                if (currentFloor.getFloorNumber() < passenger.getRequiredFloor()) {
                    passengers.add(currentFloor.getPassengerFromTheEnd());
                } else {
                    return;
                }
            } else if (direction.equals(DOWN)) {
                Passenger passenger = currentFloor.checkPassengerFromTheBeginning();
                if (passenger == null) {
                    return;
                }
                if (currentFloor.getFloorNumber() > passenger.getRequiredFloor()) {
                    passengers.add(currentFloor.getPassengerFromTheBeginning());
                } else {
                    return;
                }
            }
        }
    }
    
    private void nextFloor() {
        if (direction == UP) {
            up();
            if (currentFloor.getFloorNumber() == floors.size() - 1) {
                changeDirection();
            }
        } else {
            down();
            if (currentFloor.getFloorNumber() == 0) {
                changeDirection();
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
}
