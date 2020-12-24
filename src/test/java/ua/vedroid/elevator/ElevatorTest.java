package ua.vedroid.elevator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ElevatorTest {
    private Elevator elevator;
    private List<Floor> floors;
    
    @BeforeEach
    public void setUp() {
        floors = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            LinkedList<Passenger> passengers = new LinkedList<>();
            for (int j = 0; j < 10; j++) {
                passengers.add(new Passenger(j));
            }
            floors.add(new Floor(i, passengers));
        }
        elevator = new Elevator(floors);
    }
    
    @Test
    public void start_testNumberOfStops() {
        elevator.start();
        assertEquals(161, elevator.getStopCounter());
    }
    
    @Test
    public void start_testFloorsIsEmpty() {
        elevator.start();
        for (Floor floor : floors) {
            assertTrue(floor.isEmpty());
        }
    }
}
