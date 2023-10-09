package com.stuypulse.robot.util;

import java.util.LinkedList;

import com.stuypulse.stuylib.streams.filters.IFilter;

/**
 * Delay queue based on a 0.02s stream
 */
public class DelayFilter implements IFilter {

	private LinkedList<Double> queue;

	public DelayFilter(Number seconds) {
		queue = new LinkedList<Double>();

		for (double d = 0; d < seconds.doubleValue(); d += 0.02) {
			queue.add(0.0);
		}
	}

	@Override
	public double get(double next) {
		queue.addLast(next);

		return queue.removeFirst();
	}
	
}
