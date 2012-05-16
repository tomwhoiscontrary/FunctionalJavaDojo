package org.timgroup.dojo;


public class Range implements Comparable<Range> {

    public String label;
    public int left;
    public int right;
    
    public Range(String label, int left, int right) {
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
}