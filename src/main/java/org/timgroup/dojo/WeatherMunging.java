package org.timgroup.dojo;

import fj.data.Stream;

public class WeatherMunging extends Munging {
    
    public WeatherMunging(Stream<String> lines) {
        super(lines);
    }
    
    public int dayWithTheSmallestTemperatureSpread() {
        return Integer.parseInt(munge(1, 2, 3));
    }
    
}
