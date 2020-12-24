package ua.vedroid.elevator;

import java.util.List;

public class Floor {
    private final int number;
    //private LinkedList<Passenger> passengers;
    private List<Passenger> passengers;
    private boolean upButton;
    private boolean downButton;
    
    public Floor(int number, List<Passenger> passengers) {
        this.number = number;
        this.passengers = passengers;
        
        this.passengers.removeIf(passenger -> passenger.getRequiredFloor() == number);
        //this.passengers.sort(Comparator.comparingInt(Passenger::getRequiredFloor));
        updateButtons();
    }
    
    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
        updateButtons();
    }
    
    private void updateButtons() {
        upButton = false;
        downButton = false;
        for (Passenger passenger : passengers) {
            if (passenger.getRequiredFloor() > number) {
                upButton = true;
            }
            if (passenger.getRequiredFloor() < number) {
                downButton = true;
            }
        }
    }
    
    public int getNumber() {
        return number;
    }
    
    public List<Passenger> getPassengers() {
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
        return "#" + number + " " + passengers;
    }
}
