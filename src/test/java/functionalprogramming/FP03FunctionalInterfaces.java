package functionalprogramming;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FP03FunctionalInterfaces {
    
    @SuppressWarnings("unused")
    public static void main(String[] args) {

        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        Predicate<Integer> isEvenPredicate = x -> x % 2 == 0;
        Function<Integer, Integer> squareFunction = x -> x * x;
        Consumer<Integer> sysoutConsumer = System.out::println;

        // This is the same as the below example that is commented out, to show the classes behind the lambda expression
        numbers.stream()
                .filter(isEvenPredicate)
                .map(squareFunction)
                .forEach(sysoutConsumer);

        // Lambda
        numbers.stream()
                .filter(x -> x%2 == 0)
                .map(x -> x*x)
                .forEach(System.out::println);
    }
}
