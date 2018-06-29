package com.and;

import java.util.HashSet;
import java.util.Set;

public class MeasSeries {

    public static String measurementName;
    public static Set<MeasSeries> measurement;

    public MeasSeries(String measurementName) {
        this.measurementName = measurementName;
        this.measurement = new HashSet<>();
    }

    public static String getMeasurementName() {
        return measurementName;
    }

    public static void setMeasurementName(String measurementName) {
        MeasSeries.measurementName = measurementName;
    }

    public boolean addMeasurement(MeasSeries measurement) {
        return this.measurement.add(measurement);
    }

    public Set<MeasSeries> getMeasurement(){
        return new HashSet<>(this.measurement);
    }
}