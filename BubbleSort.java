/**
//**********************************************************************************
// Name of the file: BubbleSort.java  
// Author: Patrice M. Ngassa
// Date: September 16,2012
// Instructor: Prof. Duane Jarc
// Class: CMSC451
// Project Part#1 
// Purpose: Application that gives and/or compares the average execution time, the average   
//		    critical operation count, the standard deviation of count, and the standard  
//		    deviation of time of the iterative and recursive versions of the 
//			Bubble Sort Algorithm.
//********************************************************************************
 */

import javax.swing.*;
import java.io.*;
import java.awt.*;



public class BubbleSort extends JFrame {
	
	final static int MIN = 2; // constant variable
	final static int MAX = 4; // constant variable
	final static int N = 10; // constant variable
	final static int M = 50; // constant variable
	final static int O = 5; // constant variable
	
	
	// Create an instance of javax.swing.JTextArea control
			JTextArea txtConsole = new JTextArea(M,M);
	
	// create a scrolling panel			
	private JScrollPane scrollPane = new JScrollPane(txtConsole);
	
	public static void main(String[] args) {
		BubbleSort frame = new BubbleSort();		
		frame.setTitle("Project CMSC451 - Part1"); // title of the frame
		frame.setSize(800,650); // size of the frame
		frame.setLocationRelativeTo(null); // Center the frame
		// exit the program when clicking on close button
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		// display the frame so it can be seen
		frame.setVisible(true);	
	} // end main
	
	
	 public BubbleSort() // constructor
	   {
	      
		     JPanel grid = new JPanel(); // create a JPanel
		    
		    grid.setLayout(new GridLayout(1, 1));
		    
		 // Now create a new TextAreaOutputStream to write to our JTextArea control and wrap a
			// PrintStream around it to support the println/printf methods.
		    TextAreaOutputStream text = new TextAreaOutputStream( txtConsole );
		    
		    		    
			PrintStream out = new PrintStream( text );
			
			
			// redirect standard output stream to the TextAreaOutputStream
			System.setOut( out );
			

			// redirect standard error stream to the TextAreaOutputStream
			System.setErr( out );
			
					    		     		
		     //add a scroll pane to hold JTextArea into the frame
			grid.add(scrollPane,BorderLayout.CENTER);
		            
		    add(grid);
		    
		  							 
	      int dataSetSize[] = new int [N];// array of the data set sizes
	      long resultsRec[][] = new long[N][O]; // array of the results for recursive algorithm
	      long resultsIte[][] = new long[N][O]; // array of the results for iterative algorithm
	      
	    		  
	      for (int i = 0 ; i < N ; i++){	// create 10 random data set sizes and populate the array
	    	  dataSetSize [i] = getRandom(MIN*(i+1),MAX*(i+1))*(i+1);
	      }// end for
	      System.out.println("The data set sizes are the followings: ");
	      for (int value: dataSetSize)// print the data set sizes
		         System.out.print(value + " ");
		      System.out.println();
		  
		  for (int j = 0; j < N; j++){    // 10 arrays
			  
			  long timeRec[] = new long[M];// array of time execution for recursive algorithm
		      long timeIte[] = new long[M];// array of time execution for iterative algorithm
		      long critiCountRec[] = new long[M]; // array of critical operation count for recursive algorithm
		      long critiCountIte[] = new long[M]; // array of critical operation count for iterative algorithm
		      
		      
			  for (int k = 0; k < M; k++){ // 50 runs
				  long array[] = new long [dataSetSize [j]];
				  long array1[] = new long [dataSetSize [j]];
				  long countOperatRec = 0; // count the critical operation for recursive version
				  long countOperatIte = 0; // count the critical operation for iterative version
				  long count0 = 0;  // help to count the critical operation for recursive version
				  for (int i = 0 ; i < dataSetSize [j] ; i++){ // populate the array
				      array [i] = (int) (Math.random () * dataSetSize [j]);
				      array1[i] = array[i];
				  } // end for
				 
				  // Recursive call part
			  		System.out.println("Elements in the array "+ (j+1)+" : ");    
		     	    for (long value: array)
			         System.out.print(value + " ");
			        System.out.println();
			  
			        System.out.println("Begin sorting using recusrsive version");
		      		long startTime1 = System.nanoTime();
		      		for (int start = 0; start < array.length - 1; start++){
		         	 long temp = recursiveBubble(array, start, 1,count0);
		         	 countOperatRec = countOperatRec + temp;
		      		}// end for 
		      		
		      		long endTime1 = System.nanoTime();
		      		for (long value: array) // verify that the data set has been properly sorted
			         System.out.print(value + " ");
		      		System.out.println();
		      		long t1 = endTime1-startTime1; // execution time recursive version
		      		timeRec[k] = t1; // save execution time
		      		critiCountRec[k] = countOperatRec;// save the critical operation count
		      		System.out.println("Total execution time for Recursive Bubble Sort for the array "+ (j+1)+" :  "+t1 +" ns");
			
			  	    // Iterative call part	
		     		 for (long value: array1)
			         System.out.print(value + " ");
			     	 System.out.println();
			     	 System.out.println("Begin sorting using iterative version");
			     	 long startTime2 = System.nanoTime();
			     	 countOperatIte = iterativeBubble(array1);
			     	 long endTime2 = System.nanoTime();
			     	 for (long value: array1)
			         System.out.print(value + " ");
			     	 System.out.println();
			     	 long t2 = endTime2-startTime2; // execution time iterative version
			     	 timeIte[k] = t2; // save execution time
			     	 critiCountIte[k] = countOperatIte; // save the critical operation count
			     	 System.out.println("Total execution time for Iterative Bubble Sort for the array "+ (j+1)+" : "+ t2+" ns");
			  }// end for - 50 runs
			  
			  resultsRec[j][0] = dataSetSize[j]; // data set size - recursive version
			  resultsIte[j][0] = dataSetSize[j]; // data set size - iterative version
				  
			  resultsRec[j][1] = (long)mean(critiCountRec); // average critical operation count - recursive version
			  resultsIte[j][1] = (long)mean(critiCountIte); // average critical operation count - iterative version
				  
			  resultsRec[j][2] = (long)stddev(critiCountRec);// standard deviation of count -  recursive version
			  resultsIte[j][2] = (long)stddev(critiCountIte);// standard deviation of count - iterative version
			  
			  resultsRec[j][3] = (long)mean(timeRec);// average execution time -  recursive version
			  resultsIte[j][3] = (long)mean(timeIte);// average execution time - iterative version
					  
			  resultsRec[j][4] = (long)stddev(timeRec);// standard deviation of time -  recursive version
			  resultsIte[j][4] = (long)stddev(timeIte);// standard deviation of time - iterative version
			  
		  }// end for - creation and test of all the arrays  
		  
		  
		  System.out.println();
		  System.out.println("Data Set 			Iterative Bubble Sort (Time in nano seconds)"); 
		  System.out.println("Size n		 Avg Critical		Standard Deviation  	Average Execution 	Standard Deviation");	
	 	  System.out.println("		Operation Count	of Count		   Time		of Time"); 	 	 	 	 
	 	  for (int i = 0; i < N; i++){
	 		  for (int j = 0; j < O; j++)
	 			  System.out.print(resultsIte[i][j] + "		");
	 		  System.out.println();
	 	  }// end for
	 	 System.out.println();
	 	System.out.println("Data Set 			Recursive Bubble Sort (Time in nano seconds)"); 
		  System.out.println("Size n		 Avg Critical		Standard Deviation  	Average Execution 	Standard Deviation");	
	 	  System.out.println("		Operation Count	of Count		   Time		of Time"); 	 	 	 	 
	 	  for (int i = 0; i < N; i++){
	 		  for (int j = 0; j < O; j++)
	 			  System.out.print(resultsRec[i][j] + "		");
	 		  System.out.println();
	 	  }// end for 
	   }// end constructor
	   
