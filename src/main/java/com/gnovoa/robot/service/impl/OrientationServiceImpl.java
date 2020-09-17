
//Namespace
package com.gnovoa.robot.service.impl;

import com.gnovoa.robot.domain.constants.Orientation;
import com.gnovoa.robot.exception.IllegalOrientationStateException;
import com.gnovoa.robot.service.OrientationService;
import org.springframework.stereotype.Service;

/**
 * Class that handles orientation services
 */
@Service
public class OrientationServiceImpl implements OrientationService {

    /**
     * Returns new orientation when robot turn left from an orientation given
     * @param currentOrientation
     * @return
     */
    @Override
    public char turnLeft(char currentOrientation) {

        return switch (currentOrientation) {
            case Orientation.NORTH -> Orientation.EAST;
            case Orientation.SOUTH -> Orientation.WEST;
            case Orientation.EAST -> Orientation.SOUTH;
            case Orientation.WEST -> Orientation.NORTH;
            default -> throw new IllegalOrientationStateException("Unexpected value: " + currentOrientation);
        };
    }


    /**
     * Returns new orientation when robot turn right from an orientation given
     * @param currentOrientation
     * @return
     */
    @Override
    public char turnRight(char currentOrientation) {

        return switch (currentOrientation) {
            case Orientation.NORTH -> Orientation.WEST;
            case Orientation.SOUTH -> Orientation.EAST;
            case Orientation.EAST -> Orientation.NORTH;
            case Orientation.WEST -> Orientation.SOUTH;
            default -> throw new IllegalOrientationStateException("Unexpected value: " + currentOrientation);
        };
    }
}


