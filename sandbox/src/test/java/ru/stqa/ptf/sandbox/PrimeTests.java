package ru.stqa.ptf.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Sungatullina on 16.04.2017.
 */
public class PrimeTests {

  @Test

  public void testPrime () {
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }

  @Test(enabled = false)

  public void testPrimeLong () {
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }

  @Test

  public void testNonPrimes () {
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE-2));
  }

}
