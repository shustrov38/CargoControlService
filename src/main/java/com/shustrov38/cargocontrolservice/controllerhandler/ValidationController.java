package com.shustrov38.cargocontrolservice.controllerhandler;

import com.shustrov38.cargocontrolservice.components.emulator.controller.EmulatorController;
import com.shustrov38.cargocontrolservice.components.sensorrequestmanager.controller.SensorRequestManagerController;
import com.shustrov38.cargocontrolservice.components.webapi.controller.WebApiController;
import com.shustrov38.cargocontrolservice.exceptions.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice(assignableTypes = {
        EmulatorController.class,
        SensorRequestManagerController.class,
        WebApiController.class
})
public class ValidationController {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException e) {

        return ResponseEntity.badRequest().body(new ErrorResponse(
                Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage(),
                HttpStatus.BAD_REQUEST
        ));
    }
}
