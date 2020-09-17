
//Namespace
package com.gnovoa.robot.service;

//Imports
import com.gnovoa.robot.domain.model.RobotLog;
import com.gnovoa.robot.domain.rest.Input;

import java.util.List;

public interface ParsingService {
    Input parseInput(String input);
    String generateOutput(List<RobotLog> logs);
}
