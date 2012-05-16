package org.timgroup.dojo;

import org.junit.Test;
import org.timgroup.dojo.WeatherMunging.Day;

import fj.data.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.timgroup.dojo.Files.readLinesFrom;

public class WeatherMungingTest {
    
    @Test public void
    recognises_data_lines() {
        assertFalse(WeatherMunging.isDataLine.f(""));
        assertFalse(WeatherMunging.isDataLine.f("(Unofficial, Preliminary Data). Source: <a"));
        assertFalse(WeatherMunging.isDataLine.f("  mo  82.9  60.5  71.7    16  58.8       0.00              6.9          5.3"));
        
        assertTrue(WeatherMunging.isDataLine.f("   1  88    59    74          53.8       0.00 F       280  9.6 270  17  1.6  93 23 1004.5"));
        assertTrue(WeatherMunging.isDataLine.f("   9  86    32*   59       6  61.5       0.00         240  7.6 220  12  6.0  78 46 1018.6"));
    }
    
    @Test public void
    parses_data_lines() {
        Day day = WeatherMunging.toDay.f("   1  88    59    74          53.8       0.00 F       280  9.6 270  17  1.6  93 23 1004.5");
        assertEquals(1, day.number);
        assertEquals(88, day.maxTemp);
        assertEquals(59, day.minTemp);
    }

    @Test public void
    parses_data_lines_with_asterisks() {
        Day day = WeatherMunging.toDay.f("   9  86    32*   59       6  61.5       0.00         240  7.6 220  12  6.0  78 46 1018.6");
        assertEquals(9, day.number);
        assertEquals(86, day.maxTemp);
        assertEquals(32, day.minTemp);
    }

    @Test
    public void weatherIsFine() {
        Stream<String> weatherLines = readLinesFrom("weather.dat");
        assertThat(new WeatherMunging(weatherLines).dayWithTheSmallestTemperatureSpread(), is(14));
    }
}
