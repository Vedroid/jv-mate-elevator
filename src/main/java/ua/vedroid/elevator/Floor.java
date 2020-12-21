package ua.vedroid.elevator;

import java.util.List;

public class Floor {
    private final int floorNumber;
    private List<Passenger> passengers;
    
    public Floor(int floorNumber, List<Passenger> passengers) {
        this.floorNumber = floorNumber;
        this.passengers = passengers;
    }
    
    public Passenger getNextPassenger() {
        if (passengers.isEmpty()) {
            return null;
        }
        Passenger passenger = passengers.get(passengers.size() - 1);
        passengers.remove(passengers.size() - 1);
        return passenger;
    }
    
    public int getPassengersCount() {
        return passengers.size();
    }
    
    public int getFloorNumber() {
        return floorNumber;
    }
    
    public List<Passenger> getPassengers() {
        return passengers;
    }
    
    @Override
    public String toString() {
        return "#" + floorNumber + " " + passengers;
    }
}
