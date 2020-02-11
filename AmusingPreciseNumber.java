package cs228hw2;

import java.io.IOException;
import java.io.Reader;

/**
 * Class that uses the AmusingLinkedList implementation to store precise numbers and be able to add,
 * subtract, negate, and find the absolute value
 * @author Zach Eisele
 *
 */
public class AmusingPreciseNumber 
{
	//AmusingLinkedList used to store all the digits
	public AmusingLinkedList<Object> all=new AmusingLinkedList<Object>();
	
	//checks if the number is negative without having to have a negative sign in the all
	public boolean isNegative;
	
	/*
	 * constructor from a given int
	 * @return AmusingPreciseNumber
	 * @param number to be converted
	 */
	public AmusingPreciseNumber(int numb)
	{
		String tmp=Integer.toString(numb);
		
		//notes that the number is negative but does not add the - sign to avoid complications
		if(tmp.charAt(0)=='-')
		{
			isNegative=true;
		}
		
		//adds first number if no negative sign
		else
		{
			String c=""+tmp.charAt(0);
			int first=Integer.parseInt(c);
			all.add(first);
		}
		
		
		if (tmp.length()>1)
		{
			//goes through each digit and adds it to the all as a String
			for (int i=1; i<tmp.length(); i++)
			{
				String c="" + tmp.charAt(i);
				int number=Integer.parseInt(c);
				all.add(number);
			}
		}
		
		//if the number is negative inserts - sign
		if(isNegative)
		{
			all.add(0, "-");
		}
		
	}
	
	/*
	 * constructor from a given String
	 * @return AmusingPreciseNumber
	 * @param String to be converted
	 */
	public AmusingPreciseNumber(String numb)
	{
		String tmp=numb;
		
		//decimal counter to check for validity
		int decCnt=0;
		 
		//notes the sign
		if(tmp.charAt(0)=='-')
		{
			isNegative=true;
		}
		else if(tmp.charAt(0)=='+')
		{
			isNegative=false;
		}
		else
		{
			String c=""+tmp.charAt(0);
			
			//tries to parse int and if failed checks if it is a decimal point
			try
			{
				int number=Integer.parseInt(c);
				all.add(number);
			}
			catch(NumberFormatException e)
			{
				if (c.contains("."))
				{
					decCnt++;
					all.add(c);
				}
				else
				{
					throw e;
				}
			}
		}
		
		
		if (tmp.length()>1)
		{
			
			//attempts to put a digit in and catches if not, lets one decimal go anything else will throw an error
			for (int i=1; i<tmp.length(); i++)
			{
				int number;
				String c="" +tmp.charAt(i);
				try 
				{
					number=Integer.parseInt(c);
					all.add(number);
					
				}
				catch(NumberFormatException e)
				{
					if (c.contains("."))
					{
						decCnt++;
						if (decCnt>1)
						{
							throw new NumberFormatException("The number is not valid");
						}
						all.add(c);
					}
					else
					{
						throw e;
					}

				}
				
			}
		}
		if(isNegative)
		{
			all.add(0, "-");
		}
		
	}
	
	/*
	 * constructor given from a reader
	 * @return AmusingPreciseNumber
	 * @param reader
	 */
	public AmusingPreciseNumber(Reader r) throws IOException
	{
		
		int data=r.read();
		
		//used to perform a check for sign
		boolean isFirst=true;
		
		//current digit
		char c=(char) data;
		
		//decimal count
		int decCnt=0;
		
		
		while (c!='\r')
		{
			if(isFirst)
			{
				//checks for signs
				if(c=='-')
				{
					isNegative=true;
					data=r.read();
					c=(char) data;
					isFirst=false;
					continue;
				}
				else if(c=='+')
				{
					isNegative=false;
					data=r.read();
					c=(char) data;
					isFirst=false;
					continue;
				}
				else
				{
					String s=""+c;
					try
					{
						int number=Integer.parseInt(s);
						all.add(number);
						isFirst=false;
						data=r.read();
						c=(char) data;
						continue;
					}
					
					//catches a decimal and inserts if it is the first
					catch(NumberFormatException e)
					{
						if (s.contains("."))
						{
							decCnt++;
							if(decCnt>1)
							{
								throw e;
							}
							all.add(s);
							isFirst=false;
							data=r.read();
							c=(char) data;
							continue;
						}
						else
						{
							throw e;
						}
					}
				}
				
			}
			
			//current digit in int form
			int number;
			
			//String version of the char being tested
			String s= "" +c;
			try 
			{
				number=Integer.parseInt(s);
				all.add(number);
				data=r.read();
				c=(char) data;
				
			}
			catch(NumberFormatException e)
			{
				//white space will terminate the process
				if(s.equals(" "))
				{
					break;
				}
				if (s.contains("."))
				{
					decCnt++;
					if (decCnt>1)
					{
						throw new NumberFormatException("The number is not valid");
					}
					String dec=""+c;
					all.add(dec);
					data=r.read();
					c=(char) data;
				}
				else
				{
					throw e;
				}
	
				
			}
		}
		if(isNegative)
		{
			all.add(0, "-");
		}
	}
	
