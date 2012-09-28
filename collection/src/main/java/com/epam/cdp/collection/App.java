package com.epam.cdp.collection;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.epam.cdp.collection.guava.filter.AnimalUtil;
import com.epam.cdp.collection.guava.model.Animal;
import com.epam.cdp.collection.guava.model.Elephant;
import com.epam.cdp.collection.guava.model.Monkey;
import com.epam.cdp.collection.guava.model.Tiger;
import com.epam.cdp.collection.interval.Interval;
import com.epam.cdp.collection.interval.IntervalCollection;
import com.google.common.collect.Lists;

public class App {
	public static void main(String[] args) {
		// guavaTask();
		customCollectionTask();
	}

	private static void guavaTask() {
		AnimalUtil animalUtil = new AnimalUtil();
		List<Animal> zoo = getZoo();

		Collection<String> names = animalUtil.getAnimalNames(zoo);
		Collection<Animal> monkeys = animalUtil.filterAnimals(zoo, Monkey.class);
		Map<Class<? extends Animal>, Collection<String>> groupedNames = animalUtil.getGroupedAnimalNames(zoo);

		System.out.println(names);
		System.out.println(monkeys);
		System.out.println(groupedNames);
	}

	private static List<Animal> getZoo() {
		List<Animal> animals = Lists.newLinkedList();
		animals.add(new Monkey("Monkey 1"));
		animals.add(new Monkey("Monkey 2"));
		animals.add(new Monkey("Monkey 3"));
		animals.add(new Monkey("Monkey 4"));
		animals.add(new Monkey("Monkey 5"));
		animals.add(new Tiger("Tiger 1"));
		animals.add(new Tiger("Tiger 2"));
		animals.add(new Tiger("Tiger 3"));
		animals.add(new Tiger("Tiger 4"));
		animals.add(new Tiger("Tiger 5"));
		animals.add(new Elephant("Elephant 1"));
		animals.add(new Elephant("Elephant 2"));
		animals.add(new Elephant("Elephant 3"));
		animals.add(new Elephant("Elephant 4"));
		animals.add(new Elephant("Elephant 5"));

		return animals;
	}

	private static void customCollectionTask() {
		Collection<Interval> listTask1 = new IntervalCollection<Interval>();
		listTask1.add(new Interval(1, 4));
		listTask1.add(new Interval(2, 5));
		listTask1.add(new Interval(6, 10));
		listTask1.add(new Interval(7, 8));
		listTask1.add(new Interval(7, 11));
		listTask1.add(new Interval(2, 10));

		Collection<Interval> listTask2 = new IntervalCollection<Interval>();
		listTask2.add(new Interval(1, 4));
		listTask2.add(new Interval(2, 5));
		
		Collection<Interval> listTask3 = new IntervalCollection<Interval>();
		listTask3.add(new Interval(1, 4));
		listTask3.add(new Interval(5, 7));
		
		Collection<Interval> listTask4 = new IntervalCollection<Interval>();
		listTask4.add(new Interval(1, 4));
		listTask4.add(new Interval(5, 7));
		listTask4.add(new Interval(4, 5));
		
		System.out.println("List #1: " + listTask1);
		System.out.println("List #2: " + listTask2);
		System.out.println("List #3: " + listTask3);
		System.out.println("List #4: " + listTask4);
	}
}
