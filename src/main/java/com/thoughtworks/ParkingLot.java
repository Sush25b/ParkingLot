package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final int capacity;
    private List<Object> list = new ArrayList<Object>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public boolean park(Object object) {

        if (list.size() < capacity && !list.contains(object)) {
            list.add(object);
            return true;
        }
        return false;
    }
}
