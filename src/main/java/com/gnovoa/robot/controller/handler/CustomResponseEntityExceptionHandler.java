
//Namespace
package com.gnovoa.robot.controller.handler;

//Imports
import com.gnovoa.robot.domain.rest.api.ApiErrorResponse;
import com.gnovoa.robot.exception.IllegalInstructionStateException;
import com.gnovoa.robot.exception.IllegalOrientationStateException;
import com.gnovoa.robot.exception.InvalidCoordinateException;
import com.gnovoa.robot.exception.ParseException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Exception handler class for API level
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ParseException.class})
    public ResponseEntity<ApiErrorResponse> exceptionHandler(ParseException e, WebRequest request) {
        return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvalidCoordinateException.class})
    public ResponseEntity<ApiErrorResponse> exceptionHandler(InvalidCoordinateException e, WebRequest request) {
        return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {IllegalOrientationStateException.class})
    public ResponseEntity<ApiErrorResponse> exceptionHandler(IllegalOrientationStateException e, WebRequest request) {
        return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {IllegalInstructionStateException.class})
    public ResponseEntity<ApiErrorResponse> exceptionHandler(IllegalInstructionStateException e, WebRequest request) {
        return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
