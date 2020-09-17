
//Namespace
package com.gnovoa.robot.service;

//Imports
import com.gnovoa.robot.domain.Coordinate;
import com.gnovoa.robot.domain.RobotPosition;
import com.gnovoa.robot.domain.constants.Orientation;

public interface MovementService {
    Coordinate moveForward(char currentOrientation, Coordinate currentPosition);
}
