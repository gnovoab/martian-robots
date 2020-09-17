
//Namespace
package com.gnovoa.robot.domain.model;

/**
 * Class that represents robot positiomn
 */
public class RobotPosition {

    //Fields
    private Coordinate coordinate;
    private char orientation;

    //Getters and Setters

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public char getOrientation() {
        return orientation;
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }
}
