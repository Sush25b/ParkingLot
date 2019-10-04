package com.thoughtworks;

public class ParkingLotException extends Throwable {
    private final String message;

    public ParkingLotException(String message) {
        this.message = message;
    }

    public String message(){
        return message;
    }
}
