package org.timgroup.dojo;


import fj.Ord;
import fj.data.Stream;


public class WeatherMunging {
    private final Stream<String> lines;
    
    public WeatherMunging(Stream<String> lines) {
        this.lines = lines;
    }

    public int dayWithTheSmallestTemperatureSpread() {
        return Integer.parseInt(lines.filter(MungingFunctions.isDataLine)
                                     .map(MungingFunctions.toRange(1, 2, 3))
                                     .foldLeft1(Ord.<Range>comparableOrd().min)
                                     .label);
    }

}
