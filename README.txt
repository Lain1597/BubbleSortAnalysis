The course project I chose to work on involves benchmarking the behavior of Java implementation of the bubble sort algorithm. This task is done by comparing its recursive version to its iterative version. The first part of this course project was about writing the code to perform the benchmarking of the bubble algorithm; now in this part, we are analyzing the results obtained. Before doing this, let’s talk first about those two versions of bubble sort algorithm. 
Bubble sort, sometimes incorrectly referred to as sinking sort, is a simple (not complicated to understand and implement) sorting algorithm that works by repeatedly stepping through the list to be sorted, comparing each pair of adjacent items and swapping them if they are in the wrong order. The pass through the list is repeated until no swaps are needed, which indicates that the list is sorted. The algorithm gets its name from the way smaller elements "bubble" to the top of the list. 

Here are two versions of the bubble sort algorithm (recursive and iterative) implemented in Java:

//Recursive bubble sort obtained from the week 4 Conference proposed by Professor Jarc

private static long recursiveBubble(long[] array, int start, int n, long count1)
{
	    if (n >= array.length)
	         return 0;
	    for (int i = start; i + n < array.length; i += n * 2){
	         sortTwo(array, i, i + n);
	        count1++;//Count the critical operation which is the call of sortTwo followed by the recursive call
	     }// end for
	    //recursive call	          
	    recursiveBubble(array, start, n * 2, count1);
	    return count1;
	 }// end recursiveBubble



private static void sortTwo(long[] array, int left, int right)
	   {
	      if (array[left] > array[right])
	      {
	         long temp = array[left];
	         array[left] = array[right];
	         array[right] = temp;
	      }
	   }// end sortTwo





// Iterative version of bubble sort taken in "Introduction to Java Programming" Daniel Liang 8th Edition
	   public static long iterativeBubble(long [] list){
		   boolean needNextPass = true;  long count2 = 0;
		   for ( int k=1; k < list.length && needNextPass; k++){
			   //Array may be sorted and next pass not needed
			   needNextPass = false;
			   for ( int i=0; i < list.length - k; i++){
				   if (list[i] > list[i+1]){
					   //Swap list[i] with list[i+1]
					   long temp = list[i];
					   list[i] = list[i+1];
					   list[i+1] = temp;
					   count2++; // count the critical operation which is the comparison followed by the swapping of two values
					   needNextPass = true;//Next pass still needed
				   }// end if
			   }// end for
		   }// end for
		   return count2;
	   }// end iterativeBuble



	Let’s take a look first at the recursive version; the first line highlighted in bright green (call of sortTwo method) is a macro operation and also considered here as the critical operation because it contents the key elements of the bubble sort mechanism which is the comparison (highlighted line in yellow) followed by the swapping of two values. The Big-? in the worst case for the recursive Bubble Sort is in ?(n2) time. For the iterative version, the line highlighted in bright green which is the comparison of two values in the list is considered here as the critical operation because it is the first fundamental operation in the Bubble Sort mechanism followed by the swapping of two values. Also, Bubble Sort only uses comparisons to operate on elements; it is then a comparison sort. The Big-? in the worst case for the iterative Bubble Sort is in also ?(n2) time. 

	The results from the figure 1 below, which are the output of our implementation in the Course Project part 1 are the ones used to generate the graphs in figure 2, figure 3 and figure 4. The x-axis (n) represents the size of the arrays and the y-axis (ns) the average time execution in nanoseconds. 









Figure 1: Output of our program (attached to this document).





 
Figure 2: Iterative Bubble Sort Benchmark
 
Figure 3: Recursive Bubble Sort Benchmark
  
Figure 4: Comparison side-by-side of the Iterative and Recursive Bubble Sort Benchmark

When we look at the figure 4 above which is a comparison side-by-side of the Iterative and Recursive Bubble Sort Benchmark, we can say that for data set sizes less than 100, the iterative and the recursive versions have approximately the same execution time. However, for data set sizes greater than 100 the recursive version consumes significantly more time than the iterative version. Although this comparison is done in nanoseconds, the greater the data set sizes are (i.e. 400,600, 1000, 5000,…) the more the difference will be in time execution.  
When we look at the figure 1, we can say that the average operation count for the iterative version is slightly smaller than the recursive version for every single data set size. Also, when considering the average execution time, the iterative version and the recursion version are very closed at the beginning, and then different with the recursive version on top as the data set size becomes bigger. The standard deviation of count for the iterative version shows that during the 50 runs, the critical operation counts were close to the average critical operation counts. The standard deviation of time for the iterative version shows that during the 50 runs, the execution times were not very close to the average execution times. The standard deviation of count for the recursive version which is zero, shows that during the 50 runs the critical operation counts remained constant (the same) whatever the position of the elements in the lists. The standard deviation of time for the recursive version shows that during the 50 runs, the execution times were not very close to the average execution times. When we take a look of the figure 4, the growth here is almost the same as the function f(n)=n2,  for n > 0; recall, the complexity is ?(n2) time. We can say that our Big-? is very close to the output of our program.
To summarize, we can say that when the data set size is small the iterative and recursive versions of Bubble Sort behave nearly the same way in term of execution time. For large data set sizes, the recursive version consumes more time than the iterative version. Although bubble sort is among the simplest well-known sorting algorithms, its ?(n2) complexity means that its efficiency decreases dramatically on lists of more than a small number of elements. Even though the algorithm is simple, there are better algorithms such as Merge Sort (O(n log n)) more efficient for sorting large lists.
