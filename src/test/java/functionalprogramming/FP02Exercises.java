package functionalprogramming;

import java.util.List;
import java.util.stream.Collectors;

public class FP02Exercises {

    public static void main(String[] args) {

        List<Integer> numbers = List.of(12,9,13,4,6,2,4,12,15);
        List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices",
                "AWS", "PCF", "Azure", "Docker", "Kubernetes");

        System.out.println(squareNumbersInListAndFindSumOfSquares(numbers));
        System.out.println(cubeNumbersInListAndFindSumOfCubes(numbers));
        System.out.println(findSumOfOddNumbersInList(numbers));
        System.out.println(getEventNumbersOnly(numbers));
        System.out.println(getLengthOfAllCourseTitles(courses));

    }

    // Exercise 7: Square every number in a list and find the sum of squares
    // J shell: numbers.stream().map(x -> x*x).reduce(0, Integer::sum)
    private static int squareNumbersInListAndFindSumOfSquares(List<Integer> numbers) {

        return numbers.stream()
                .map(number -> number * number)
                .reduce(0, Integer::sum);

    }

    // Exercise 8: Cube every number in a list and find the sum of cubes
    // J shell: numbers.stream().map(x -> x*x).reduce(0, Integer::sum)
    private static int cubeNumbersInListAndFindSumOfCubes(List<Integer> numbers) {

        return numbers.stream()
                .map(number -> number * number * number)
                .reduce(0, Integer::sum);

    }

    // Exercise 9: Find sum of odd numbers in a list
    // J shell: numbers.stream().filter(number -> number %2 != 0).reduce(0, (x,y) -> x+y)
    private static int findSumOfOddNumbersInList(List<Integer> numbers) {

        return numbers.stream()
                .filter(number -> number % 2 != 0)
                //.reduce(0, Integer::sum);
                .reduce(0, (x,y) -> x + y);
    }

    // Exercise 10: Create a list with even numbers filtered from the numbers list
    private static List<Integer> getEventNumbersOnly(List<Integer> numbers) {

        return numbers.stream()
                .filter(number -> number % 2 == 0)
                .collect(Collectors.toList());
    }

    // Exercise 11: Create a list with lengths of all course titles
    private static List<Integer> getLengthOfAllCourseTitles(List<String> courses) {

        return courses.stream()
                .map(course -> course.length())
                .collect(Collectors.toList());
    }

}
