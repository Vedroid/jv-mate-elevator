package ua.vedroid.elevator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ElevatorTest {
    private Elevator elevator;
    private LinkedList<Floor> floors;
    
    @BeforeEach
    void setUp() {
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
    void start() {
        elevator.start();
        for (Floor floor : floors) {
            assertEquals(0, floor.getPassengers().size());
            assertTrue(floor.isEmpty());
        }
    }
}
