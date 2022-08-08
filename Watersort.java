import java.util.Random;
import java.util.*;  
import java.io.IOException;
public class Watersort
{
	static Character red= new Character('r'); 
	static Character green= new Character('g'); 
	static Character blue= new Character('b');
	
	public static boolean solved( StackAsMyArrayList bottles[])
	{
		int solved= 0;//I check the number of solved bottles since my checkStackUniform method does not count empty bottles as uniform
		boolean flag = false;
		for (int i=0; i< 5; i++)
		{
			if (bottles[i].checkStackUniform())
				solved++;
		}
		
		
		if (solved == 3)//if the game is solved there should be 3 uniform bottles
			flag = true;
		
		return flag;
	}
	
	public static void ShowAll(StackAsMyArrayList[] stack)
	{
		System.out.println("\nArray of bottles:");
		for (int i = 0; i<5; i++)
			System.out.println("Bottle " + (i+1) + ": " + stack[i]);
	}
	
	public static void move(StackAsMyArrayList[] stack,int from, int to)
	{
		
	}
	
	public static void main(String[] args)
	{
		
		Random rand = new Random();
		int num;
		StackAsMyArrayList[] scrambled = new StackAsMyArrayList[5];//creating an array of bottles
		for (int i = 0; i<5; i++)
		{
			scrambled[i] = new StackAsMyArrayList<Character>();//avoiding NullpointerException
			for (int j=0; j<4; j++)
			{
				//strategy 2
				if (i == 0)
					scrambled[i].push(red);
				
				if (i == 1)
					scrambled[i].push(green);
				
				if (i == 2)
					scrambled[i].push(blue);
				
				if (i > 2)
				{
					num = rand.nextInt(3);
					if (!scrambled[num].checkStackUniform())
					{
						int prev = num;
						while(num == prev)//special case if I get the same 2 random numbers, loop until i get another number
							num = rand.nextInt(3);
					}
					scrambled[i].push(scrambled[num].pop());
				}

			}
			
		}
		
		for (int c = scrambled[2].getStackSize(); c > 0; c--)//empty bottle 3
			{
				num = rand.nextInt(2);//choose randomly which one to fill
					if (num == 0 && scrambled[0].getStackSize() == 4)
						num = 1;

					
					if (num == 1 && scrambled[1].getStackSize() == 4)
						num = 0;
					
				scrambled[num].push(scrambled[2].pop());//fill bottle 1 or 2
			}
			
		while (scrambled[2].getStackSize() < 4)
		{
			num = rand.nextInt(2);
			if (num == 0 && scrambled[0].getStackSize() == 0)//if bottle 1 is empty
				num = 1;
			
			if (num == 1 && scrambled[1].getStackSize() == 0)//bottle 2 is empty
				num = 0;
					
			scrambled[2].push(scrambled[num].pop());//fill bottle 3 from 1 and 2
		}
			
		
		System.out.println("\nWatersort puzzle 3\n==============================");
		Scanner sc = new Scanner(System.in);
		int from = 0;
		int to = 0;
		ShowAll(scrambled);
		while (!solved(scrambled))
		{
			boolean flag = true;
			
			try//try catch to read input
			{
				System.out.println("Select the pouring bottle:");
				from = sc.nextInt();
				System.out.println("Select the bottle to fill:");
				to = sc.nextInt();
				System.out.println("You chose: " + from + "," + to);
			}
			catch (Exception e)
			{
				System.out.println("Error reading from user");
			}
			
			to--;//bottle 1 is actually bottle 0 in the array
			from--;
			
			if (to == from)// if both bottles are the same
			{
				flag = false;
				System.out.println("You selected the same bottle");
			}			
			else if (scrambled[to].getStackSize() == 4)//if the bottle is full
			{
				flag = false;
				System.out.println("bottle is already full");
			}			
			else if (scrambled[from].getStackSize() == 0)//if the pouring bottle is empty
			{
				System.out.println("The pouring bottle is empty");
				flag = false;
			}
			else if (scrambled[to].getStackSize() != 0)//checks if there are objects to compareTo
			{
				if (((Comparable)scrambled[to].peek()).compareTo(scrambled[from].peek())!=0)//if the colours do not match
				{
					System.out.println("Colours not the same");
					flag = false;
				}
			}
			
			if (flag)//if all the special cases have been passed
			{
				scrambled[to].push(scrambled[from].pop());
				//keep filling if there is enough space and the colours still match
				while ((scrambled[from].getStackSize() > 0) && ((Comparable)scrambled[to].peek()).compareTo(scrambled[from].peek())==0 && scrambled[to].getStackSize() < 4)
				{
					scrambled[to].push(scrambled[from].pop());
				}
					
				ShowAll(scrambled);
				System.out.println("Puzzle solved: " + solved(scrambled));
			}		
		}
		

	}
}