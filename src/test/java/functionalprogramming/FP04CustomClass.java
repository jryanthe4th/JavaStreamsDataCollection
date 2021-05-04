package functionalprogramming;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Course {
    private String name;
    private String category;
    private int reviewScore;
    private int noOfStudents;

    public Course(String name, String category, int reviewScore, int noOfStudents) {
        this.name = name;
        this.category = category;
        this.reviewScore = reviewScore;
        this.noOfStudents = noOfStudents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }

    public int getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(int noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public String toString() {
        return name + ":" + noOfStudents + ":" + reviewScore;
    }
}

public class FP04CustomClass {

    public static void main(String[] args) {
        List<Course> courses = List.of(
                new Course("Spring", "Framework", 98, 20000),
                new Course("Spring Boot", "Framework", 95, 25000),
                new Course("API", "Microservices", 97, 20500),
                new Course("Microservices", "Microservices", 96, 22000),
                new Course("Fullstack", "Fullstack", 94, 21000),
                new Course("AWS", "Cloud", 99, 22000),
                new Course("Azure", "Cloud", 91, 27000),
                new Course("Docker", "Cloud", 93, 25000),
                new Course("Kubernetes", "Cloud", 91, 22000)
        );

        // allMatch, noneMatch, anyMatch
        Predicate<Course> reviewScoreGreaterThan95Predicate = course -> course.getReviewScore() > 95;
        Predicate<Course> reviewScoreGreaterThan90Predicate = course -> course.getReviewScore() > 90;
        Predicate<Course> reviewScoreLessThan90Predicate = course -> course.getReviewScore() < 90;

        System.out.println(
                courses.stream().allMatch(reviewScoreGreaterThan95Predicate));

        System.out.println(
                courses.stream().noneMatch(reviewScoreLessThan90Predicate));

        System.out.println(
                courses.stream().anyMatch(reviewScoreLessThan90Predicate));

        System.out.println(
                courses.stream().anyMatch(reviewScoreGreaterThan95Predicate));

        Comparator<Course> comparingByNoOfStudentsIncreasing = Comparator.comparingInt(Course::getNoOfStudents);

        Comparator<Course> comparingByNoOfStudentsDecreasing = Comparator.comparingInt(Course::getNoOfStudents).reversed();

        Comparator<Course> comparingByNoOfStudentsAndNoOfReviews = Comparator.comparingInt(Course::getNoOfStudents)
                .thenComparing(Course::getReviewScore)
                .reversed();

        System.out.println(
                courses.stream()
                        .sorted(comparingByNoOfStudentsIncreasing)
                        .collect(Collectors.toList()));

        System.out.println(
                courses.stream()
                        .sorted(comparingByNoOfStudentsDecreasing)
                        .collect(Collectors.toList()));

        System.out.println(
                courses.stream()
                .sorted(comparingByNoOfStudentsAndNoOfReviews)
                .collect(Collectors.toList()));

        // Only pickup first 5 courses
        System.out.println(
                courses.stream()
                        .sorted(comparingByNoOfStudentsAndNoOfReviews)
                        .limit(5)
                        .collect(Collectors.toList()));

        // Skip first 3 results
        System.out.println(
                courses.stream()
                        .sorted(comparingByNoOfStudentsAndNoOfReviews)
                        .skip(3)
                        .collect(Collectors.toList()));

        // Skip first 3 results, then get the next 5
        System.out.println(
                courses.stream()
                        .sorted(comparingByNoOfStudentsAndNoOfReviews)
                        .skip(3)
                        .limit(5)
                        .collect(Collectors.toList()));

        // dropWhile - once it finds an element that matches, it will take all subsequent elements
        System.out.println(
                courses.stream()
                        .dropWhile(course -> course.getReviewScore() >= 95)
                        .collect(Collectors.toList())); //[Fullstack:21000:94, AWS:22000:99, Azure:27000:91, Docker:25000:93, Kubernetes:22000:91]

        // max - returns last element using primitive function
        System.out.println(
                courses.stream()
                        .max(comparingByNoOfStudentsAndNoOfReviews)); //Optional[Spring:20000:98]

        // min - returns first element using primitive function
        System.out.println(
                courses.stream()
                        .min(comparingByNoOfStudentsAndNoOfReviews)); //Optional[Azure:27000:91]

        // Handling empty values
        System.out.println(
                courses.stream()
                        .filter(reviewScoreLessThan90Predicate)
                        .min(comparingByNoOfStudentsAndNoOfReviews)
                        .orElse(new Course("Kubernetes", "Cloud", 91, 20000))); //Kubernetes:20000:91

        // findFirst
        System.out.println(
                courses.stream()
                        .filter(reviewScoreLessThan90Predicate)
                        .findFirst()); //Optional.empty

        // findFirst
        System.out.println(
                courses.stream()
                        .filter(reviewScoreGreaterThan95Predicate)
                        .findFirst()); //Optional[Spring:20000:98]

        // findAny
        System.out.println(
                courses.stream()
                        .filter(reviewScoreGreaterThan95Predicate)
                        .findAny()); //Optional[Spring:20000:98]

        // Find sum of students enrolled in courses with reviewScoreGreaterThan95Predicate
        System.out.println(
                courses.stream()
                        .filter(reviewScoreGreaterThan95Predicate)
                        .mapToInt(course -> course.getNoOfStudents())
                        .sum()); //84500

        // Find average of students enrolled in courses with reviewScoreGreaterThan95Predicate
        System.out.println(
                courses.stream()
                        .filter(reviewScoreGreaterThan95Predicate)
                        .mapToInt(course -> course.getNoOfStudents())
                        .average()); //OptionalDouble[21125.0]

        // Find count of courses with reviewScoreGreaterThan95Predicate
        System.out.println(
                courses.stream()
                        .filter(reviewScoreGreaterThan95Predicate)
                        .mapToInt(course -> course.getNoOfStudents())
                        .count()); //4

        System.out.println(
                courses.stream()
                    .collect(Collectors.groupingBy(Course::getCategory)));
                    //{Cloud=[AWS:22000:99, Azure:27000:91, Docker:25000:93, Kubernetes:22000:91],
                    // Fullstack=[Fullstack:21000:94],
                    // Microservices=[API:20500:97, Microservices:22000:96],
                    // Framework=[Spring:20000:98, Spring Boot:25000:95]}

        System.out.println(
                courses.stream()
                        .collect(Collectors.groupingBy(Course::getCategory, Collectors.counting())));
                        //{Cloud=4, Fullstack=1, Microservices=2, Framework=2}

        System.out.println(
                courses.stream()
                        .collect(Collectors.groupingBy(Course::getCategory, Collectors.maxBy(Comparator.comparing(Course::getReviewScore)))));
                        //{Cloud=Optional[AWS:22000:99], Fullstack=Optional[Fullstack:21000:94], Microservices=Optional[API:20500:97], Framework=Optional[Spring:20000:98]}

        System.out.println(
                courses.stream()
                        .collect(Collectors.groupingBy(Course::getCategory, Collectors.mapping(Course::getName, Collectors.toList()))));
                            //{Cloud=[AWS, Azure, Docker, Kubernetes], Fullstack=[Fullstack], Microservices=[API, Microservices], Framework=[Spring, Spring Boot]}

    }
}
