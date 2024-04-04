public class ArrayBagPractice
{	
	public static <T> void addItems(BagInterface<T> B, T [] data)
	{
		for (T val: data)
			B.add(val);
	}
	
	public static <T> void showItems(BagInterface<T> B)
	{
		T [] items = B.toArray();
		for (T x: items)
			System.out.print(x + " ");
		System.out.println();
	}
		
	public static void main(String [] args)
	{
		String [] data1 = {"A", "B", "C", "D", "B"};
		String [] data2 = {"F", "G", "H", "B", "D"};
		
		BagInterface<String> bag1 = new ResizableArrayBag<String>();
		BagInterface<String> bag2 = new ResizableArrayBag<String>();
		addItems(bag1, data1);
		addItems(bag2, data2);

		System.out.println("B1 contains:");
		showItems(bag1);
		System.out.println();
		System.out.println("B2 contains:");
		showItems(bag2);
		System.out.println();
		
		BagInterface<String> bag3 = bag1.union(bag2);
		BagInterface<String> bag4 = bag1.intersection(bag2);
		BagInterface<String> bag5 = bag1.difference(bag2);
		BagInterface<String> bag6 = bag2.difference(bag1);

		System.out.println("B1 union B2 contains:");
		showItems(bag3);
		System.out.println();
		System.out.println("B1 intersect B2 contains:");
		showItems(bag4);
		System.out.println();
		System.out.println("B1 diff B2 contains:");
		showItems(bag5);
		System.out.println();
		System.out.println("B2 diff B1 contains:");
		showItems(bag6);
		System.out.println();
		
		BagInterface<String> bag7 = bag3.union(bag1);
		System.out.println("(B1 union B2) union B1 contains:");
		showItems(bag7);
		System.out.println();
		
		BagInterface<String> bag8 = bag3.intersection(bag1);
		System.out.println("(B1 union B2) intersect B1 contains:");
		showItems(bag8);
		System.out.println();
		
		BagInterface<String> bag9 = bag3.difference(bag1);
		System.out.println("(B1 union B2) difference B1 contains:");
		showItems(bag9);
		System.out.println();
		
		BagInterface<String> bag10 = bag1.difference(bag3);
		System.out.println("B1 difference (B1 union B2) contains:");
		showItems(bag10);
	}
}
