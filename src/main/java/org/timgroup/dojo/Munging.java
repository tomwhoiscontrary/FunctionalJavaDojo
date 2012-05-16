package org.timgroup.dojo;

import java.util.regex.Pattern;

import fj.data.Stream;

import static java.lang.Integer.parseInt;

public class Munging {
    
    protected final Stream<String> lines;
    
    public Munging(Stream<String> lines) {
        this.lines = lines;
    }
    
    protected String munge(int labelColumn, int leftColumn, int rightColumn) {
        Range minimumRange = null;
        for (String line : lines) {
            if (!isDataLine(line)) continue;
            Range range = parseRange(line, labelColumn, leftColumn, rightColumn);
            minimumRange = min(minimumRange, range);
        }
        return minimumRange.label;
    }
    
    private Range parseRange(String line, int labelColumn, int leftColumn, int rightColumn) {
        Range range;
        {
            final String[] cells = line.split("[^\\w]+");
            range = new Range(cells[labelColumn], parseInt(cells[leftColumn]), parseInt(cells[rightColumn]));
        }
        return range;
    }
    
    private boolean isDataLine(String line) {
        return Pattern.matches("\\s+[0-9]+.*", line);
    }
    
    private <T extends Comparable<T>> T min(T a, T b) {
        if (a == null) return b;
        return a.compareTo(b) <= 0 ? a : b;
    }
    
}
