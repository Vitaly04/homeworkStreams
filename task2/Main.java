package homework.streams.task2;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long result = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(result);

        List<String> result1 = persons.stream()
                .filter(value -> value.getSex() == Sex.MAN)
                .filter(x -> x.getAge() > 18 && x.getAge() < 27)
                .map(value -> value.getFamily())
                .collect(Collectors.toList());
        System.out.println(result1);

        Predicate<Person> man = p -> p.getSex() == Sex.MAN;
        Predicate<Person> woman = p -> p.getSex() == Sex.WOMAN;
        Predicate<Person> rangeAgeMen = p -> p.getAge() > 18 && p.getAge() < 65;
        Predicate<Person> rangeAgeWomen = p -> p.getAge() > 18 && p.getAge() < 60;

        List<String> result2 = persons.stream()
                .filter(value -> value.getEducation() == Education.HIGHER)
                .filter(man.and(rangeAgeMen).or(woman.and(rangeAgeWomen)))
                .sorted(Comparator.comparing(Person::getFamily))
                .map(value -> value.getFamily())
                .collect(Collectors.toList());
        System.out.println(result2);
    }
}
