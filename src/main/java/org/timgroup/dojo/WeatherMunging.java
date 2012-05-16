package org.timgroup.dojo;

import java.util.regex.Pattern;

import fj.F;
import fj.data.Stream;

import static java.lang.Integer.parseInt;

public class WeatherMunging {
    private final Stream<String> lines;
    
    public static class Day {

        public int number;
        public int maxTemp;
        public int minTemp;
        public Day(int number, int maxTemp, int minTemp) {
            this.number = number;
            this.maxTemp = maxTemp;
            this.minTemp = minTemp;
        }
    };
    
    public static final F<String, Day> toDay = new F<String, WeatherMunging.Day>() {

        @Override
        public Day f(String a) {
            final String[] cells = a.split("[^\\d]+");
            return new Day(parseInt(cells[1]), parseInt(cells[2]), parseInt(cells[3]));
        }
    };
    
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
        Stream<Day> days = dataLines.map(toDay);
        return 0;
    }

}
