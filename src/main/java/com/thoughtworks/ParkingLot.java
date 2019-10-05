package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final int capacity;
    private List<Object> vehicles = new ArrayList<>();
    private List<Subscriber> subscriber; // TODO - both Owner and Security Guard needs to get the notification.

    public ParkingLot(int capacity, List<Subscriber> subscriber) {
        this.capacity = capacity;
        this.subscriber = subscriber;
    }

    private boolean isSpaceAvailable() {
        return vehicles.size() <capacity;
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

        vehicles.remove(vehicles.indexOf(object));
        this.notifyIfFull();
        return object;
    }

    private void notifyIfFull()
    {
        if(!isSpaceAvailable())       //space is full--> then only notify owner  ELSE dont notify
        {
            for(Subscriber s:subscriber){
                s.informParkingLotFull();
            }
        }


        if(isSpaceAvailable())       //space is  not full--> then only notify owner  ELSE dont notify
        {
            for(Subscriber s:subscriber){
                s.informParkingLotAvailable();
            }
        }

    }
}
