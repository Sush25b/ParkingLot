package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final int capacity;
    private List<Object> vehicles = new ArrayList<>();
    private Consumer consumer;

    public ParkingLot(int capacity, Consumer consumer) {
        this.capacity = capacity;
        this.consumer = consumer;
    }

    private boolean isSpaceAvailable() {
        return vehicles.size() < capacity;
    }

    private boolean isNotAlreadyParked(Object object) {
        return vehicles.contains(object);
    }

    public boolean park(Object object) throws ParkingLotException {

        if (!isSpaceAvailable()) {
            throw new ParkingLotException("Space Not Available");
        }

        if (isNotAlreadyParked(object)) {
            throw new ParkingLotException("Already Parked");
        }

        vehicles.add(object);
        this.notifyIfFull();   //parkingLotGotFull   isParkingLotFull checkParkingSpace getParkingSpace
        return true;
    }

    public Object unPark(Object object) throws ParkingLotException {

        if (!vehicles.contains(object)) {
            throw new ParkingLotException("Object is Not Parked");
        }

        this.notifyIfFull();
        return vehicles.remove(vehicles.indexOf(object));
    }

    void notifyIfFull()
    {
        if(!isSpaceAvailable())       //space is full--> then only notify owner  ELSE dont notify
        {
            consumer.informParkingLotFull();
        }

        if(isSpaceAvailable())       //space is full--> then only notify owner  ELSE dont notify
        {
            consumer.informParkingLotAvailable();
        }

    }
}
