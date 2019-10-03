package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;

public class Consumer {
    private final List<Object> vehicleToPark;

    public Consumer(List<Object> vehicleToPark) {
        this.vehicleToPark = vehicleToPark;
    }

    private void parkVechile(ParkingLot parkingLot, ParkingLot parkingLot1) {

//        for (int i = 0; i < vehicleToPark.size(); i++)
//        {
//            System.out.println( parkingLot.park( vehicleToPark.get(i)) +"parkingLot" + i );
//            if( (parkingLot.park( vehicleToPark.get(i)).equals("Space Not Available") || parkingLot.park(vehicleToPark.get(i)).equals("Already Parked")) )
//            {
//                System.out.println( parkingLot1.park(vehicleToPark.get(i)) +"parkingLot1" );
//            }
//        }


        int i= 0;
        do
        {
//            System.out.println( parkingLot.park( vehicleToPark.get(i)) +"parkingLot" + i );
            i++;
        }while(i < vehicleToPark.size());
    }

    public static void main(String[] args) {

        List<Object> listOfVechile = new ArrayList<>();
        listOfVechile.add(new Object());
        listOfVechile.add(new Object());
        listOfVechile.add(new Object());

        Consumer sanjay = new Consumer(listOfVechile);

        sanjay.parkVechile(new ParkingLot(2), new ParkingLot(3)); //plots


    }


}
