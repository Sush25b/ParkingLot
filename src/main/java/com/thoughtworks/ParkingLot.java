package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final int capacity;
    private List<Object> vehicles = new ArrayList<>();
    private Owner owner = new Owner();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
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
        owner.getNotify( checkCapacityIsFull() );
        return true;
    }

    public Object unPark(Object object) throws ParkingLotException {

        if (!vehicles.contains(object)) {
            throw new ParkingLotException("Object is Not Parked");
        }

        return vehicles.remove(vehicles.indexOf(object));
    }

    public boolean checkCapacityIsFull()
    {
        return !isSpaceAvailable();
    }

}
