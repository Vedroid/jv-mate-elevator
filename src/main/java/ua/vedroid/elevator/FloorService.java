package ua.vedroid.elevator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FloorService {
    private static final int MAX_FLOORS = 20;
    private static final int MIX_FLOORS = 5;
    private static final int MAX_PASSENGERS_IN_FLOOR = 10;
    
    public void printPassengersOnFloors(List<Floor> floors) {
        for (Floor floor : floors) {
            System.out.println(floor);
        }
    }
    
    public List<Floor> generateFloors() {
        List<Floor> floors = new ArrayList<>();
        int numberOfFloors = getRandomNumber(MAX_FLOORS - MIX_FLOORS) + MIX_FLOORS;
        for (int floorNumber = 0; floorNumber < numberOfFloors; floorNumber++) {
            LinkedList<Passenger> passengers = new LinkedList<>();
            int numberOfPassengersInFloor = getRandomNumber(MAX_PASSENGERS_IN_FLOOR);
            for (int j = 0; j < numberOfPassengersInFloor; j++) {
                int requiredFloor = getRandomNumber(numberOfFloors);
                passengers.add(new Passenger(requiredFloor));
            }
            floors.add(new Floor(floorNumber, passengers));
        }
        return floors;
    }
    
    private int getRandomNumber(int limit) {
        return new Random().nextInt(limit);
    }
}
