package com.thoughtworks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    void givenParkingLot_whenPark_thenShouldBeAbleToPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(1);
        assertTrue(parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotHavingCapacityTen_whenPark_thenShouldBeAbleToPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(10);

        assertTrue(parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotWithNoCapacity_whenPark_thenShouldNotBeAbleToPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(0);
        Object objectOne = new Object();

        ParkingLotException exception = assertThrows(ParkingLotException.class, () -> parkingLot.park(objectOne));

        assertEquals("Space Not Available", exception.message());
    }

    @Test
    void givenParkingLotCapacityTwoWithOneFreeSpace_whenPark_thenShouldBeAbleToPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(new Object());

        assertTrue(parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotCapacityTwoWithNoFreeSpace_whenPark_thenShouldNotBeAbleToPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(new Object());
        parkingLot.park(new Object());

        ParkingLotException exception = assertThrows(ParkingLotException.class, () -> parkingLot.park(new Object()));

        assertEquals("Space Not Available", exception.message());
    }

    @Test
    void givenParkingLotCapacityTwoWithNoFreeSpace_whenParkSameObject_thenItThrowsException() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2);
        Object objectOne = new Object();

        assertTrue(parkingLot.park(objectOne));

        ParkingLotException exception = assertThrows(ParkingLotException.class, () -> parkingLot.park(objectOne));
        assertEquals("Already Parked", exception.message());
    }

    @Test
    void givenParkingLotCapacityOne_WhenUnparkTheParkObject_ThenItShouldBeUnPark() throws ParkingLotException {

        ParkingLot parkingLot = new ParkingLot(1);
        Object object = new Object();
        parkingLot.park(object);

        assertEquals(object, parkingLot.unPark(object));
    }

    @Test
    void givenParkingLotWithCapacityTwo_WhenUnParkTheNotParkedObject_ThenItShouldNotAbleToUnPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2);

        Object objectOne = new Object();
        Object objectTwo = new Object();

        parkingLot.park(objectOne);

        ParkingLotException exception = assertThrows(ParkingLotException.class, () -> parkingLot.unPark(objectTwo));
        assertEquals("Object is Not Parked", exception.message());
    }


}
