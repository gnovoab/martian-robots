
//Namespace
package com.gnovoa.robot.domain;

/**
 * Class that represents robot positiomn
 */
public class RobotPosition {

    //Fields
    private GridCoordinate coordinate;
    private char orientation;

    //Getters and Setters

    public GridCoordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(GridCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    public char getOrientation() {
        return orientation;
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }
}
