package org.timgroup.dojo;

import fj.data.Stream;

public class FootballMunging extends Munging {
    
    public FootballMunging(Stream<String> lines) {
        super(lines);
    }
    
    public String teamWithSmallestDifferenceBetweenForAndAgainst() {
        return munge(2, 7, 8);
    }
    
}
