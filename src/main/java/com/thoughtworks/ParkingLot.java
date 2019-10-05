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

    public void park(Object object) throws Exception {

        if (!isSpaceAvailable()) {
            throw new ParkingLotFullException();
        }

        if (isNotAlreadyParked(object)) {
            throw new VechileAlreadyParkedException();
        }

        vehicles.add(object);
        this.notifyIfFull();   //parkingLotGotFull   isParkingLotFull checkParkingSpace getParkingSpace
    }

    public Object unPark(Object object) throws Exception {

        if (!vehicles.contains(object)) {
            throw new VechileNotFoundException();
        }

        this.notifyIfFull();
        return vehicles.remove(vehicles.indexOf(object));
    }

    private void notifyIfFull()
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
