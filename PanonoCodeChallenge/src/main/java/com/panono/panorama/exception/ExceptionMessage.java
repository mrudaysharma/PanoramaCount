/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panono.panorama.exception;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author uday
 */
@Getter
@Setter
class ExceptionMessage {

    private final int status;

    private final String message;

    private List<FieldError> fieldErrors = new ArrayList<>();

    ExceptionMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public void addException(String fieldName, String path, String message) {
        FieldError error = new FieldError(fieldName, path, message);
        fieldErrors.add(error);
    }

}
