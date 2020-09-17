
//Namespace
package com.gnovoa.robot.domain.model;

//Imports
import java.util.List;

/**
 * Class that represent robot instruction
 */
public class RobotInstruction {
    private RobotPosition initialPosition;
    private List<Character> instructions;


    //Getters and Setters
    public RobotPosition getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(RobotPosition initialPosition) {
        this.initialPosition = initialPosition;
    }

    public List<Character> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Character> instructions) {
        this.instructions = instructions;
    }
}
