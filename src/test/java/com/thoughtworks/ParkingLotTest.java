package com.thoughtworks;

import org.graalvm.compiler.debug.Assertions;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotTest {

    @Test
    public void givenParkingLot_whenPark_thenShouldBeAbleToPark() {
        ParkingLot parkingLot = new ParkingLot(0);

        assertTrue(parkingLot.park(new Object()));
    }

    @Test
    public void givenParkingLotHavingCapacityTen_whenPark_thenShouldBeAbleToPark() {
        ParkingLot parkingLot = new ParkingLot(10);

        assertTrue(parkingLot.park(new Object()));
    }
}
