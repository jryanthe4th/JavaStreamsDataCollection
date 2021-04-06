package functionalprogramming;


import testbase.TestBase;

import java.util.List;

public class FP02Functional {

    public static void main(String[] args) {

        List<Integer> numbers = List.of(12,9,13,4,6,2,4,12,15);

        int sum = addListFunctional(numbers);

        System.out.println("Sum of numbers is: " + sum);

        System.out.println("Max value in the list is: " + minValue(numbers));
        System.out.println("Min value in the list is: " + maxValue(numbers));

    }

    private static int sum(int aggregate, int nextNumber) {
        System.out.println(aggregate + " " + nextNumber);
        return aggregate + nextNumber;
    }

    private static int addListFunctional(List<Integer> numbers) {
        // Stream of number -> result in one value
        return numbers.stream()
                //.reduce(0,FP02Functional::sum);

                // Or you can use method reference
                //.reduce(0, Integer::sum);

                // Or you can use lambda expression
                .reduce(0, (x,y) -> x + y);

    }

    private static int minValue(List<Integer> numbers) {
        // Find max value present in a list
        return numbers.stream()
                .reduce(Integer.MIN_VALUE, (x,y) -> x>y ? x:y);
    }

    private static int maxValue(List<Integer> numbers) {
        // Find min value present in a list
        return numbers.stream()
                .reduce(Integer.MAX_VALUE, (x,y) -> x>y ? y:x);
    }
}
