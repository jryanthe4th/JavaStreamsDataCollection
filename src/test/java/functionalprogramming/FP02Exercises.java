package functionalprogramming;

import com.sun.tools.javac.util.List;
import testbase.TestBase;

import java.util.stream.Collectors;

public class FP02Exercises {

    public static void main(String[] args) {

        List<Integer> numbers = List.of(12,9,13,4,6,2,4,12,15);

        System.out.println(squareNumbersInListAndFindSumOfSquares(numbers));
        System.out.println(cubeNumbersInListAndFindSumOfCubes(numbers));
        System.out.println(findSumOfOddNumbersInList(numbers));

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


}
