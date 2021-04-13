package functionalprogramming;

import java.util.List;
import java.util.function.Predicate;

public class FP03BehaviorParameterization {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        List<Integer> numbers = List.of(12,9,13,4,6,2,4,12,15);

        filterAndPrint(numbers, x1 -> x1 % 2 == 0);

        filterAndPrint(numbers, x -> x % 2 != 0);
    }

    private static void filterAndPrint(List<Integer> numbers, Predicate<Integer> integerPredicate) {
        numbers.stream()
                .filter(integerPredicate)
                .forEach(System.out::println);
    }
}
