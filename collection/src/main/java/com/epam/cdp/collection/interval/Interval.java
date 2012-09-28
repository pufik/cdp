package com.epam.cdp.collection.interval;

public class Interval {
	private final Integer min;
	private final Integer max;

	public Interval(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("Incorrect interval values: [" + min + "," + max + "]");
		}

		this.min = new Integer(min);
		this.max = new Integer(max);
	}

	public Integer getMin() {
		return min;
	}

	public Integer getMax() {
		return max;
	}

	@Override
	public String toString() {
		return "[" + min + "," + max + "]";
	}

}
