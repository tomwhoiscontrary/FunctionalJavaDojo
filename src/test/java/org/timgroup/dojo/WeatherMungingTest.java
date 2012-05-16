package org.timgroup.dojo;

import org.junit.Test;

import fj.data.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.timgroup.dojo.Files.readLinesFrom;

public class WeatherMungingTest {
    
    @Test
    public void weatherIsFine() {
        Stream<String> weatherLines = readLinesFrom("weather.dat");
        assertThat(new WeatherMunging(weatherLines).dayWithTheSmallestTemperatureSpread(), is(14));
    }
}
