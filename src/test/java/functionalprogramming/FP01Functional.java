package functionalprogramming;

import java.util.List;

public class FP01Functional {

    public static void main(String[] args) {

        printAllNumbersInListFunctional(List.of(12,9,13,4,6,2,4,12,15));
    }


    /*private static void print(int number) {
        System.out.println(number);
    }*/

    private static boolean isEven(int number) {
        return number%2 == 0;
    }

    private static void printAllNumbersInListFunctional(List<Integer> numbers) {

        //numbers.stream().forEach(FP01Functional::print);
        numbers.stream()
                //.filter(FP01Functional::isEven)
                // Using Lambda to simplify the filtering
                .filter(number -> number%2 == 0)
                .forEach(System.out::println); // Method Reference
    }

}

