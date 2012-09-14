package com.epam.cdp.collection;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.epam.cdp.collection.guava.filter.AnimalUtil;
import com.epam.cdp.collection.guava.model.Animal;
import com.epam.cdp.collection.guava.model.Elephant;
import com.epam.cdp.collection.guava.model.Monkey;
import com.epam.cdp.collection.guava.model.Tiger;
import com.google.common.collect.Lists;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
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
}
