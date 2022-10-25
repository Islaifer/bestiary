package com.islaifer.bestiary.constants;

/**
 * Maps the errors.
 * @version 0.1.0
 */
public enum MessagesConstants {
    SUCCESS_POST("Data was add successfully"),
    SUCCESS_UPDATE("Data was update successfully"),
    SUCCESS_DELETE("Data was delete successfully"),
    ERROR_POST("Data wasn't add successfully"),
    ERROR_UPDATE("Data wasn't update successfully"),
    ERROR_DELETE("Data wasn't delete successfully");

    private final String messageConstant;

    MessagesConstants(String messageConstant){
        this.messageConstant = messageConstant;
    }

    @Override
    public String toString() {
        return this.messageConstant;
    }
}
