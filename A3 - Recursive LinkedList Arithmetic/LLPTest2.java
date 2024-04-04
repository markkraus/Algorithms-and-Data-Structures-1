// Test program for your LinkedListPlus2 class


import java.util.*;

public class LLPTest2
{
	static final String [] data = {"Tori", "Sarah", "Kate", "Aimee", "Shirley",
							"Chrissy", "Debbie", "Alanis", "Courtney", "Sinead"};

	public static void main (String [] args)
	{
		// Testing constructor
		LinkedListPlus2<Integer> L1 = new LinkedListPlus2<Integer>();
		for (int i = 0; i < 10; i++)
		{
			L1.add(Integer.valueOf(i));
		}
		System.out.println("L1: " + L1.toString());

		// Testing copy constructor
		LinkedListPlus2<Integer> L2 = new LinkedListPlus2<Integer>(L1);
		System.out.println("L2: " + L2.toString());

		System.out.println("L1.leftShift(2)");
		// Testing shift and rotate methods
		L1.leftShift(2);
		System.out.println("\tL1: " + L1.toString());

		System.out.println("L2.rightShift(3)");
		L2.rightShift(3);
		System.out.println("\tL2: " + L2.toString());

		System.out.println("L1.leftRotate(4)");
		L1.leftRotate(4);
		System.out.println("\tL1: " + L1.toString());
		System.out.println("L2.rightRotate(2)");
		L2.rightRotate(2);
		System.out.println("\tL2: " + L2.toString());

		System.out.println("L2.leftShift(7)");
		L2.leftShift(7);
		System.out.println("\tL2: " + L2.toString());

		
		LinkedListPlus2<String> L3 = new LinkedListPlus2<String>();
		LinkedListPlus2<String> L4 = new LinkedListPlus2<String>();
		LinkedListPlus2<String> L5 = new LinkedListPlus2<String>();
		for (int i = 0; i < data.length; i++)
		{
			L3.add(data[i]);
			L4.add(data[i]);
			L5.add(data[i]);
		}
		System.out.println("L3: " + L3.toString());
		System.out.println("L3.leftRotate(9)");
		L3.leftRotate(9);
		System.out.println("\tL3: " + L3.toString());

		System.out.println("L4: " + L4.toString());
		System.out.println("L4.leftRotate(10)");
		L4.leftRotate(10);
		System.out.println("\tL4: " + L4.toString());

		System.out.println("L5: " + L5.toString());
		System.out.println("L5.leftRotate(11)");
		L5.leftRotate(11);
		System.out.println("\tL5: " + L5.toString());

		System.out.println("L4.rightRotate(22)");
		L4.rightRotate(22);
		System.out.println("\tL4: " + L4.toString());
		System.out.println("L4.rightRotate(-3)");
		L4.rightRotate(-3);
		System.out.println("\tL4: " + L4.toString());
		System.out.println("L4.leftRotate(-4)");
		L4.leftRotate(-4);
		System.out.println("\tL4: " + L4.toString());

		System.out.println("L4 add at front and back");
		L4.add(1, new String("Bjork"));
		L4.add(L4.getLength()+1, new String("Loreena"));
		System.out.println("\tL4: " + L4.toString());
		System.out.println("L4.rightRotate(1)");
		L4.rightRotate(1);
		System.out.println("\tL4: " + L4.toString());
	}
}