	/*
	 * creates a deep copy of a given all
	 * @return AmusingPreciseNumber
	 * @param AmusingPreciseNumber to be copied
	 */
	public AmusingPreciseNumber(AmusingPreciseNumber numb)
	{
		for (int i=0; i<numb.all.size(); i++)
		{
			all.add(numb.all.get(i));
		}
		isNegative=numb.isNegative;
	}
	
	/*
	 * helper method that lines up the two numbers(by decimal or last digits) so that they might be operated on
	 * @param number to be lined up with
	 */
	private void lineUp(AmusingPreciseNumber numb)
	{
		//decimal index of this object
		int decIdxthis;
		
		//decimal index of the given numb
		int decIdxNumb;

		//if both numbers have decimals
		if(all.contains(".")&&numb.all.contains("."))
		{
			decIdxthis=all.indexOf(".");
			decIdxNumb=numb.all.indexOf(".");
			
			//if this all has decimal later in the number compared to the numb's decimal
			if(decIdxthis>decIdxNumb)
			{
				//adds 0 to the all(that do not change the number) until the decimals are lined up
				while(decIdxthis!=decIdxNumb)
				{
					numb.all.add(0, 0);
					decIdxNumb=numb.all.indexOf(".");
				}
			}
			
			//if they are equal or added number has decimal later
			else
			{
				//adds 0 to the all(that do not change the number) until the decimals are lined up
				while(decIdxthis!=decIdxNumb)
				{
					all.add(0, 0);
					decIdxthis=all.indexOf(".");
				}	
			}
		}
		
		//if this all is the only one with a decimal
		else if(all.contains("."))
		{
			//index of the number before(to the left of) the decimal
			int idxB4dec=all.indexOf(".")-1;
			
			//index of the last number in the numb's all
			int idxlast=numb.all.size()-1;
			
			//if the number before the decimal's index is later than the last number of the numb, tested 
			if(idxB4dec>idxlast)
			{
				//ads a decimal so they may be lined up
				numb.all.add(".");
				int deciIdxthis=all.indexOf(".");
				int deciIdxnumb=numb.all.indexOf(".");
				
				//adds 0(that do not change the number) until the decimals are lined up
				while(deciIdxthis!=deciIdxnumb)
				{
					numb.all.add(0,0);
					deciIdxnumb=numb.all.indexOf(".");
				}
			}
			
			//if the number before the decimal's index is equal to or earlier than the last number of numb, not tested
			else
			{
				numb.all.add(".");
				
				int deciIdxthis=all.indexOf(".");
				int deciIdxnumb=numb.all.indexOf(".");
				
				//adds 0(that do not change the number) until the decimals are lined up
				while(deciIdxthis!=deciIdxnumb)
				{	
					all.add(0, 0);
					deciIdxthis=numb.all.indexOf(".");
				}
			}
		}
		
		//same as above but numb contains the decimal and this all does not
		else if(numb.all.contains("."))
		{
			all.add(".");
			int deciIdxthis=all.indexOf(".");
			int deciIdxnumb=numb.all.indexOf(".");
			
			//if numb's last digit before decimal is later than the last of this all last digit, tested
			if(deciIdxthis>deciIdxnumb)
			{
				while(deciIdxthis!=deciIdxnumb)
				{
					numb.all.add(0, 0);
					deciIdxnumb=numb.all.indexOf(".");
				}
				
				
			}
			
			//if numb's last digit before decimal is equal or earlier than last digit of this all, tested
			else
			{
				while(deciIdxthis!=deciIdxnumb)
				{	
					all.add(0, 0);
					deciIdxthis=numb.all.indexOf(".");
				}
			}
		}
		
		//if both numbers do not have decimals
		else
		{
			int idxlastNumb=numb.all.size()-1;
			int idxlastthis=all.size()-1;
			
			//if numbs amount of digits is larger than this all, tested
			if (idxlastNumb>idxlastthis)
			{
				while(idxlastNumb!=idxlastthis)	
				{	
					all.add(0,0);
					idxlastthis=all.size()-1;
				}
			}
			
			//if the amount of digits is equal or this all has more, tested
			else
			{
					while(idxlastNumb!=idxlastthis)	
					{	
						numb.all.add(0,0);
						idxlastNumb=numb.all.size()-1;
					}
			}
		}
		
		//corrects the size of number with decimals to add zeroes that do not change value of the number for adding sake
		if(numb.all.size()!=all.size())
		{
			if(numb.all.size()>all.size())
			{
				while(numb.all.size()!=all.size())
				{
					all.add(0);
				}
				
			}
			else
			{
				while(numb.all.size()!=all.size())
				{
					numb.all.add(0);
				}
			}
		}
	}
	
	
	/*
	 * helper method that adds the lined up numbers
	 * @param number to be added
	 */
	private void adder(AmusingPreciseNumber numb)
	{
		// goes from least significant digit to most
		for(int i=numb.all.size()-1; i>=0; i--)
		{
			//takes the objects from the all and changes them to a string
			Object numbO=numb.all.get(i);
			Object thisO=all.get(i);
			String numbS=numbO.toString();
			String thisS=thisO.toString();
			
			//if successful, it means it was an int, if not tests if it is a decimal point
			try
			{
				int numbI=Integer.parseInt(numbS);
				int thisI=Integer.parseInt(thisS);
				int answer=numbI+thisI;
				
				//carries a 1 if the two numbers are greater than 9
				if(answer>9)
				{
					//adds a 1 to the all because the carry needs to be represented
					if(i==0)
					{
						answer=answer-10;
						all.set(i, answer);
						all.add(0, 1);
						continue;
					}
					
					//tries to add 1 to the next line of addition
					Object higher=all.get(i-1);
					String higherS=higher.toString();
					int higherI;
					try 
					{
						higherI=Integer.parseInt(higherS);
						all.set(i-1, higherI+1);
						
					}
					catch(NumberFormatException e)
					{
						//if there is a decimal, the 1 is carries over to the next number after the decimal
						if(higherS.contains("."))
						{
							if(i-2>=0)
							{
								Object higher2=all.get(i-2);
								String higher2S=higher2.toString();
								int higher2I;
								higher2I=Integer.parseInt(higher2S);
								all.set(i-2, higher2I+1);
							}
							else
							{
								all.add(0, 1);
							}
								
						}
					}
					
					//takes away the carried 1(or 10 for its position)
					answer=answer-10;
				}
				all.set(i, answer);
			}
			catch(NumberFormatException e)
			{
				if (numbS.contains(".")&&thisS.contains("."));
				{
					all.set(i, ".");
				}
			}
		}
	}
	
