package com.rv02.AssetManagement.exceptionHandling;

/**
 * Throws RuntimeException when the given data cannot be found. Generally due to invalid id.
 */
public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException() {
    }
}