	   public static int getRandom(int min, int max)
	    {
	       return (int) (Math.random() * (max - min + 1) ) + min;
	    }
	   // Recursive bubble sort obtained from the week 4 Conference proposed by Professor Jarc
	   private static long recursiveBubble(long[] array, int start, int n, long count1)
	   {
	      if (n >= array.length)
	         return 0;
	      for (int i = start; i + n < array.length; i += n * 2)
	         sortTwo(array, i, i + n);
	      count1++; // Count the critical operation which is the recursive call
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
	   
	   // The following functions come from the website http://introcs.cs.princeton.edu/java/stdlib/StdStats.java.html
	   // Proposed in the Project Conference by Leah Rachel Belin
	    /**
	     * Return sum of all values in array.
	     */
	    public static long sum(long[] a) {
	        long sum = 0;
	        for (int i = 0; i < a.length; i++) {
	            sum += a[i];
	        }
	        return sum;
	    }
	    
	    /**
	     * Return average value in array, NaN if no such value.
	     */
	    public static double mean(long[] a) {
	        if (a.length == 0) return Double.NaN;
	        long sum = sum(a);
	        return sum / a.length;
	    }
	    
	    /**
	     * Return sample variance of array, NaN if no such value.
	     */
	    public static double var(long[] a) {
	        if (a.length == 0) return Double.NaN;
	        double avg = mean(a);
	        long sum = 0;
	        for (int i = 0; i < a.length; i++) {
	            sum += (a[i] - avg) * (a[i] - avg);
	        }
	        return sum / (a.length - 1);
	    }
	    	    
	    /**
	     * Return sample standard deviation of array, NaN if no such value.
	     */
	    public static double stddev(long[] a) {
	        return Math.sqrt(var(a));
	    }
	    
	  // Iterative version of bubble sort taken in "Introduction to Java Programming" Daniel Liang
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
					   count2++; // count the critical operation which is the swapping of two values
					   needNextPass = true; // Next pass still needed
				   }// end if
			   }// end for
		   }// end for
		   return count2;
	   }// end iterativeBuble
}// end class BubbleSort
