package org.timgroup.dojo;

import java.util.regex.Pattern;

import fj.F;
import fj.Ord;
import fj.data.Stream;

import static java.lang.Integer.parseInt;

public class WeatherMunging {
    private final Stream<String> lines;
    
    public static class Range implements Comparable<Range> {

        public int label;
        public int left;
        public int right;
        
        public Range(int label, int left, int right) {
            this.label = label;
            this.left = left;
            this.right = right;
        }
        
        private int difference() {
            return left - right;
        }

        @Override
        public int compareTo(Range d) {
            return Integer.valueOf(difference()).compareTo(d.difference());
        }
    };
    
    public static final F<String, Range> toRange = new F<String, WeatherMunging.Range>() {

        @Override
        public Range f(String a) {
            final String[] cells = a.split("[^\\d]+");
            return new Range(parseInt(cells[1]), parseInt(cells[2]), parseInt(cells[3]));
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
        return lines.filter(isDataLine)
                     .map(toRange)
                     .foldLeft1(Ord.<Range>comparableOrd().min)
                     .label;
    }

}
