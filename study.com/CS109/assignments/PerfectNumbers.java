package com.mksybr.sdc.cs109;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Long;
/*
* A perfect number is a number in which all of its divisors add up to
* that number. For example, the number 6 is perfect: 1 + 2 + 3 =
* 6. This program will make use of loops and nested loops in order to
* determine more perfect numbers.
*/
public class PerfectNumbers {
  public static void main(String[] args) {
    // Part 1: Determining Perfect Numbers between 1 and 200
    System.out.println("Part 1: Perfect numbers between 1 and 200");
    perfectNumbers(200);
    // Part 2: Allow the User to Choose an Upper Limit
    
    System.out.println("Part 2: Enter the upper limit to find perfect numbers: ");
    var limit = choose();
    perfectNumbers(limit);
  }

  public static void perfectNumbers(long limit) {
    for(var i = 1; i <= limit; i++) {
      var factors = factors(Long.valueOf(i));
      long sum = 0;
      for (Long factor : factors)
        sum += factor;
      // Check perfect number (equal to summation of it's factors)
      if(sum == i)
        System.out.printf("%d is a perfect number\n", i);
    }
  }
  
  // Get factors of number
  public static ArrayList<Long> factors(Long n) {
    ArrayList<Long> result = new ArrayList<Long>();
    for(var i = 1; i <= n/2; i++) {
      if(n % i == 0 && n != i)
        result.add(Long.valueOf(i));
    }
    return result;
  }

  // Take user input
  public static long choose() {
    var s = new Scanner(System.in);
    var limit = s.nextLong(); // if overflow -> throw exception
    return limit;
  }
}