	/*
	 * helper method that subtracts lined up numbers
	 * @param number to be subtracted with
	 */
	private void subtractor(AmusingPreciseNumber numb)
	{
		
		// goes from least significant digit to most
		for(int i=numb.all.size()-1; i>=0; i--)
		{
			//takes the objects from the all and changes them to a string
			Object numbO=numb.all.get(i);
			Object thisO=all.get(i);
			String numbS=numbO.toString();
			String thisS=thisO.toString();
			
			//if successful, it means it was an int, if not tests if it is a decimal point
			try
			{
				int numbI=Integer.parseInt(numbS);
				int thisI=Integer.parseInt(thisS);
				int answer=thisI-numbI;
				
				//borrows a 1(or ten for the line of digits if the two numbers subtracted is less than 0
				if(answer<0)
				{
					//sets the number as positive at the first node, and negates the number
					if(i==0)
					{
						answer=Math.abs(answer);
						all.set(i, answer);
						if(!isNegative)
						{
							isNegative=true;
						}
						else
						{
							isNegative=false;
						}
						continue;
					}
					
					//tries to subtract 1 to the next line of addition
					Object higher=all.get(i-1);
					String higherS=higher.toString();
					int higherI;
					
					try 
					{
						higherI=Integer.parseInt(higherS);
						all.set(i-1, higherI-1);
						thisI=thisI+10;
						answer=thisI-numbI;
						
					}
					
					//if there is a decimal, the 1 is taken from the next number after the decimal
					catch(NumberFormatException e)
					{
						if(higherS.contains("."))
						{
							if(i-2>=0)
							{
								Object higher2=all.get(i-2);
								String higher2S=higher2.toString();
								int higher2I;
								higher2I=Integer.parseInt(higher2S);
								thisI=thisI+10;
								answer=thisI-numbI;
								all.set(i-2, higher2I-1);
							}
							else
							{
								all.add(0, 1);
							}
								
						}
					}
				}
				all.set(i, answer);
			}
			catch(NumberFormatException e)
			{
				if (numbS.contains(".")&&thisS.contains("."));
				{
					all.set(i, ".");
				}
			}
		}
	}
	
