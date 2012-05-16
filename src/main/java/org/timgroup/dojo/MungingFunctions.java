package org.timgroup.dojo;

import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

import fj.F;

public class MungingFunctions {

    public static final F<String, Boolean> isDataLine = new F<String, Boolean>() {
        
        @Override
        public Boolean f(String a) {
            return Pattern.matches("\\s+[0-9]+.*", a);
        }
    };

    public static final F<String, Range> toRange(final int labelColumn, final int leftColumn, final int rightColumn) {
        return new F<String, Range>() {
            
            @Override
            public Range f(String a) {
                final String[] cells = a.split("[^\\d]+");
                return new Range(cells[labelColumn], parseInt(cells[leftColumn]), parseInt(cells[rightColumn]));
            }
        };
    }
    
}
