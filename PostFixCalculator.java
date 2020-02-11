package cs228hw2;

import java.util.Deque;
import java.util.Scanner;

/**
 * class that contains a main method that operates as a post fix calculator
 * @author Zach Eisele
 *
 */
public class PostFixCalculator 
{
	/*
	 * main method uses a deque to operate a post fix calculator
	 */
	@SuppressWarnings("resource")
	public static void main(String args[])
	{
		Deque<AmusingPreciseNumber> memory= new Deque228<AmusingPreciseNumber>();


		Scanner in= new Scanner(System.in);
		String input=in.nextLine();
		
		Scanner cl= new Scanner(input);
		String cur;
		
		AmusingPreciseNumber op1;
		AmusingPreciseNumber op2;
		
		//goes until there is no more information
		while(cl.hasNext())
		{
			cur=cl.next();
			
			//if the data is a plus sign, it takes the two numbers from the stack and adds them and pushes the answer back on the stack  
			if(cur.equals("+"))
			{
				op1=memory.pop();
				op2=memory.pop();
				op1.add(op2);
				memory.push(op1);
			}
			
			//if the data is a minus sign, it takes the two numbers from the stack and subtracts them and pushes the answer back on the stack
			else if(cur.equals("-"))
			{
				op1=memory.pop();
				op2=memory.pop();
				op2.subtract(op1);
				memory.push(op2);
			}
			
			//if the data is neg, the first number is popped of the deque, negated, then pushed back on
			else if(cur.equals("neg"))
			{
				op1=memory.pop();
				AmusingPreciseNumber.negate(op1);
				memory.push(op1);
			}
			
			//if the data is abs, the first number is popped of the deque, gives the absolute value, then pushed back on
			else if(cur.equals("abs"))
			{
				op1=memory.pop();
				AmusingPreciseNumber.abs(op1);
				memory.push(op1);
			}
			
			//any other input is either a number or an illegal argument
			else
			{
				try
				{
					AmusingPreciseNumber numb= new AmusingPreciseNumber(cur);
					memory.push(numb);
				}
				catch(NumberFormatException e)
				{
					throw new IllegalArgumentException("Invalid input");
				}
			}
			
		}
		
		in.close();
		cl.close();
		
		//remaining number on the stack is the answer
		AmusingPreciseNumber result=memory.pop();
		
		System.out.println(result.toString());
		
	}
	
	
	
}