	/*
	 * helper method to compare two lined up numbers
	 * returns 1 if the object that called the method is larger
	 * returns 0 if the two numbers are equal
	 * returns -1 if the given number is larger than the object called
	 * @return result
	 * @param number to compare
	 */
	private int compareTo(AmusingPreciseNumber numb)
	{
		Object thisO;
		Object numbO;
		
		//variable to show if the object that called the method is larger, equal to, or smaller than the given number
		int thisIsLarger=0;
		
		//goes through both number from most significant digit to least
		for(int i=0; i<all.size(); i++)
		{
			if(all.get(i)!="."&&numb.all.get(i)!=".")
			{
				thisO=all.get(i);
				numbO=numb.all.get(i);
				String thisS=thisO.toString();
				String numbS=numbO.toString();
				int firstRealThis = 0;
				int firstRealNumb=0;
				
				//numbers to track when there is a difference in value
				try 
				{
					firstRealThis=Integer.parseInt(thisS);
					firstRealNumb=Integer.parseInt(numbS);
				}
				catch(NumberFormatException e)
				{
					if(thisS.contains(".")&& numbS.contains("."))
					{
						continue;
					}
				}
				
				
				//declares the object larger if it comes across the instance where the object has the first larger number   
				if(firstRealThis>firstRealNumb)
				{
					thisIsLarger=1;
					return thisIsLarger;
				}
				
				//declares the gievn number larger if it comes across the instance where the given n8umber has the first larger number
				else if(firstRealThis<firstRealNumb)
				{
					thisIsLarger=-1;
					return thisIsLarger;
				}
				
				//if both numbers are the same the loop continues to try to find a difference
				else
				{
					continue;
				}
			}
		}
		return thisIsLarger;
	}
	
	/*
	 * method that adds a given number to the number that called the method
	 * @param number to be added with
	 */
	public void add(AmusingPreciseNumber numb)
	{
		if(all.get(0).equals("-"))
		{
			all.remove(0);
		}
		if(numb.all.get(0).equals("-"))
		{
			numb.all.remove(0);
		}
		lineUp(numb);
		
		//if there are no negative numbers just add them
		if(!isNegative&&!numb.isNegative)
		{
			adder(numb);
		}
		
		//if the given number is negative
		else if(!isNegative&&numb.isNegative)
		{
			//if the given number's absolute value is larger than the object's
			if(compareTo(numb)<0)
			{
				//swap the two numbers so the subtractor can be called(adding a negative is subtracting)
				AmusingPreciseNumber tmp= new AmusingPreciseNumber(this);
				all.clear();
				for(int i=0; i<numb.all.size(); i++)
				{
					all.add(numb.all.get(i));
				}
				
				//absolute values need to be subtracted
				isNegative=false;
				numb.isNegative=false;
				
				subtractor(tmp);
				
				//the number must be negative because the negative number was larger
				isNegative=true;
				
				if(isNegative)
				{
					all.add(0, "-");
				}
			}
			
			//if the given number is less
			else if(compareTo(numb)>0)
			{
				subtractor(numb);
				
				//the positve number was larger so must be positive
				isNegative=false;
			}
			
			//the two numbers are the same so the answer is 0
			else
			{
				for(int i=0; i<all.size(); i++)
				{
					all.set(i, 0);
					isNegative=false;
				}
			}
		}
		
		//if this number is negative and the given number is not
		else if(isNegative&&!numb.isNegative)
		{
			//if this number is larger
			if(compareTo(numb)>0)
			{
				subtractor(numb);
				
				//the negative number is larger so the answer must be negative
				isNegative=true;
				if(isNegative)
				{
					all.add(0, "-");
				}
			}
			
			//if the given number is larger
			else if(compareTo(numb)<0)
			{
				//swap the numbers to subtract
				AmusingPreciseNumber tmp= new AmusingPreciseNumber(this);
				all.clear();
				for(int i=0; i<numb.all.size(); i++)
				{
					all.add(numb.all.get(i));
				}
				isNegative=false;
				numb.isNegative=false;
				
				subtractor(tmp);
				
				//the positive number is larger so must be positive
				isNegative=false;
			}
			
			//numbers are the same so the answer is 0
			else
			{
				for(int i=0; i<all.size(); i++)
				{
					all.set(i, 0);
					isNegative=false;
				}
			}
				
		}
		
		//no negatives, just add
		else 
		{
			adder(numb);
			isNegative=true;
			if(isNegative)
			{
				all.add(0, "-");
			}
		}
	}
	
