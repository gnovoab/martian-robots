
//Namespace
package com.gnovoa.robot.service;

//Imports
import com.gnovoa.robot.domain.model.Coordinate;
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
public class MovementServiceTest {

    //Fields
    @Autowired
    private transient MovementService movementService;

    @Test
    void moveForwardOK() {

        Coordinate currentCoordinate = new Coordinate(0,0);

        //Test EAST
        Coordinate newCoordinate = movementService.moveForward(Orientation.EAST, currentCoordinate);
        Assertions.assertEquals(currentCoordinate.getX() - 1, newCoordinate.getX());
        Assertions.assertEquals(currentCoordinate.getY(), newCoordinate.getY());

        //Test WEST
        newCoordinate = movementService.moveForward(Orientation.WEST, currentCoordinate);
        Assertions.assertEquals(currentCoordinate.getX() + 1, newCoordinate.getX());
        Assertions.assertEquals(currentCoordinate.getY(), newCoordinate.getY());

        //Test NORTH
        newCoordinate = movementService.moveForward(Orientation.NORTH, currentCoordinate);
        Assertions.assertEquals(currentCoordinate.getX(), newCoordinate.getX());
        Assertions.assertEquals(currentCoordinate.getY() + 1, newCoordinate.getY());

        //Test SOUTH
        newCoordinate = movementService.moveForward(Orientation.SOUTH, currentCoordinate);
        Assertions.assertEquals(currentCoordinate.getX(), newCoordinate.getX());
        Assertions.assertEquals(currentCoordinate.getY() - 1, newCoordinate.getY());
    }

    @Test
    void moveForwardWrong() {
        Assertions.assertThrows(IllegalOrientationStateException.class, () -> {
            movementService.moveForward('Q', new Coordinate(0,0));
        });
    }
}
