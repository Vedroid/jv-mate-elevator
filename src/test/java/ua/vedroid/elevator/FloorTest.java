package ua.vedroid.elevator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import org.junit.jupiter.api.Test;

class FloorTest {
    @Test
    void testRemovePassengers() {
        LinkedList<Passenger> passengersToGroundFloor = new LinkedList<>();
        LinkedList<Passenger> passengersToSecondFloor = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            passengersToGroundFloor.add(new Passenger(0));
            passengersToSecondFloor.add(new Passenger(2));
        }
        Floor actualFloor;
        actualFloor = new Floor(0, passengersToGroundFloor);
        assertEquals(0, actualFloor.getPassengers().size());
        
        actualFloor = new Floor(0, passengersToSecondFloor);
        assertEquals(10, actualFloor.getPassengers().size());
    }
    
    @Test
    void getPassengerFromTheBeginning() {
        LinkedList<Passenger> passengers = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            passengers.add(new Passenger(i));
        }
        Floor actualFloor;
        actualFloor = new Floor(0, passengers);
        for (int i = 0; i < 3; i++) {
            actualFloor.getPassengerFromTheBeginning();
        }
        assertEquals(6, actualFloor.getPassengers().size());
        assertEquals(4, actualFloor.getPassengers().get(0).getRequiredFloor());
    }
    
    @Test
    void getPassengerFromTheEnd() {
        LinkedList<Passenger> passengers = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            passengers.add(new Passenger(i));
        }
        Floor actualFloor;
        actualFloor = new Floor(0, passengers);
        for (int i = 0; i < 3; i++) {
            actualFloor.getPassengerFromTheEnd();
        }
        int actualFloorSize = actualFloor.getPassengers().size();
        assertEquals(6, actualFloorSize);
        assertEquals(6,
                actualFloor.getPassengers().get(actualFloorSize - 1).getRequiredFloor());
    }
    
    @Test
    void upButtonPressed() {
        LinkedList<Passenger> passengersUp = new LinkedList<>();
        LinkedList<Passenger> passengersDown = new LinkedList<>();
        for (int i = 5; i < 10; i++) {
            passengersUp.add(new Passenger(i));
            passengersDown.add(new Passenger(10 - i));
        }
        Floor actualFloor;
        actualFloor = new Floor(0, passengersUp);
        assertTrue(actualFloor.upButtonPressed());
        actualFloor = new Floor(8, passengersDown);
        assertFalse(actualFloor.upButtonPressed());
    }
    
    @Test
    void downButtonPressed() {
        LinkedList<Passenger> passengersUp = new LinkedList<>();
        LinkedList<Passenger> passengersDown = new LinkedList<>();
        for (int i = 5; i < 10; i++) {
            passengersUp.add(new Passenger(i));
            passengersDown.add(new Passenger(10 - i));
        }
        Floor actualFloor;
        actualFloor = new Floor(0, passengersUp);
        assertFalse(actualFloor.downButtonPressed());
        actualFloor = new Floor(8, passengersDown);
        assertTrue(actualFloor.downButtonPressed());
    }
}
