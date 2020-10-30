/*
 Gavin Swiger
 Module 8 | Concurrency Assignment 
 October 30th 2020
  
Implement a parallel array sum, and compare performance with single thread sum. 
This is not an easy assignment – just do as much as you can and turn in what you have for partial credit.
Make an array of 200 million random numbers between 1 and 10. 
Compute the sum in parallel using multiple threads. 
Then compute the sum with only one thread, and display the sum and times for both cases.
 */

import java.util.Random;

public class Engine{
	
	static int array_length = 200000000;
	
	static Random rand = new Random();
	
	static int upperbound = 10;
	
	
	public static int[] array_generator(int arr[]) {
		
		int arr_index = 0;
		while (arr_index < arr.length) {
	
			arr[arr_index] = rand.nextInt(upperbound);			
			arr_index++;
			
		}
		return arr;
	}
	
		
	public static long get_array_sum(int arr[]) {
		long array_sum = 0;
		
		for(int i=0; i<array_length; i++) {
			
			array_sum = array_sum + arr[i];
		}
		return array_sum;
	}

	
	
	public static void main(String[] args) {
		
	//----------Array Generation-----------------------
		int[] test_arr = new int[(int) array_length];
		test_arr = array_generator(test_arr);
		
		long[] arr_total = new long[1];
		arr_total[0] = 0;
		
	//----------Single Threaded Sum and Timer-----------------------
		long single_startTime = System.nanoTime();
		long test_sum = get_array_sum(test_arr);
		long single_endTime = System.nanoTime();
		long single_totalTime = single_endTime - single_startTime;
		System.out.println("Non Threaded Array Sum: " + test_sum);
		System.out.println("Non Threaded Array RunTime: " + single_totalTime);
		
		
	//----------Multi Threaded Sum and Timer-----------------------
		long multi_startTime = System.nanoTime();
		
		//int [] arr123, int startpoint, int endpoint, long [] total
		MultiSum t1 = new MultiSum(test_arr, 0, array_length/4, arr_total);
		MultiSum t2 = new MultiSum(test_arr, array_length/4, array_length/2, arr_total);
		MultiSum t3 = new MultiSum(test_arr, array_length/2, (array_length/4)*3, arr_total);
		MultiSum t4 = new MultiSum(test_arr, (array_length/4)*3, array_length, arr_total);


		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long multi_endTime = System.nanoTime();
		long multi_totalTime = multi_endTime - multi_startTime;
		
		System.out.println("Threaded Array Sum " + arr_total[0]);
		System.out.println("Threaded Array RunTime: " + multi_totalTime);
		
	}
	


}


