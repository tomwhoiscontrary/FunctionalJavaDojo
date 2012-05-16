package org.timgroup.dojo;

import fj.Ord;

import fj.data.Stream;

public class Munging {
    
    protected final Stream<String> lines;

    public Munging(Stream<String> lines) {
        this.lines = lines;
    }

    protected String munge(int labelColumn, int leftColumn, int rightColumn) {
        return lines.filter(MungingFunctions.isDataLine)
                .map(MungingFunctions.toRange(labelColumn, leftColumn, rightColumn))
                .foldLeft1(Ord.<Range>comparableOrd().min)
                .label;
    }
    
}
