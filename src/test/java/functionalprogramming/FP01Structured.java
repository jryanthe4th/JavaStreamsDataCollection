package functionalprogramming;

import com.sun.tools.javac.util.List;
import testbase.TestBase;

public class FP01Structured {

    public static void main(String[] args) {

        List<Integer> numbers = List.of(12,9,13,4,6,2,4,12,15);

        printAllNumbersInListStructured(numbers);
        printEvenNumbersInListStructured(numbers);

    }

    private static void printAllNumbersInListStructured(List<Integer> numbers) {
        // How to loop the numbers?
        for(int number:numbers) {
            System.out.println("All Numbers: " + number);
        }
    }

    private static void printEvenNumbersInListStructured(List<Integer> numbers) {
        // How to loop the numbers?
        for(int number:numbers) {
            if(number%2 == 0) // Check that number is even
                System.out.println("Even Numbers: " + number);
        }
    }
}
