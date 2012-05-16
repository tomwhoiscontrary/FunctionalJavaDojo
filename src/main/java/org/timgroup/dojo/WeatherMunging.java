package org.timgroup.dojo;

import java.util.regex.Pattern;

import fj.F;
import fj.data.Stream;

public class WeatherMunging {
    private final Stream<String> lines;
    
    public static final F<String, Boolean> isDataLine = new F<String, Boolean>() {
        
        @Override
        public Boolean f(String a) {
            return Pattern.matches("\\s+[0-9]+.*", a);
        }
    };

    public WeatherMunging(Stream<String> lines) {
        this.lines = lines;
    }

    public int dayWithTheSmallestTemperatureSpread() {
        final Stream<String> dataLines = lines.filter(isDataLine);
        return 0;
    }

}
