
//Namespace
package com.gnovoa.robot.service;

//Imports
import com.gnovoa.robot.domain.model.Coordinate;

public interface MovementService {
    Coordinate moveForward(char currentOrientation, Coordinate currentPosition);
}
