package org.timgroup.dojo;

import org.junit.Test;

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
        assertFalse(MungingFunctions.isDataLine.f(""));
        assertFalse(MungingFunctions.isDataLine.f("(Unofficial, Preliminary Data). Source: <a"));
        assertFalse(MungingFunctions.isDataLine.f("  mo  82.9  60.5  71.7    16  58.8       0.00              6.9          5.3"));
        
        assertTrue(MungingFunctions.isDataLine.f("   1  88    59    74          53.8       0.00 F       280  9.6 270  17  1.6  93 23 1004.5"));
        assertTrue(MungingFunctions.isDataLine.f("   9  86    32*   59       6  61.5       0.00         240  7.6 220  12  6.0  78 46 1018.6"));
    }
    
    @Test public void
    parses_data_lines() {
        Range day = MungingFunctions.toRange(1, 2, 3).f("   1  88    59    74          53.8       0.00 F       280  9.6 270  17  1.6  93 23 1004.5");
        assertEquals("1", day.label);
        assertEquals(88, day.left);
        assertEquals(59, day.right);
    }

    @Test public void
    parses_data_lines_with_asterisks() {
        Range day = MungingFunctions.toRange(1, 2, 3).f("   9  86    32*   59       6  61.5       0.00         240  7.6 220  12  6.0  78 46 1018.6");
        assertEquals("9", day.label);
        assertEquals(86, day.left);
        assertEquals(32, day.right);
    }

    @Test
    public void weatherIsFine() {
        Stream<String> weatherLines = readLinesFrom("weather.dat");
        assertThat(new WeatherMunging(weatherLines).dayWithTheSmallestTemperatureSpread(), is(14));
    }
}
