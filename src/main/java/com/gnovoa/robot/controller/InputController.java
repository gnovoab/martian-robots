
//Namespace
package com.gnovoa.robot.controller;

//Imports
import com.gnovoa.robot.domain.constants.Instruction;
import com.gnovoa.robot.domain.model.Coordinate;
import com.gnovoa.robot.domain.model.RobotInstruction;
import com.gnovoa.robot.domain.model.RobotLog;
import com.gnovoa.robot.domain.model.RobotPosition;
import com.gnovoa.robot.domain.rest.Input;
import com.gnovoa.robot.domain.rest.api.ApiErrorResponse;
import com.gnovoa.robot.exception.IllegalInstructionStateException;
import com.gnovoa.robot.exception.IllegalOrientationStateException;
import com.gnovoa.robot.service.MovementService;
import com.gnovoa.robot.service.OrientationService;
import com.gnovoa.robot.service.ParsingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Endpoint for Robots inputs
 */
@Tag(name = "Input", description = "Robots Input")
@RestController
@RequestMapping("/api/v1/input")
public class InputController {

    @Autowired
    private transient ParsingService parsingService;

    @Autowired
    private transient OrientationService orientationService;

    @Autowired
    private transient MovementService movementService;

    /**
     * Process an input
     * @return
     */
    @Operation(summary = "Process an input", description = "Process an input for the robots", tags = { "Input" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource created", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Malformed request syntax", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "The service encountered a problem.", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> processInput(@RequestBody String plainTextInput) {

        //PTransform the String into a meaningfull Object
        Input input = parsingService.parseInput(plainTextInput);
        Map<RobotPosition, Boolean> scents = new HashMap<>();
        List<RobotLog> logs = new ArrayList<>();

        //Iterate Over each robot
        for(RobotInstruction robot : input.getRobotsInstructions()){
            RobotPosition currentPosition = robot.getInitialPosition();
            RobotLog robotLog = new RobotLog(currentPosition, false);

            //Iterate over each instruction
            for(char instruction : robot.getInstructions()) {

                if(instruction == Instruction.LEFT) {
                    currentPosition.setOrientation(orientationService.turnLeft(currentPosition.getOrientation()));
                }
                else if(instruction == Instruction.RIGHT) {
                    currentPosition.setOrientation(orientationService.turnRight(currentPosition.getOrientation()));
                }
                else if(instruction ==  Instruction.FORWARD) {
                    Coordinate coordinate = movementService.moveForward(currentPosition.getOrientation(), currentPosition.getCoordinate());

                    //If Scent goes to next instruction
                    if(scents.get(coordinate) != null) {
                        continue;
                    }

                    //If falls save scent
                    else if(coordinate.getX() > input.getGrid().getX() || coordinate.getY() > input.getGrid().getY() || coordinate.getX() < 0 || coordinate.getY() < 0){
                        scents.put(currentPosition, true);
                        robotLog = new RobotLog(currentPosition, true);
                        break;
                    }
                    else {
                        currentPosition.setCoordinate(coordinate);
                    }
                }
                else {
                    throw new IllegalInstructionStateException("Unexpected value: " + instruction);
                }
                robotLog = new RobotLog(currentPosition, false);
            }

            logs.add(robotLog);
        }

        //Return the output
        return new ResponseEntity<>(parsingService.generateOutput(logs), HttpStatus.OK);
    }
}
