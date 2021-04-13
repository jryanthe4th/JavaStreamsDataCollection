package functionalprogramming;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FP03Exercises {

    // Exercise 12
    // Find functional interface behind the second argument of reduce method.
    // Create an implementation for the Functional Interface.
    // int sum = numbers.stream().reduce(0, Integer::sum);
    


    // Exercise 13
    // Do Behavior Parameterization for the mapping logic
    // List squaredNumbers = numbers.stream().map(x -> x*x).collect(Collectors.toList());

    public static void main(String[] args) {

        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        List<Integer> squaredNumbers = mapAndCreateNewList(numbers, x -> x*x);
        List<Integer> cubedNumbers = mapAndCreateNewList(numbers, x -> x*x*x);
        List<Integer> doubledNumbers = mapAndCreateNewList(numbers, x -> x+x);
    }

    private static List<Integer> mapAndCreateNewList(List<Integer> numbers, Function<Integer, Integer> mappingFunction) {
        return numbers.stream()
                .map(mappingFunction)
                .collect(Collectors.toList());
    }
}
