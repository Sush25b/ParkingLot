package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final int capacity;
    private List<Object> vehicles = new ArrayList<>();

//    public static Map<Integer, String> message = new TreeMap<>();
//
//    static {
//        message.put(1, "Space Not Available");
//        message.put(2, "Already Parked");
//        message.put(3, "Sucessfully Park");
//    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    private boolean isSpaceAvailable() {
        return vehicles.size() < capacity;
    }

    private boolean isNotAlreadyParked(Object object)  {
        return vehicles.contains(object);
    }

    public boolean park(Object object) throws ParkingLotException {

        if (!isSpaceAvailable()) {
           throw new ParkingLotException("Space Not Available");
//            return message.get(1);
        }

        if (isNotAlreadyParked(object)) {
            throw new ParkingLotException("Already Parked");
//            return message.get(2);
        }

         return vehicles.add(object);
//        return true;
//        return message.get(3);
    }

    public boolean unPark(Object object) throws ParkingLotException {
        if (vehicles.contains(object)) {
            return vehicles.remove(object);
        }
        if(vehicles.isEmpty()){
            throw  new ParkingLotException("Object is Not Parked");
        }
//        return new ParkingLotExceptionEx("Object is Not Parked");
        return false;
    }


}
