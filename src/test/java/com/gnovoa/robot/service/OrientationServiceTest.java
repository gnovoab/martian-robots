
//Naespace
package com.gnovoa.robot.service;

//Imports
import com.gnovoa.robot.domain.constants.Orientation;
import com.gnovoa.robot.exception.IllegalOrientationStateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Unit test class
 */
@ActiveProfiles("unitTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrientationServiceTest {

    //Fields
    @Autowired
    private transient OrientationService orientationService;

    @Test
    void turnLeftOKTest() {
        Assertions.assertEquals(Orientation.EAST, orientationService.turnLeft(Orientation.NORTH));
        Assertions.assertEquals(Orientation.SOUTH, orientationService.turnLeft(Orientation.EAST));
        Assertions.assertEquals(Orientation.WEST, orientationService.turnLeft(Orientation.SOUTH));
        Assertions.assertEquals(Orientation.NORTH, orientationService.turnLeft(Orientation.WEST));
    }

    @Test
    void turnRightOKTest() {
        Assertions.assertEquals(Orientation.WEST, orientationService.turnRight(Orientation.NORTH));
        Assertions.assertEquals(Orientation.SOUTH, orientationService.turnRight(Orientation.WEST));
        Assertions.assertEquals(Orientation.EAST, orientationService.turnRight(Orientation.SOUTH));
        Assertions.assertEquals(Orientation.NORTH, orientationService.turnRight(Orientation.EAST));
    }

    @Test
    void turnLeftWrongValueTest() {
        Assertions.assertThrows(IllegalOrientationStateException.class, () -> {
           orientationService.turnLeft('Q');
        });
    }

    @Test
    void turnRightWrongValueTest() {
        Assertions.assertThrows(IllegalOrientationStateException.class, () -> {
            orientationService.turnLeft('Q');
        });
    }
}
