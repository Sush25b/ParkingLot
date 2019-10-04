package com.thoughtworks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DummyOwner implements Owner {
    private int noOfTimesLotFull =0;
    private int noOfTimesLotAvailable =0;

    @Override
    public void informParkingLotFull()
    {
        ++noOfTimesLotFull;
    }

    @Override
    public void informParkingLotAvailable()
    {
        ++noOfTimesLotAvailable;
    }

    public int getCountOfLotFull(){ return noOfTimesLotFull;}

    public int getCountOfLotAvailable(){ return noOfTimesLotAvailable; }
}


public class ParkingLotTest {

    @Test
    void givenParkingLot_whenPark_thenShouldBeAbleToPark() throws ParkingLotException {
        Owner owner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(1, owner);
        assertTrue(parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotHavingCapacityTen_whenPark_thenShouldBeAbleToPark() throws ParkingLotException {
        Owner owner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(10, owner);

        assertTrue(parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotWithNoCapacity_whenPark_thenShouldNotBeAbleToPark() throws ParkingLotException {
        Owner owner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(0, owner);
        Object objectOne = new Object();

        ParkingLotException exception = assertThrows(ParkingLotException.class, () -> parkingLot.park(objectOne));

        assertEquals("Space Not Available", exception.message());
    }

    @Test
    void givenParkingLotCapacityTwoWithOneFreeSpace_whenPark_thenShouldBeAbleToPark() throws ParkingLotException {
        Owner owner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2, owner);
        parkingLot.park(new Object());

        assertTrue(parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotCapacityTwoWithNoFreeSpace_whenPark_thenShouldNotBeAbleToPark() throws ParkingLotException {
        Owner owner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2, owner);
        parkingLot.park(new Object());
        parkingLot.park(new Object());

        ParkingLotException exception = assertThrows(ParkingLotException.class, () -> parkingLot.park(new Object()));

        assertEquals("Space Not Available", exception.message());
    }

    @Test
    void givenParkingLotCapacityTwoWithNoFreeSpace_whenParkSameObject_thenItThrowsException() throws ParkingLotException {
        Owner owner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object objectOne = new Object();

        assertTrue(parkingLot.park(objectOne));

        ParkingLotException exception = assertThrows(ParkingLotException.class, () -> parkingLot.park(objectOne));
        assertEquals("Already Parked", exception.message());
    }

    @Test
    void givenParkingLotCapacityOne_WhenUnparkTheParkObject_ThenItShouldBeUnPark() throws ParkingLotException {
        Owner owner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(1, owner);
        Object object = new Object();
        parkingLot.park(object);

        assertEquals(object, parkingLot.unPark(object));
    }

    @Test
    void givenParkingLotWithCapacityTwo_WhenUnParkTheNotParkedObject_ThenItShouldNotAbleToUnPark() throws ParkingLotException {
        Owner owner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2, owner);

        Object objectOne = new Object();
        Object objectTwo = new Object();

        parkingLot.park(objectOne);

        ParkingLotException exception = assertThrows(ParkingLotException.class, () -> parkingLot.unPark(objectTwo));
        assertEquals("Object is Not Parked", exception.message());
    }

    @Test
    void givenParkingLotWithCapacityTwo_whenParkTwoVechile_ThenItShouldInformOwnerLotIsFullOnce() throws ParkingLotException {

        DummyOwner dummyOwner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2, dummyOwner);
        Object firstVechile = new Object();
        Object secondVechile = new Object();
        parkingLot.park(firstVechile);
        parkingLot.park(secondVechile);

        assertEquals(1, dummyOwner.getCountOfLotFull());
    }

    @Test
    void givenParkingLotWithCapacityTwo_whenUnParkOneVechile_ThenItShouldInformOwnerLotIsAvailableOnce() throws ParkingLotException {

        DummyOwner dummyOwner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2, dummyOwner);
        Object firstVehicle = new Object();
        Object secondVehicle = new Object();
        parkingLot.park(firstVehicle);
        parkingLot.park(secondVehicle);

        parkingLot.unPark(secondVehicle);

        assertEquals(1, dummyOwner.getCountOfLotAvailable());
    }

}
