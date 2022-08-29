/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dagitik;

/**
 *
 * @author velik
 */
/*
* @author maktas
*/
import edu.rit.pj.ParallelRegion;
import edu.rit.pj.ParallelTeam;
public class PrimeTestParallel {
/**
* only test odd divisors.
* even numbers can not be primes
* start with 3 and test with every odd number: 3, 5, 7, 9,
* go up to the square root of the tested number
* the numbers larger than the square root can not divide be sole
divisors
*/private static boolean isPrime(long x) {
if (x % 2 == 0) {
return false;
}
long p = 3;
long xsqrt = (long) Math.ceil(Math.sqrt(x));
while (p <= xsqrt) {
if (x % p == 0) {
return false;
}
p += 2;
}
return true;
}
static long start;
static long startTimes[];
static long endTimes[];
//static long numbers[] = {1000000000000037L, 1000000000000091L};
 static long numbers[] = {1000000000000037L, 1000000000000091L,
1000000000000159L, 1000000000000187L};
public static void main(String[] args) throws Exception {
start = System.currentTimeMillis();
startTimes = new long[numbers.length];
endTimes = new long[numbers.length];
ParallelTeam team = new ParallelTeam(numbers.length);
team.execute(new ParallelRegion() {
public void run() {
int i = getThreadIndex();
startTimes[i] = System.currentTimeMillis();
isPrime(numbers[i]);
endTimes[i] = System.currentTimeMillis();
}
});
for (int i = 0; i < numbers.length; i++) {
System.out.println(i + " start: "+ (startTimes[i]-start) );
System.out.println(i + " end: "+ (endTimes[i]-start) );
}
}
}