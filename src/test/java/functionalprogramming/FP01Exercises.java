package functionalprogramming;

import com.sun.tools.javac.util.List;

public class FP01Exercises {

    public static void main(String[] args) {

        List<Integer> numbers = List.of(12,9,13,4,6,2,4,12,15);
        List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices",
                "AWS", "PCF", "Azure", "Docker", "Kubernetes");

        printAllOddNumbersInList(numbers);
        printAllCoursesIndividually(courses);
        printCoursesContainingWord(courses);
        printCoursesWhoseNameHasFourLetters(courses);
        printSquaresOfEvenNumbersInList(numbers);
        printCubesOfOddNumbersInList(numbers);
        printNumberOfCharactersInEachCourseName(courses);
    }

    private static void printAllOddNumbersInList(List<Integer> numbers) {

        numbers.stream()
                .filter(number -> number % 2 != 0)
                .forEach(System.out::println);
    }

    private static void printAllCoursesIndividually(List<String> courses) {

        courses.stream()
                .forEach(System.out::println);
    }

    private static void printCoursesContainingWord(List<String> courses) {

        courses.stream()
                .filter(course -> course.contains("Spring"))
                .forEach(System.out::println);
    }

    private static void printCoursesWhoseNameHasFourLetters(List<String> courses) {

        courses.stream()
                .filter(course -> course.length() >= 4)
                .forEach(System.out::println);
    }

    private static void printSquaresOfEvenNumbersInList(List<Integer> numbers) {

        numbers.stream()
                .filter(number -> number % 2 == 0)
                .map(number -> number * number)
                .forEach(System.out::println);
    }

    private static void printCubesOfOddNumbersInList(List<Integer> numbers) {

        numbers.stream()
                .filter(number -> number % 2 != 0)
                .map(number -> number * number * number)
                .forEach(System.out::println);
    }

    private static void printNumberOfCharactersInEachCourseName(List<String> courses) {

        courses.stream()
                .map(course -> course.length())
                .forEach(System.out::println);
    }
}
