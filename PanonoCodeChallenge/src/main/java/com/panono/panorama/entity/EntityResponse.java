/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panono.panorama.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author uday
 * @param <T>
 */
public class EntityResponse<T extends Object> extends ResponseEntity<T> {

     public EntityResponse(HttpStatus status) {
        super(status);
    }
    public EntityResponse(T body, HttpStatus status) {
        super(body, status);
    }
    
}
