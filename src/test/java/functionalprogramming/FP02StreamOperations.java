package functionalprogramming;

import java.util.List;
import java.util.stream.Collectors;

public class FP02StreamOperations {

    public static void main(String[] args) {

        List<Integer> numbers = List.of(12,9,13,4,6,2,4,12,15);
        List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices",
                "AWS", "PCF", "Azure", "Docker", "Kubernetes");

        numbers.stream()
                .distinct() //Stream<T> Intermediate operation
                .sorted() //Stream<T> Intermediate operation
                .forEach(System.out::println); //void

        List<Integer> squaredNumbers = numbers.stream()
                .map(number -> number * number) //Stream<R> Intermediate operation
                .collect(Collectors.toList()); //
    }
}
