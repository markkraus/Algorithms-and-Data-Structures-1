// CS 0445 Spring 2022
//
// Testing of simple queue with iterator
// This program should run without changes with 
// your PrimQ1WithIterator<T> class
// Note: You also need file QueueWithIteratorInterface.java to run this
//       program

import java.util.*;
public class Rec10
{
	public static void main(String [] args)
	{
		QueueWithIteratorInterface<Integer> Q1 = new PrimQ1WithIterator<Integer>(5);

		for (int i = 0; i < 5; i++)
		{
			Integer newItem = Integer.valueOf(2 * i);
			System.out.println("Adding " + newItem + " to Queue");
			Q1.enqueue(newItem);
		}
		
		Iterator<Integer> eye1 = Q1.iterator();
		while (eye1.hasNext())
		{
			Integer currOne = eye1.next();
			System.out.println("Outer value: " + currOne);
			Iterator<Integer> eye2 = Q1.iterator();
			System.out.print("\tInner values: ");
			while (eye2.hasNext())
			{
				Integer currTwo = eye2.next();
				System.out.print(currTwo + " ");
			}
			System.out.println();
		}
	}
}
		