
//Namespace
package com.gnovoa.robot.service;

import com.gnovoa.robot.domain.constants.Instruction;
import com.gnovoa.robot.domain.constants.Orientation;
import com.gnovoa.robot.domain.model.RobotInstruction;
import com.gnovoa.robot.domain.rest.Input;
import com.gnovoa.robot.exception.IllegalOrientationStateException;
import com.gnovoa.robot.exception.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

/**
 * Unit test class
 */
@ActiveProfiles("unitTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParsingServiceTest {

    @Autowired
    private transient ParsingService parsingService;

    private transient  String inputText =
            "5 3\n" +
            "1 1 E\n" +
            "RFRFRFRF\n" +
            "3 2 N\n" +
            "FRRFLLFFRRFLL\n" +
            "0 3 W\n" +
            "LLFFFLFLFL";


    @Test
    void parseInputTest() {
        Input input = parsingService.parseInput(inputText);
        Assertions.assertEquals(5, input.getGrid().getX());
        Assertions.assertEquals(3, input.getGrid().getY());
        Assertions.assertEquals(3, input.getRobotsInstructions().size());

        //First Robot
        RobotInstruction robotInstruction = input.getRobotsInstructions().get(0);
        Assertions.assertEquals(1, robotInstruction.getInitialPosition().getCoordinate().getX());
        Assertions.assertEquals(1, robotInstruction.getInitialPosition().getCoordinate().getY());
        Assertions.assertEquals(Orientation.EAST, robotInstruction.getInitialPosition().getOrientation());
        Assertions.assertEquals(8, robotInstruction.getInstructions().size());

        Assertions.assertEquals(Instruction.RIGHT, robotInstruction.getInstructions().get(0));
        Assertions.assertEquals(Instruction.FORWARD, robotInstruction.getInstructions().get(1));
        Assertions.assertEquals(Instruction.RIGHT, robotInstruction.getInstructions().get(2));
        Assertions.assertEquals(Instruction.FORWARD, robotInstruction.getInstructions().get(3));
        Assertions.assertEquals(Instruction.RIGHT, robotInstruction.getInstructions().get(4));
        Assertions.assertEquals(Instruction.FORWARD, robotInstruction.getInstructions().get(5));
        Assertions.assertEquals(Instruction.RIGHT, robotInstruction.getInstructions().get(6));
        Assertions.assertEquals(Instruction.FORWARD, robotInstruction.getInstructions().get(7));

        //Second Robot
        robotInstruction = input.getRobotsInstructions().get(1);
        Assertions.assertEquals(3, robotInstruction.getInitialPosition().getCoordinate().getX());
        Assertions.assertEquals(2, robotInstruction.getInitialPosition().getCoordinate().getY());
        Assertions.assertEquals(Orientation.NORTH, robotInstruction.getInitialPosition().getOrientation());
        Assertions.assertEquals(13, robotInstruction.getInstructions().size());

        Assertions.assertEquals(Instruction.FORWARD, robotInstruction.getInstructions().get(0));
        Assertions.assertEquals(Instruction.RIGHT, robotInstruction.getInstructions().get(1));
        Assertions.assertEquals(Instruction.LEFT, robotInstruction.getInstructions().get(4));
    }


    @Test
    void parseInputTestWrong() {

        String inputTextWrong =
                "5 3\n" +
                        "1 B E\n" +
                        "RFRFRFRF\n" +
                        "3 2 N\n" +
                        "FRRFLLFFRRFLL\n" +
                        "0 3 W\n" +
                        "LLFFFLFLFL";


        Assertions.assertThrows(ParseException.class, () -> {
            parsingService.parseInput(inputTextWrong);
        });
    }


    @Test
    void CoordinatesBiggerThan50() {

        String inputTextWrong =
                "5 3\n" +
                        "1 1 E\n" +
                        "RFRFRFRF\n" +
                        "3 51 N\n" +
                        "FRRFLLFFRRFLL\n" +
                        "0 3 W\n" +
                        "LLFFFLFLFL";

        Assertions.assertThrows(ParseException.class, () -> {
            parsingService.parseInput(inputTextWrong);
        });
    }



    @Test
    void InstructionsBiggerThan100() {

        String inputTextWrong =
                "5 3\n" +
                        "1 1 E\n" +
                        "RFRFRFRF\n" +
                        "3 5 N\n" +
                        "FRRFLLFFRRFLLFRRFLLFFRRFLLFRRFLLFFRRFLLFRRFLLFFRRFLLFRRFLLFFRRFLLFRRFLLFFRRFLLFRRFLLFFRRFLLFRRFLLFFRRFLLFRRFLLFFRRFLL\n" +
                        "0 3 W\n" +
                        "LLFFFLFLFL";

        Assertions.assertThrows(ParseException.class, () -> {
            parsingService.parseInput(inputTextWrong);
        });
    }
}
