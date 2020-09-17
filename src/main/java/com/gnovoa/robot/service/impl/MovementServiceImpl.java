
//Namespace
package com.gnovoa.robot.service.impl;

//Imports
import com.gnovoa.robot.domain.model.Coordinate;
import com.gnovoa.robot.domain.constants.Orientation;
import com.gnovoa.robot.exception.IllegalOrientationStateException;
import com.gnovoa.robot.service.MovementService;
import org.springframework.stereotype.Service;

@Service
public class MovementServiceImpl implements MovementService {


    @Override
    public Coordinate moveForward(char currentOrientation, Coordinate currentPosition) {
        return switch (currentOrientation) {
            case Orientation.NORTH -> new Coordinate(currentPosition.getX(), currentPosition.getY() + 1);
            case Orientation.SOUTH -> new Coordinate(currentPosition.getX(), currentPosition.getY() - 1);
            case Orientation.EAST -> new Coordinate(currentPosition.getX() - 1, currentPosition.getY());
            case Orientation.WEST -> new Coordinate(currentPosition.getX() + 1, currentPosition.getY());
            default -> throw new IllegalOrientationStateException("Unexpected value: " + currentOrientation);
        };
    }
}
