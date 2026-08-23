package com.gabriel.challenge.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;

import com.gabriel.challenge.exceptions.RequiredFieldException;

public class RequiredFieldValidator {

    private RequiredFieldValidator() { }

    public static void validate(Record record) {
        for (RecordComponent component : record.getClass().getRecordComponents()) {
            try {
                Object value = component.getAccessor().invoke(record);
                if (value == null) {
                    throw new RequiredFieldException(component.getName());
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Failed to validate field: " + component.getName(), e);
            }
        }
    }
}