package com.epam.cdp.collection.guava.filter;

import java.util.Collection;
import java.util.Map;

import com.epam.cdp.collection.guava.model.Animal;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class AnimalUtil {
	public Collection<Animal> filterAnimals(Collection<Animal> animals, final Class<? extends Animal> clazz) {

		Predicate<Animal> filter = new Predicate<Animal>() {
			public boolean apply(Animal animal) {
				return animal.getClass().equals(clazz);
			}
		};

		return Collections2.filter(animals, filter);
	}

	public Collection<String> getAnimalNames(Collection<Animal> animals) {

		Function<Animal, String> nameFunction = new Function<Animal, String>() {
			public String apply(Animal animal) {
				return animal.getName();
			}
		};
		// One more variant HashMultiset.create(Iterables.transform(animals, nameFunction));
		return Collections2.transform(animals, nameFunction);
	}

	public Map<Class<? extends Animal>, Collection<String>> getGroupedAnimalNames(Collection<Animal> zoo) {
		Multimap<Class<? extends Animal>, String> animalNames = HashMultimap.create();

		for (Animal animal : zoo) {
			animalNames.put(animal.getClass(), animal.getName());
		}

		return animalNames.asMap();
	}
}
