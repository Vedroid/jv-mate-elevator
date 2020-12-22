package ua.vedroid.elevator;

import java.util.Comparator;
import java.util.LinkedList;

public class Floor {
    private final int floorNumber;
    private LinkedList<Passenger> passengers;
    private boolean upButton;
    private boolean downButton;
    
    public Floor(int floorNumber, LinkedList<Passenger> passengers) {
        this.floorNumber = floorNumber;
        this.passengers = passengers;
        
        this.passengers.removeIf(passenger -> passenger.getRequiredFloor() == floorNumber);
        this.passengers.sort(Comparator.comparingInt(Passenger::getRequiredFloor));
        updateButtons();
    }
    
    private void updateButtons() {
        upButton = false;
        downButton = false;
        for (Passenger passenger : passengers) {
            if (passenger.getRequiredFloor() > floorNumber) {
                upButton = true;
            }
            if (passenger.getRequiredFloor() < floorNumber) {
                downButton = true;
            }
        }
    }
    
    public Passenger getPassengerFromTheBeginning() {
        Passenger passenger = passengers.pollFirst();
        updateButtons();
        return passenger;
    }
    
    public Passenger getPassengerFromTheEnd() {
        Passenger passenger = passengers.pollLast();
        updateButtons();
        return passenger;
    }
    
    public Passenger checkPassengerFromTheBeginning() {
        if (passengers.isEmpty()) {
            return null;
        }
        return passengers.peekFirst();
    }
    
    public Passenger checkPassengerFromTheEnd() {
        if (passengers.isEmpty()) {
            return null;
        }
        return passengers.peekLast();
    }
    
    public int getFloorNumber() {
        return floorNumber;
    }
    
    public LinkedList<Passenger> getPassengers() {
        return passengers;
    }
    
    public boolean upButtonPressed() {
        return upButton;
    }
    
    public boolean downButtonPressed() {
        return downButton;
    }
    
    public boolean isEmpty() {
        return passengers.isEmpty();
    }
    
    @Override
    public String toString() {
        return "#" + floorNumber + " " + passengers;
    }
}
