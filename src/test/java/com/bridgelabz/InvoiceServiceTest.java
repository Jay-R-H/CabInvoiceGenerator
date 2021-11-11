package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class InvoiceServiceTest {

    InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
    private static final int NORMAL_RIDE = 1;
    private static final int PREMIUM_RIDE = 2;

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        int option = 1;
        double fare = invoiceGenerator.calculateFare(distance, time, option);
        Assert.assertEquals(10, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFareCharged() {
        double distance = 0.1;
        int time = 1;
        int option = 1;
        double fare = invoiceGenerator.calculateFare(distance, time, option);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnDetailsOfTheRides() {
        Ride[] rides = {new Ride(2.0, 5), new Ride(0.1, 1)};
        int option = NORMAL_RIDE;
        InvoiceSummary invoiceSummary = invoiceGenerator.calculateFareSummary(rides, option);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
    }

    @Test
    public void givenUserID_ShouldReturnRideDetailsOfTheUserID() {
        HashMap<Integer, Ride[]> userTable = new HashMap<>();

        int userID1 = 1;
        Ride[] ride1 = {new Ride(2.0, 5), new Ride(0.1, 1)};
        userTable.put(userID1, ride1);


        int userID2 = 2;
        Ride[] ride2 = {new Ride(3.0, 5), new Ride(1, 1)};
        userTable.put(userID2, ride2);

        int userID = 1;

        if (userTable.containsKey(userID)) {
            int option = PREMIUM_RIDE;
            InvoiceSummary invoiceSummary = invoiceGenerator.calculateFareSummary(userTable.get(userID), option);
            InvoiceSummary expectedInvoiceSummary;
            if (option == NORMAL_RIDE) {
                expectedInvoiceSummary = new InvoiceSummary(2, 61);
                Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
            }
            else {
                expectedInvoiceSummary = new InvoiceSummary(2, 100);
                Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
            }
        }
    }
}