	/*
	 * method that subtracts a number from the number that called the method
	 * @param number to subtract with
	 */
	public void subtract(AmusingPreciseNumber numb)
	{
		if(isNegative)
		{
			all.remove(0);
		}
		if(numb.isNegative)
		{
			numb.all.remove(0);
		}
		lineUp(numb);
		
		//both numbers are negative, subtracting a negative is adding a positive and a negative. That is a case that can be done in add
		if(isNegative&&numb.isNegative)
		{
			numb.isNegative=false;
			add(numb);
			if(isNegative&&all.get(0)!="-")
			{
				all.add(0, "-");
			}
		}
		
		//the given number is the only negative, subtracting a negative is just adding, just use the adder
		else if(!isNegative&&numb.isNegative)
		{
			numb.isNegative=false;
			adder(numb);
			if(isNegative)
			{
				all.add(0, "-");
			}
		}
		
		//a positive number subtracted from a negative number is just adding a number and making it negative
		else if(isNegative&&!numb.isNegative)
		{
			adder(numb);
			isNegative=true;
			if(isNegative)
			{
				all.add(0, "-");
			}
		}
		
		//both positive just subtract
		else
		{
			numb.isNegative=true;
			add(numb);
		}

	}
	
	/*
	 * method that switches the signs of the number
	 */
	public void negate()
	{
		if(isNegative)
		{
			isNegative=false;
			if(!isNegative&&all.get(0).equals("-")) 
			{
				all.remove(0);
			}
		}
		else
		{
			isNegative=true;
			if(isNegative)
			{
				all.add(0, "-");
			}
		}
	}
	
	/*
	 * method that makes the number positive
	 */
	public void abs()
	{
		if(isNegative)
		{
			isNegative=false;
			if(!isNegative&&all.get(0).equals("-"))
			{
				all.remove(0);
			}
		}
	}
	
