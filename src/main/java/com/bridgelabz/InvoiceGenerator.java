package com.bridgelabz;

public class InvoiceGenerator {

    private static double MINIMUM_COST_PER_KILOMETER;
    private static double COST_PER_TIME;
    private static double MINIMUM_FARE;
    private static final int NORMAL_RIDE = 1;
    private static final int PREMIUM_RIDE = 2;


    public double calculateFare(double distance, int time, int option) {

        if (option == NORMAL_RIDE) {
            MINIMUM_COST_PER_KILOMETER = 10.0;
            COST_PER_TIME = 1;
            MINIMUM_FARE = 5;

        } else {
            MINIMUM_COST_PER_KILOMETER = 15.0;
            COST_PER_TIME = 2;
            MINIMUM_FARE = 20;
        }

        double totalFare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
        return Math.max(totalFare, MINIMUM_FARE);
    }

    public double calculateTotalFare(Ride[] rides, int option) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.distance, ride.time, option);
        }
        return totalFare;
    }

    public InvoiceSummary calculateFareSummary(Ride[] rides, int option) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.distance, ride.time, option);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }
}
