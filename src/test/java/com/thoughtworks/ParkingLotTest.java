package com.thoughtworks;

import org.graalvm.compiler.debug.Assertions;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotTest {
    @Test
    public void givenParkingLot_whenCheck_thenShouldBeAbleToPark() {
        ParkingLot parkingLot = new ParkingLot();

        assertTrue(parkingLot.park(new Object()));
    }
}
