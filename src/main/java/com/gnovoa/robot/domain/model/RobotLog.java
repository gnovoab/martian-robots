
//Namespace
package com.gnovoa.robot.domain.model;

/**
 * Class that logs robot movement
 */
public class RobotLog {

    //Fields
    private RobotPosition position;
    private boolean isRobotLost;


    /**
     * Constructor
     * @param position
     * @param isRobotLost
     */
    public RobotLog(RobotPosition position, boolean isRobotLost) {
        this.position = position;
        this.isRobotLost = isRobotLost;
    }

    //Getters and Setters
    public RobotPosition getPosition() {
        return position;
    }

    public void setPosition(RobotPosition position) {
        this.position = position;
    }

    public boolean isRobotLost() {
        return isRobotLost;
    }

    public void setRobotLost(boolean robotLost) {
        isRobotLost = robotLost;
    }
}
