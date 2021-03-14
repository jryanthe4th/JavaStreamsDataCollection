import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaStreamsExample {

    // This is a demo of Java Streams from:
    // https://clearcapital.udemy.com/course/selenium-real-time-examplesinterview-questions/learn/lecture/22091322#overview

    // What are Java Steams?
    // A: Stream API is a new feature available from java 8. By using streams, we can perform various aggregate operations
    // on the data returned from collections classes by drastically reducing the complexity of code

    // What are Lambda Expressions?
    // A: Lambda expressions introduce the new arrow operator -> into java. It divides the lambda expressions in two parts:
    // 1: The left side of the lambda body specifies the parameters required by the expression, which could also be empty if no parameters are required.
    // 2: The right side of the lambda body specifies the actions of the lambda expression.
    // 3: If the action is more than one line, you can open the braces and put code inside

    @Test(description = "Example not using JavaStreams")
    public void regular() {

        ArrayList<String> names = new ArrayList<String>();
        names.add("Joseph");
        names.add("Michael");
        names.add("Ryan");
        names.add("Joe");
        names.add("Alex");
        names.add("Levi");
        int count = 0;

        // This method scans the full array list names every time
        for(int i = 0; i < names.size(); i++) {
            String actual = names.get(i);
            if(actual.startsWith("J")) {
                count++;
            }
        }
        System.out.println(count);
    }

    @Test(description = "example using JavaStreams")
    public void streamFilter() {

        ArrayList<String> names = new ArrayList<String>();
        names.add("Joseph");
        names.add("Michael");
        names.add("Ryan");
        names.add("Joe");
        names.add("Alex");
        names.add("Levi");
        names.add("Leticia");
        names.add("Laura");
        names.add("Alyssa");
        int count = 0;

        // There is no life for intermediate operation if there is no terminal operation (if no match, no count provided)
        // Terminal operation will execute only if inter operation (filter) returns true
        Long x = names.stream().filter(s -> s.startsWith("L")).count();
        System.out.println("x = " + x);

        // Create a stream compatible collection
        // Different way of using streams, instead of creating a collection, then converting it into a stream, as we did above
        Long y = Stream.of("Joseph", "Michael", "Ryan", "Joe", "Alex", "Levi", "Leticia", "Laura", "Alyssa").filter(s -> s.startsWith("L")).count();
        System.out.println("y = " + y);

        // Create a stream compatible collection
        // Action with more than one line, so we're opening the braces
        Stream.of("Joseph", "Michael", "Ryan", "Joe", "Alex", "Levi", "Leticia", "Laura", "Alyssa").filter(s -> {
            s.startsWith("L");
            // fix this boolean
            return true;
        }).count();

        // Print all the names of ArrayList with length gt 4
        names.stream().filter(s -> s.length() > 4).forEach(s -> System.out.println("Test 4: " + s));

        // Print only one of the names of ArrayList with length gt 4
        names.stream().filter(s -> s.length() > 4 ).limit(1).forEach(s -> System.out.println("Test 5: " + s));

    }

    @Test
    public void streamMap() {

        // Print names which have last letter of "a" with uppercase
        Stream.of("Joseph", "Michael", "Ryan", "Joe", "Alex", "Levi", "Leticia", "Laura", "Alyssa").filter(s -> s.endsWith("a")).map(s -> s.toUpperCase())
                .forEach(s -> System.out.println("Names ending with 'a' : " + s));

        // Print names which have first letter as "a" with uppercase and sorted
        List<String> names = Arrays.asList("Joseph", "Michael", "Ryan", "Joe", "Alex", "Levi", "Leticia", "Laura", "Alyssa");
        names.stream().filter(s -> s.startsWith("A")).sorted().map(s -> s.toUpperCase()).forEach(s -> System.out.println("Names starting with capital 'A': " + s));

    }

    @Test
    public void merge2ListsAndStreamMap() {

        List<String> names1 = Arrays.asList("Joseph", "Michael", "Ryan", "Joe", "Alex", "Levi", "Leticia", "Laura", "Alyssa");
        names1.stream().filter(s -> s.startsWith("A")).sorted().map(s -> s.toUpperCase()).forEach(s -> System.out.println("Names1 starting with capital 'A': " + s));

        List<String> names2 = Arrays.asList("Jonny", "Curt", "Jason", "Angela", "David", "Anthony", "Joe", "Jeff", "Roman");

        // Merging 2 different lists
        Stream<String> newStream = Stream.concat(names1.stream(), names2.stream());
        System.out.println("Names1: " + names1);
        System.out.println("Names2: " + names2);
        System.out.println("Combined: " + newStream);
        //List<String> combinedList = newStream.collect(Collectors.toList());
        //System.out.print(combinedList);

        // Find any match in the new concatenated array. If present, flag will be true; If not present, flag will be false
        boolean flag = newStream.anyMatch(s -> s.equalsIgnoreCase("Jonny"));
        System.out.println(flag);
        Assert.assertTrue(flag);
    }

    @Test
    public void streamCollect() {

        List<String> ls = Stream.of("Joseph", "Michael", "Ryan", "Joe", "Alex", "Levi", "Leticia", "Laura", "Alyssa").filter(s -> s.endsWith("a")).map(s -> s.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(ls.get(0));
    }

    @Test
    public void distinctElementsFromArray() {

        List<Integer> values = Arrays.asList(3,2,2,7,5,1,9,7);

        // Print only unique numbers from this array, then sort the array
        values.stream().distinct().sorted().forEach(s -> System.out.println("The distinct and sorted array: " + s));

        // Now return only the 3rd index of the sorted array
        List<Integer> li = values.stream().distinct().sorted().collect(Collectors.toList());
        Integer thirdIndexValue = li.get(2);
        //System.out.println(li.get(2));
        System.out.println("The 3rd index value in the distinct and sorted array is: " + thirdIndexValue);


    }

}
