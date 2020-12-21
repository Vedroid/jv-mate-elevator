package ua.vedroid.elevator;

public class Passenger {
    private final int requiredFloor;
    
    public Passenger(int requiredFloor) {
        this.requiredFloor = requiredFloor;
    }
    
    public int getRequiredFloor() {
        return requiredFloor;
    }
    
    @Override
    public String toString() {
        return "'" + requiredFloor + "'";
    }
}
