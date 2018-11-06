package com.codecool;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Streams {
    public static void main(String[] args) {
        /*
         * Create a stream out of the following array.
         */

        String[] a1 = {"one", "two", "three"};
        Stream<String> s1 = Arrays.stream(a1);
        s1.forEach(s-> System.out.println(s));

        /*
         * Create a stream containing the Strings "one" , "two" and "three"
         * without using an array
         */

        Stream<String> s2 = Stream.of(a1);
        s2.forEach(s-> System.out.println(s));

        /*
         * Create a stream using a stream builder.
         */

        Stream.Builder<String> b1 = Stream.builder();
        b1.add("one");
        b1.add("two");
        b1.add("three");
        Stream<String> s3 = b1.build();
        s3.forEach(s-> System.out.println(s));

        /*
         * Collect one of the above defined streams into a list.
         *
         * HINT: the keyword here is "collect"
         */

        Stream<String> sNew = Stream.of(a1);
        List<String> l1 = sNew.collect(Collectors.toList());
        l1.forEach(l-> System.out.println(l));


        /*
         * Streams can be infinite.  We obviously cannot create such a
         * stream by supplying all the elements ourselves.
         *
         * HINT: When looking for methods to create such streams
         * realize that:
         * - These methods cannot belong to an instance
         * - They are returning streams
         */

        /*
         * Create a stream of the powers of two.
         */

        Integer twoToTheZeroth = 1;
        UnaryOperator<Integer> doubler = (Integer x) -> 2 * x;
        Stream<Integer> s4 = Stream.iterate(twoToTheZeroth, doubler).limit(20);

        /*
         * Create a stream containing the first ten elements of s4.
         */

        Stream<Integer> s5 = s4.limit(10);
        s5.forEach(s-> System.out.println(s));

        /*
         * Create a stream containing the elements of the Fibonacci
         * sequence.
         *
         * HINT: You will need to create a new class for this.
         */

        Supplier<Integer> fibSupp = new Fibonacci();
        Stream<Integer> s6Temp = Stream.generate(fibSupp);
        Stream<Integer> s6 = Stream.concat(Stream.of(0, 1), s6Temp.limit(20));
        s6.forEach(System.out::println);
    }

    private static class Fibonacci implements Supplier<Integer> {
        private Integer firstNumber = 0;
        private Integer secondNumber = 1;

        @Override
        public Integer get() {
            Integer nextNumber = firstNumber + secondNumber;
            firstNumber = secondNumber;
            secondNumber = nextNumber;
            return nextNumber;
        }

    }
}
