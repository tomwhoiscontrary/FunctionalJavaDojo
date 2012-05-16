package org.timgroup.dojo;

import fj.Ord;
import fj.data.Stream;

public class FootballMunging {

    private final Stream<String> lines;

    public FootballMunging(Stream<String> lines) {
        this.lines = lines;
    }

    public String teamWithSmallestDifferenceBetweenForAndAgainst() {
        return lines.filter(MungingFunctions.isDataLine)
                .map(MungingFunctions.toRange(2, 7, 8))
                .foldLeft1(Ord.<Range>comparableOrd().min)
                .label;
    }
}
