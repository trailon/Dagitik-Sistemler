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
/**
* Primality test with threads and without using Parallel Java library
*
* @author maktas
*/
public class PrimeTestThreads extends Thread{
int index;
public PrimeTestThreads(int index){
this.index = index;
}public void run() {
startTimes[index] = System.currentTimeMillis();
PrimeTestParallel02.isPrime(numbers[index]);
endTimes[index] = System.currentTimeMillis();
}
/**
* only test odd divisors.
* even numbers can not be primes
* start with 3 and test with every odd number: 3, 5, 7, 9,
* go up to the square root of the tested number
* the numbers larger than the square root can not divide be sole
divisors
*/
public static boolean isPrime(long x) {
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
PrimeTestThreads threadler[] = new
PrimeTestThreads[numbers.length];
for (int i = 0; i < threadler.length; i++) {
threadler[i] = new PrimeTestThreads(i);
}
for (int i = 0; i < threadler.length; i++) {
threadler[i].start();
}
// wait until all threads are done
boolean notDone = true;
while(notDone){
notDone = false;
for (int i = 0; i < threadler.length; i++) {
if(threadler[i].isAlive())