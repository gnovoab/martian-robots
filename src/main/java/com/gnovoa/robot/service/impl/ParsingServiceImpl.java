
//Namespace
package com.gnovoa.robot.service.impl;

//Imports
import com.gnovoa.robot.domain.model.Coordinate;
import com.gnovoa.robot.domain.model.RobotInstruction;
import com.gnovoa.robot.domain.model.RobotPosition;
import com.gnovoa.robot.domain.rest.Input;
import com.gnovoa.robot.exception.ParseException;
import com.gnovoa.robot.service.ParsingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Class that handles parsing operations
 */
@Service
public class ParsingServiceImpl implements ParsingService {

    /**
     * Transform plain inpit into Java Object
     * @param plainTextInput
     * @return
     */
    @Override
    public Input parseInput(String plainTextInput) {

        try{
            //Initialise
            Input input = new Input();
            input.setRobotsInstructions(new ArrayList<>());

            RobotInstruction robotInstruction = null;
            int count = 0;

            //Iterate Over
            String[]lines = plainTextInput.split(System.getProperty("line.separator"));
            for(String line : lines){
                String[] fields = line.split(" ");
                count ++;

                //Set the grid dimentions
                if(count == 1) {
                    input.setGrid(new Coordinate(Integer.parseInt(fields[0]),Integer.parseInt(fields[1])));
                }

                //Set Robot initial position
                else if( count % 2 == 0) {
                    robotInstruction = new RobotInstruction();
                    robotInstruction.setInitialPosition(new RobotPosition(
                            new Coordinate(Integer.parseInt(fields[0]),Integer.parseInt(fields[1])),
                            fields[2].charAt(0)));
                }

                //Set the robot instructions
                else {
                    robotInstruction.setInstructions(fields[0].chars().mapToObj(e -> (char)e).collect(Collectors.toList()));
                    input.getRobotsInstructions().add(robotInstruction);
                }
            }

            return input;
        }
        catch (Exception e){
            throw new ParseException(e.getMessage(), e);
        }


    }
}
