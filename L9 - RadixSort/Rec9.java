import java.util.*;

public class Rec9
{
     public static void main(String [] args)
     {
        try (Scanner S = new Scanner(System.in)) {
          System.out.print("Array size > ");
          int size = S.nextInt();  S.nextLine();
          System.out.print("Max word length > ");
          int len = S.nextInt();  S.nextLine();
          String [] A = new String[size];
          String [] B = new String[size];

          // Fill arrays with random "words" of the correct
          // maximum word length.  Note that the argument to
          // randWord() is the maximum word length -- the returned
          // word will be of length between 1 and len (inclusive)
          for (int i = 0; i < A.length; i++)
          {
          	String curr = StringHelp.randWord(len);
          	String curr2 = new String(curr);
          	A[i] = curr;
          	B[i] = curr2;
          }
          
          // Time MergeSort
          long start = System.nanoTime();
          TextMergeQuick.mergeSort(A, A.length);
          long stop = System.nanoTime();
          
          // Check output for small arrays.
          if (A.length <= 50)
          {
          	for (int i = 0; i < A.length; i++)
          		System.out.print(A[i] + " ");
          	System.out.println();
          }		
          
          // Time Radix Sort
          long diff = stop - start;
          double sec = (double) diff / 1000000000;
          System.out.println("MergeSort took " + sec + " sec");
          
          start = System.nanoTime();
          RadixDemo.RadixSort(B);
          stop = System.nanoTime();
          
          // Check output for small arrays.
          if (B.length <= 50)
          {
          	for (int i = 0; i < B.length; i++)
          		System.out.print(B[i] + " ");
          	System.out.println();
          }		
          
          diff = stop - start;
          sec = (double) diff / 1000000000;
          System.out.println("Radix Sort took " + sec + " sec");
        }
	}
}
