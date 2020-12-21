package ua.vedroid.elevator;

import java.util.List;

public class Floor {
    private final int floorNumber;
    private final List<Passenger> passengers;
    private boolean upButton;
    private boolean downButton;
    
    public Floor(int floorNumber, List<Passenger> passengers) {
        this.floorNumber = floorNumber;
        this.passengers = passengers;
        
        passengers.removeIf(passenger -> passenger.getRequiredFloor() == floorNumber);
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
    
    public Passenger getNextPassenger() {
        if (passengers.isEmpty()) {
            return null;
        }
        Passenger passenger = passengers.get(passengers.size() - 1);
        passengers.remove(passengers.size() - 1);
        updateButtons();
        return passenger;
    }
    
    public int getFloorNumber() {
        return floorNumber;
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
