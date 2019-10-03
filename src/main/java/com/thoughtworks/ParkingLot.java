package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final int capacity;
    private List<Object> vehicles = new ArrayList<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    private boolean isSpaceAvailable() {
        return vehicles.size() < capacity;
    }

    private boolean isNotAlreadyParked(Object object) {
        return !vehicles.contains(object);
    }

    public boolean park(Object object) {

        if (isSpaceAvailable() && isNotAlreadyParked(object)) {
            vehicles.add(object);
            return true;
        }
        return false;
    }


}
