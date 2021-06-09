package com.rv02.AssetManagement.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final String NOT_FOUND_MESSAGE = "No Entry Match for Given Data";
    private static final String ASSET_ASSIGNED_MESSAGE = "The given asset is assigned to a different employee";
    private static final String ASSET_NOT_ASSIGNED_MESSAGE = "The given asset is not assigned to any employee";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class,
            javax.validation.ConstraintViolationException.class})
    public HashMap<String, String> handleValidationExceptions(Exception e) {
        HashMap<String, String> error = new HashMap<>();
        error.put("Reason", "Not Valid Json");
        error.put("error", e.toString());
        return error;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({DataNotFoundException.class})
    public HashMap<String, String> handleNotFoundException(Exception e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", NOT_FOUND_MESSAGE);
        response.put("error", e.getClass().getSimpleName());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({AssetAssignedException.class, AssetNotAssignedException.class})
    public HashMap<String, String> handleAssetStatusException(Exception e) {
        HashMap<String, String> response = new HashMap<>();
        if (e instanceof AssetAssignedException) {
            response.put("message", ASSET_ASSIGNED_MESSAGE);
        } else if (e instanceof AssetNotAssignedException) {
            response.put("message", ASSET_NOT_ASSIGNED_MESSAGE);
        }
        response.put("error", e.getClass().getSimpleName());
        return response;
    }

}
