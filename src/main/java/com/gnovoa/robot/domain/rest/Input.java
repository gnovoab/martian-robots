
//Namespace
package com.gnovoa.robot.domain.rest;

//Imports
import com.gnovoa.robot.domain.model.Coordinate;
import com.gnovoa.robot.domain.model.RobotInstruction;

import java.util.List;

/**
 * Class that represents an input given
 */
public class Input {

    //Fields
    private Coordinate grid;
    private List<RobotInstruction> robotsInstructions;


    //Getters and Setters
    public Coordinate getGrid() {
        return grid;
    }

    public void setGrid(Coordinate grid) {
        this.grid = grid;
    }

    public List<RobotInstruction> getRobotsInstructions() {
        return robotsInstructions;
    }

    public void setRobotsInstructions(List<RobotInstruction> robotsInstructions) {
        this.robotsInstructions = robotsInstructions;
    }
}
