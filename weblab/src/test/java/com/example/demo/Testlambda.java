package com.example.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Testlambda {

	public static void main(String[] args) {

		List<String> languages = Arrays.asList("Scala", "Python", "Java");
		// before java8
		// for(String each:languages) {
		// System.out.println(each);
		// }
		// after java8
		List<String> p1 = languages.stream().map(name -> {
			return name.toLowerCase();
		}).collect(Collectors.toList());
		System.out.println(p1);

		System.out.println(languages.stream().map(name -> name.toLowerCase()).collect(Collectors.joining()));

		Collections.sort(languages, (o1, o2) -> o1.compareTo(o2));
		System.out.println(languages);
		languages.forEach(x -> System.out.println(x));
		languages.forEach(System.out::println);

		List<Double> cost = Arrays.asList(10.0, 20.0, 30.0);
		cost.stream().map(x -> x + x * 0.05).forEach(x -> System.out.println(x));

		List<Integer> list = Arrays.asList(0, 1, 2, 3);
		Comparator<Integer> comparator = (o1, o2) -> o1.compareTo(o2);
		System.out.println(list.stream().min(comparator).get());
		System.out.println(list.stream().max(comparator).get());
	}

}
