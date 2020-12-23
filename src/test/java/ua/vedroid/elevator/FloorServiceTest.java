package ua.vedroid.elevator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FloorServiceTest {
    private static final int MAX_FLOORS = 20;
    private static final int MIX_FLOORS = 5;
    private static final int MAX_PASSENGERS_IN_FLOOR = 10;
    
    private FloorService floorService;
    
    @BeforeEach
    void setUp() {
        floorService = new FloorService();
    }
    
    @Test
    void generateFloors() {
        for (int i = 0; i < 1000; i++) {
            List<Floor> floors = floorService.generateFloors();
            int floorsSize = floors.size();
            assertTrue(floorsSize >= MIX_FLOORS && floorsSize <= MAX_FLOORS);
            for (Floor floor : floors) {
                assertTrue(floor.getPassengers().size() <= MAX_PASSENGERS_IN_FLOOR);
            }
        }
    }
}
