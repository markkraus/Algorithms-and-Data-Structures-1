import java.util.*;

public class Rec7
{
	public static void main(String [] args)
	{
		Scanner S = new Scanner(System.in);  // Create input Scanner
		System.out.print("Array size > ");
		int size = S.nextInt();  S.nextLine(); // Read in array size
		Integer [] A = new Integer[size];
		Integer [] B = new Integer[size];
		Random R = new Random();	// Create Random object
		for (int i = 0; i < A.length; i++)  // Fill arrays with
		{									// same random values
			int val = R.nextInt();
			Integer X = Integer.valueOf(val);
			Integer Y = Integer.valueOf(val);
			A[i] = X;
			B[i] = Y;
		}
		
		// SelectionSort of random data
		SimpleSorts.selectionSort(A, A.length);
		long SSrand = SimpleSorts.comps;
		
		if (A.length <= 100)
		{
			for (int i = 0; i < A.length; i++)
				System.out.print(A[i] + " ");
			System.out.println();
		}
		
		// InsertionSort of random data
		SimpleSorts.insertionSort(B, B.length);
		long ISrand = SimpleSorts.comps;
		
		if (B.length <= 100)
		{
			for (int i = 0; i < B.length; i++)
				System.out.print(B[i] + " ");
			System.out.println();
		}
		
		// SelectionSort of sorted data
		SimpleSorts.selectionSort(A, A.length);
		long SSsort = SimpleSorts.comps;
		
		// InsertionSort of sorted data
		SimpleSorts.insertionSort(B, B.length);
		long ISsort = SimpleSorts.comps;
		
		// Reverse the data (see reverse() method)
		A = reverse(A);
		B = reverse(B);
		
		// SelectionSort of reversed data
		SimpleSorts.selectionSort(A, A.length);
		long SSrev = SimpleSorts.comps;
		
		// InsertionSort of reversed data
		SimpleSorts.insertionSort(B, B.length);
		long ISrev = SimpleSorts.comps;
		
		System.out.println("SelectionSort random: " + SSrand);
		System.out.println("SelectionSort sorted: " + SSsort);
		System.out.println("SelectionSort reversed: " + SSrev);
		System.out.println();
		System.out.println("InsertionSort random: " + ISrand);
		System.out.println("InsertionSort sorted: " + ISsort);
		System.out.println("InsertionSort reversed: " + SSrev);
	}
	
	// Method to reverse the data in an array
	public static Integer [] reverse(Integer [] A)
	{
		Integer [] temp = new Integer[A.length];
		for (int i = 0; i < A.length; i++)
			temp[i] = A[A.length-1-i];
		return temp;
	}	
}