	/*
	 * method that returns the sum of two number
	 * does not change either number
	 * @return sum
	 * @param first number
	 * @param second number
	 */
	public static AmusingPreciseNumber add(AmusingPreciseNumber n1, AmusingPreciseNumber n2)
	{
		AmusingPreciseNumber numb1=n1;
		AmusingPreciseNumber numb2=n2;
		
		if(numb1.all.get(0).equals("-"))
		{
			numb1.all.remove(0);
		}
		if(numb2.all.get(0).equals("-"))
		{
			numb2.all.remove(0);
		}
		numb1.lineUp(numb2);
		
		//if there are no negative numbers just add them
		if(!numb1.isNegative&&!numb2.isNegative)
		{
			numb1.adder(numb2);
		}
		
		//if the given number is negative
		else if(!numb1.isNegative&&numb2.isNegative)
		{
			//if the given number's absolute value is larger than the object's
			if(numb1.compareTo(numb2)<0)
			{
				//swap the two numbers so the subtractor can be called(adding a negative is subtracting)
				AmusingPreciseNumber tmp= new AmusingPreciseNumber(numb1);
				numb1.all.clear();
				for(int i=0; i<numb2.all.size(); i++)
				{
					numb1.all.add(numb2.all.get(i));
				}
				
				//absolute values need to be subtracted
				numb1.isNegative=false;
				numb2.isNegative=false;
				
				numb1.subtractor(tmp);
				
				//the number must be negative because the negative number was larger
				numb1.isNegative=true;
				
				if(numb1.isNegative)
				{
					numb1.all.add(0, "-");
				}
			}
			
			//if the given number is less
			else if(numb1.compareTo(numb2)>0)
			{
				numb1.subtractor(numb2);
				
				//the positve number was larger so must be positive
				numb1.isNegative=false;
			}
			
			//the two numbers are the same so the answer is 0
			else
			{
				for(int i=0; i<numb1.all.size(); i++)
				{
					numb1.all.set(i, 0);
					numb1.isNegative=false;
				}
			}
		}
		
		//if this number is negative and the given number is not
		else if(numb1.isNegative&&!numb2.isNegative)
		{
			//if this number is larger
			if(numb1.compareTo(numb2)>0)
			{
				numb1.subtractor(numb2);
				
				//the negative number is larger so the answer must be negative
				numb1.isNegative=true;
				if(numb1.isNegative)
				{
					numb1.all.add(0, "-");
				}
			}
			
			//if the given number is larger
			else if(numb1.compareTo(numb2)<0)
			{
				//swap the numbers to subtract
				AmusingPreciseNumber tmp= new AmusingPreciseNumber(numb1);
				numb1.all.clear();
				for(int i=0; i<numb2.all.size(); i++)
				{
					numb1.all.add(numb2.all.get(i));
				}
				numb1.isNegative=false;
				numb2.isNegative=false;
				
				numb1.subtractor(tmp);
				
				//the positive number is larger so must be positive
				numb1.isNegative=false;
			}
			
			//numbers are the same so the answer is 0
			else
			{
				for(int i=0; i<numb1.all.size(); i++)
				{
					numb1.all.set(i, 0);
					numb1.isNegative=false;
				}
			}
				
		}
		
		//no negatives, just add
		else 
		{
			numb1.adder(numb2);
			numb1.isNegative=true;
			if(numb1.isNegative)
			{
				numb1.all.add(0, "-");
			}
		}
		return numb1;
	}
	

	
	/*
	 * method that subtracts two numbers
	 * does not change either number
	 * @return difference
	 * @param number 1
	 * @param number 2
	 */
	public static AmusingPreciseNumber subtract(AmusingPreciseNumber n1, AmusingPreciseNumber n2)
	{
		AmusingPreciseNumber numb1=n1;
		AmusingPreciseNumber numb2=n2;
		
		if(numb1.isNegative)
		{
			numb1.all.remove(0);
		}
		if(numb2.isNegative)
		{
			numb2.all.remove(0);
		}
		numb1.lineUp(numb2);
		
		//both numbers are negative, subtracting a negative is adding a positive and a negative. That is a case that can be done in add
		if(numb1.isNegative&&numb2.isNegative)
		{
			numb2.isNegative=false;
			numb1.add(numb2);
			if(numb1.isNegative&&numb1.all.get(0)!="-")
			{
				numb1.all.add(0, "-");
			}
		}
		
		//the given number is the only negative, subtracting a negative is just adding, just use the adder
		else if(!numb1.isNegative&&numb2.isNegative)
		{
			numb2.isNegative=false;
			numb1.adder(numb2);
			if(numb1.isNegative)
			{
				numb1.all.add(0, "-");
			}
		}
		
		//a positive number subtracted from a negative number is just adding a number and making it negative
		else if(numb1.isNegative&&!numb2.isNegative)
		{
			numb1.adder(numb2);
			numb1.isNegative=true;
			if(numb1.isNegative)
			{
				numb1.all.add(0, "-");
			}
		}
		
		//both positive just subtract
		else
		{
			numb2.isNegative=true;
			numb1.add(numb2);
			if(numb1.isNegative)
			{
				numb1.all.add(0, "-");
			}
		}
		
		return numb1;
	}
	
	/*
	 * negates a given number
	 * @return negated number
	 * @param number to negate
	 */
	public static AmusingPreciseNumber negate(AmusingPreciseNumber n)
	{
		AmusingPreciseNumber n1= n;
		if(n1.isNegative)
		{
			n1.isNegative=false;
			if(!n1.isNegative&&n1.all.get(0).equals("-")) 
			{
				n1.all.remove(0);
			}
		}
		else
		{
			n1.isNegative=true;
			if(n1.isNegative)
			{
				n1.all.add(0, "-");
			}
		}
		return n1;
	}
	
	/*
	 * takes the absolute value of a given number
	 * @return absolute value
	 * @param number
	 */
	public static AmusingPreciseNumber abs(AmusingPreciseNumber numb)
	{
		AmusingPreciseNumber n1=numb;
		
		if(n1.isNegative)
		{
			n1.isNegative=false;
			if(!n1.isNegative&&n1.all.get(0).equals("-"))
			{
				n1.all.remove(0);
			}
		}
		return n1;
	}
	
	/*
	 * puts the AmusingPreciseNumber into an understandable string
	 * @return String representation(if can be made)
	 */
	public String toString()
	{
		String result = "";
		for(int i=0; i<all.size(); i++)
		{
			result=result+all.get(i);
		}
		return result;
	}

}