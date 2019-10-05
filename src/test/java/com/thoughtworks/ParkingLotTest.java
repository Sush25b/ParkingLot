package com.thoughtworks;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DummyOwner implements Subscriber {
    public int noOfTimesLotFull = 0;
    public int noOfTimesLotAvailable = 0;

    @Override
    public void informParkingLotFull() {
        ++noOfTimesLotFull;
    }

    @Override
    public void informParkingLotAvailable() {
        ++noOfTimesLotAvailable;
    }
}


class DummySecurityGuard implements Subscriber {
    public int noOfTimesLotFull = 0;
    public int noOfTimesLotAvailable = 0;

    @Override
    public void informParkingLotFull() {
        ++noOfTimesLotFull;
    }

    @Override
    public void informParkingLotAvailable() {
        ++noOfTimesLotAvailable;
    }
}

class DummyManager implements Subscriber {
    public int noOfTimesLotFull = 0;
    public int noOfTimesLotAvailable = 0;

    @Override
    public void informParkingLotFull() {
        ++noOfTimesLotFull;
    }

    @Override
    public void informParkingLotAvailable() {
        ++noOfTimesLotAvailable;
    }
}


public class ParkingLotTest {

    @Test
    void givenParkingLot_whenPark_thenShouldBeAbleToPark() throws Exception {
        List<Subscriber> owner = Arrays.asList(new DummyOwner());
        ParkingLot parkingLot = new ParkingLot(1, owner);

        assertDoesNotThrow(() -> parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotHavingCapacityTen_whenPark_thenShouldBeAbleToPark() throws Exception {
        List<Subscriber> owner = Arrays.asList(new DummyOwner());
        ParkingLot parkingLot = new ParkingLot(10, owner);

        assertDoesNotThrow(() -> parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotWithNoCapacity_whenPark_thenShouldNotBeAbleToPark() throws Exception {
        List<Subscriber> owner = Arrays.asList(new DummyOwner());
        ParkingLot parkingLot = new ParkingLot(0, owner);
        Object objectOne = new Object();

        assertThrows(ParkingLotFullException.class, () -> parkingLot.park(objectOne));
    }

    @Test
    void givenParkingLotCapacityTwoWithOneFreeSpace_whenPark_thenShouldBeAbleToPark() throws Exception {
        List<Subscriber> owner = Arrays.asList(new DummyOwner());
        ParkingLot parkingLot = new ParkingLot(2, owner);
        parkingLot.park(new Object());

        assertDoesNotThrow(() -> parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotCapacityTwoWithNoFreeSpace_whenPark_thenShouldNotBeAbleToPark() throws Exception {
        List<Subscriber> owner = Arrays.asList(new DummyOwner());
        ParkingLot parkingLot = new ParkingLot(2, owner);
        parkingLot.park(new Object());
        parkingLot.park(new Object());

        assertThrows(ParkingLotFullException.class, () -> parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotCapacityTwoWithNoFreeSpace_whenParkSameObject_thenItThrowsException() throws Exception {
        List<Subscriber> owner = Arrays.asList(new DummyOwner());
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object objectOne = new Object();

        parkingLot.park(objectOne);

        assertThrows(VechileAlreadyParkedException.class, () -> parkingLot.park(objectOne));
    }

    @Test
    void givenParkingLotCapacityOne_WhenUnparkTheParkObject_ThenItShouldBeUnPark() throws Exception {
        List<Subscriber> owner = Arrays.asList(new DummyOwner());
        ParkingLot parkingLot = new ParkingLot(1, owner);
        Object object = new Object();
        parkingLot.park(object);

        assertEquals(object, parkingLot.unPark(object));
    }

    @Test
    void givenParkingLotWithCapacityTwo_WhenUnParkTheNotParkedObject_ThenItShouldNotAbleToUnPark() throws Exception {
        List<Subscriber> owner = Arrays.asList(new DummyOwner()); //Subscriber owner = new DummyOwner();

        ParkingLot parkingLot = new ParkingLot(2, owner);

        Object objectOne = new Object();
        Object objectTwo = new Object();

        parkingLot.park(objectOne);

        assertThrows(VechileNotFoundException.class, () -> parkingLot.unPark(objectTwo));
    }

    @Test
    void givenParkingLotWithCapacityTwo_whenParkTwoVechile_ThenItShouldInformOwnerLotIsFullOnce() throws Exception {

        DummyOwner dummyOwner = new DummyOwner();
        List<Subscriber> owner = Arrays.asList(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object firstVechile = new Object();
        Object secondVechile = new Object();
        parkingLot.park(firstVechile);
        parkingLot.park(secondVechile);

        assertEquals(1, dummyOwner.noOfTimesLotFull);
    }

    @Test
    void givenParkingLotCapacityTwoWithTwoVechilePark_whenUnParkOneVechile_ThenItShouldInformOwnerOnceThatLotIsAvailable() throws Exception {

        DummyOwner dummyOwner = new DummyOwner();
        List<Subscriber> owner = Arrays.asList(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object firstVehicle = new Object();
        Object secondVehicle = new Object();
        parkingLot.park(firstVehicle);
        parkingLot.park(secondVehicle);

        parkingLot.unPark(secondVehicle);

        assertEquals(2, dummyOwner.noOfTimesLotAvailable);
    }

    @Test
    void givenParkingLotWithCapacityTwo_whenUnParkAndPark_ThenItShouldInformOwnerLotTwice() throws Exception {

        DummyOwner dummyOwner = new DummyOwner();
        List<Subscriber> owner = Arrays.asList(dummyOwner);
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object firstVehicle = new Object();
        Object secondVehicle = new Object();
        parkingLot.park(firstVehicle);
        parkingLot.park(secondVehicle);

        parkingLot.unPark(secondVehicle);
        parkingLot.park(secondVehicle);

        assertEquals(2, dummyOwner.noOfTimesLotAvailable);
        assertEquals(2, dummyOwner.noOfTimesLotAvailable);
    }

    @Test
    void givenParkingLotCapacityTwoWithTwoVechilePark_whenUnParkOneVechile_ThenInformOwnerAndGuardOnceThatLotIsAvailable() throws Exception {

        DummyOwner dummyOwner = new DummyOwner();
        DummySecurityGuard dummySecurityGuardguard = new DummySecurityGuard();
        List<Subscriber> subscribers = Arrays.asList(dummyOwner, dummySecurityGuardguard);
        ParkingLot parkingLot = new ParkingLot(2, subscribers);

        DummySecurityGuard dummySecurityGuard = new DummySecurityGuard();

        Object firstVehicle = new Object();
        Object secondVehicle = new Object();
        parkingLot.park(firstVehicle);
        parkingLot.park(secondVehicle);

        parkingLot.unPark(secondVehicle);

        assertEquals(2, dummyOwner.noOfTimesLotAvailable);
        assertEquals(2, dummySecurityGuardguard.noOfTimesLotAvailable);
    }

    @Test
    void givenParkingAddNewSubscriber_whenParkOneVechile_ThenInformAllExistingAndNewSubcriber() throws Exception {

        DummyOwner dummyOwner = new DummyOwner();
        DummySecurityGuard dummySecurityGuard = new DummySecurityGuard();
        List<Subscriber> subscribers=new ArrayList<>();
        subscribers.add(dummyOwner);
        subscribers.add(dummySecurityGuard);
        ParkingLot parkingLot = new ParkingLot(2, subscribers);
        Object vehicle = new Object();

        DummyManager dummyManager=new DummyManager();
        parkingLot.addSubscriber(dummyManager);
        parkingLot.park(vehicle);

        assertEquals(1, dummyOwner.noOfTimesLotAvailable);
        assertEquals(1, dummySecurityGuard.noOfTimesLotAvailable);
        assertEquals(1,dummyManager.noOfTimesLotAvailable);
    }

    @Test
    void givenParkingRemoveOneSubscriber_whenParkAndUnPark_ThenInformOnlyExistingSubcriber() throws Exception {

        List<Subscriber> subscribers=new ArrayList<>();
        DummyOwner dummyOwner = new DummyOwner();
        DummySecurityGuard dummySecurityGuard = new DummySecurityGuard();
        subscribers.add(dummyOwner);
        subscribers.add(dummySecurityGuard);
        ParkingLot parkingLot = new ParkingLot(2, subscribers);
        Object vehicleOne = new Object();

        parkingLot.park(vehicleOne);
        assertEquals(1, dummyOwner.noOfTimesLotAvailable);
        assertEquals(1, dummySecurityGuard.noOfTimesLotAvailable);

        parkingLot.removeSubscriber(dummySecurityGuard);
        parkingLot.unPark(vehicleOne);
        assertEquals(2, dummyOwner.noOfTimesLotAvailable);
        assertEquals(1, dummySecurityGuard.noOfTimesLotAvailable);
    }
}
