package com.epam.cdp.collection.interval;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class IntervalCollection<E extends Interval> extends AbstractCollection<E> {

	private Collection<E> collection;

	public IntervalCollection() {
		this.collection = new ArrayList<E>();
	}

	public IntervalCollection(Collection<E> collection) {
		this.collection = collection;
	}

	@Override
	public Iterator<E> iterator() {
		return collection.iterator();
	}

	@Override
	public int size() {
		return collection.size();
	}

	@Override
	public boolean add(E e) {
		if (size() == 0) {
			return collection.add(e);
		} else {
			return checkInterval(e);
		}

	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		for (E item : c) {
			if (!add(item)) {
				return false;
			}
		}

		return true;
	}

	private boolean checkInterval(E addObj) {
		Iterator<E> iterator = iterator();
		E interval = addObj;

		while (iterator.hasNext()) {
			Interval current = iterator.next();
			Integer min = current.getMin();
			Integer max = current.getMax();

			boolean minCondition = ((min > interval.getMin()) && (min > interval.getMax()));
			boolean maxCondition = ((max < interval.getMin()) && (max < interval.getMax()));

			if (!(minCondition || maxCondition)) {
				min = (min <= interval.getMin()) ? min : interval.getMin();
				max = (max >= interval.getMax()) ? max : interval.getMax();

				iterator.remove();
				interval = addInterval(new Interval(min, max));
			}

		}

		addObj = (addObj == interval) ? addObj : interval;

		return collection.add(addInterval(addObj));
	}

	@SuppressWarnings("unchecked")
	private E addInterval(Interval interval) {
		return (E) interval;
	}
}
