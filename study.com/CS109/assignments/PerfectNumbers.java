package com.mksybr.sdc.cs109;

/*
* A perfect number is a number in which all of its divisors add up to
* that number. For example, the number 6 is perfect: 1 + 2 + 3 =
* 6. This program will make use of loops and nested loops in order to
* determine more perfect numbers.
*/
public class PerfectNumbers {
  public static void main(String[] args) {
    for(int i = 0; i <= 200; i++) {
      var factors = factors(i);
      for(int j = 0; j < 1; j++) {
        // Check perfect number (equal to summation of it's factors)
        if(factors.stream().reduce(0, (acc, elem) -> acc + elem) == i) {
          System.out.printf("%d is a perfect number\n", i);
        }
      }
    }
  }
  // Get factors of number
  public static ArrayList<Integer> factors(Integer n) {
    ArrayList<Integer> result = new ArrayList<Integer>();
    for(int i = 1; i <= n; i++) {
      if(n % i == 0 && n != i)
        result.add(i);
    }
    return result;
  }
}
