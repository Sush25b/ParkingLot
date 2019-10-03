package com.thoughtworks;

import org.graalvm.compiler.debug.Assertions;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotTest {

    @Test
    public void givenParkingLot_whenPark_thenShouldBeAbleToPark() {
        ParkingLot parkingLot = new ParkingLot(1);

        Assert.assertEquals("Sucessfully Park", parkingLot.park(new Object()));
    }

    @Test
    public void givenParkingLotHavingCapacityTen_whenPark_thenShouldBeAbleToPark() {
        ParkingLot parkingLot = new ParkingLot(10);

        Assert.assertEquals("Sucessfully Park", parkingLot.park(new Object()));

    }

    @Test
    public void givenParkingLotWithNoCapacity_whenPark_thenShouldNotBeAbleToPark() {
        ParkingLot parkingLot = new ParkingLot(0);

        Assert.assertEquals("Space Not Available", parkingLot.park(new Object()));
    }

    @Test
    public void givenParkingLotCapacityTwoWithOneFreeSpace_whenPark_thenShouldBeAbleToPark() {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(new Object());

        Assert.assertEquals("Sucessfully Park", parkingLot.park(new Object()));
    }

    @Test
    public void givenParkingLotCapacityTwoWithNoFreeSpace_whenPark_thenShouldNotBeAbleToPark() {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(new Object());
        parkingLot.park(new Object());

        Assert.assertEquals("Space Not Available", parkingLot.park(new Object()));
    }

    @Test
    public void givenParkingLotCapacityTwoWithFreeSpace_whenParkSameObject_thenShouldNotBeAbleToPark() {
        ParkingLot parkingLot = new ParkingLot(2);

        Object object = new Object();
        parkingLot.park(object);

        Assert.assertEquals("Already Parked", parkingLot.park(object));
    }


}
