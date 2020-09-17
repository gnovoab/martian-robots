
//Namesapce
package com.gnovoa.robot.domain.model;

import com.gnovoa.robot.exception.InvalidCoordinateException;

/**
 * Class that represents the coordinates ona  grid
 */
public class Coordinate {
    private static final int MAX_COORDINATE_VALUE = 50;

    private int x;
    private int y;

    /**
     * Constructor
     * @param x
     * @param y
     */
    public Coordinate(int x, int y) {

        if(x > MAX_COORDINATE_VALUE || y > MAX_COORDINATE_VALUE){
            throw new InvalidCoordinateException("Coordinates can't be bigger than " + MAX_COORDINATE_VALUE);
        }

        this.x = x;
        this.y = y;
    }


    //Getters and Setters

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
