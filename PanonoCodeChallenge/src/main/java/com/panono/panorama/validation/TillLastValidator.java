
package com.panono.panorama.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.lang.System.currentTimeMillis;
/**
 * 
 * @author uday
 */
public class TillLastValidator implements ConstraintValidator<TillLast, Long> {

    @Override
    public void initialize(TillLast constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value == null || value < currentTimeMillis();
    }

}
