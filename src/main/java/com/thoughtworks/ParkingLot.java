package com.thoughtworks;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ParkingLot {
    private final int capacity;
    private List<Object> vehicles = new ArrayList<>();

    public static Map<Integer, String> message = new TreeMap<>();

    static {
        message.put(1, "Space Not Available");
        message.put(2, "Already Parked");
        message.put(3, "Sucessfully Park");
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    private boolean isSpaceAvailable() {
        return vehicles.size() < capacity;
    }

    private boolean isNotAlreadyParked(Object object) {
        return vehicles.contains(object);
    }

    public String park(Object object) {

        if (!isSpaceAvailable()) {
            return message.get(1);
        }

        if (isNotAlreadyParked(object)) {
            return message.get(2);
        }

        vehicles.add(object);
        return message.get(3);
    }
}
