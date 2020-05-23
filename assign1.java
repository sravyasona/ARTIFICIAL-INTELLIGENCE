
import java.util.*;
import java.util.Scanner;
@SuppressWarnings("unchecked")
public class assignment {
static String A1;
static String A2;


// It checks parentheses for the given output, which is called the main for each input string

public static String parentheses(String S) 
{
	Scanner scanner=new Scanner(System.in);
	
	// Checking parentheses whether they are same to the given inputs.
	
	int OpenParenthesesCounter=0;
	int ClosedParenthesesCounter=0;
	for(int y=0;y<S.length();y++) 
	{
	    if(S.charAt(y)=='(') 
	    {
	    	OpenParenthesesCounter++;
	    	
	    }
	    else if(S.charAt(y)==')')
	    {
	    	ClosedParenthesesCounter++;
	    }
	}
	while(ClosedParenthesesCounter!=OpenParenthesesCounter)
	{
		System.out.println("Open and Closed parentheses are not same, Please enter the Inputs again");
		
		S=scanner.nextLine();
		
		for( int y=0;y<S.length();y++)
		{
		    if(S.charAt(y)=='(')
		    {
		    	OpenParenthesesCounter++;
		    }else if(S.charAt(y)==')')
		    {
		    	ClosedParenthesesCounter++;
		    }
		}
	}
	while(S.startsWith("("))  //indicated that first input should be open Parentheses
	{
		System.out.println("Please enter the correct Expression");
		S=scanner.nextLine();
	}
	return A1;
}
//It convert the input string into a list 

public static List<String> Converttolist(String S)
{
	List<String> term = new LinkedList<String>();
	int i=0,j=0;
	if (S.length()==1)
	{
		term.add(S);
	} 
	else
	{
		for (i=0;i<S.length();i++) 
		{
			if (S.charAt(i)=='('||S.charAt(i)==')'||S.charAt(i)==',') 
			{
				term.add(S.substring(j,i));
				term.add(S.substring(i,i+1));
				j = i + 1;
			}
		}
		if (j<i) 
		{
			term.add(S.substring(j));
		}
	}
	return term;
}
static List Finalizedlist=new LinkedList();

//unify_algorithm method performs the unify_with_occurs_check functionality in prolog

public static void unify_algorithm(String A1, String S2)
{
	String temp_exp1=null;
	String temp_exp2=null;
	String addList_Value=null;
	int i=0,j=0;
	A1=A1.replaceAll("\\s", "");    		
	A2=A2.replaceAll("\\s", "");
	if(A1.equals("")||A2.equals(""))   		//checking whether the input condition is empty
	{
		System.out.println("Please enter the inputs");
		return;
	}
	while (i<Converttolist(A1).size()&&j<Converttolist(S2).size())
	{
		String Value1=(String) Converttolist(A1).get(i);
		String Value2=(String) Converttolist(A2).get(j);
		if (!Value1.equals(Value2)) 
		{
			boolean constant=false;
			
			// Displays error message when the input is in upper case letters
			
			
			if (Value1.toUpperCase().equals(Value1)&& Value2.toUpperCase().equals(Value2)) 								
			{
				if (Converttolist(A1).size()>i+1) 
				{
					if (Converttolist(A1).get(i+1).equals("(")) 
					{
						System.out.println("Funtion names should be always in lower case in first input");
						return;
					}
				}
				if (Converttolist(A2).size()>j+1) 
				{
					if (Converttolist(A2).get(j+1).equals("("))
					{
						System.out.println("Funtion names should be always in lower case in second input");
						return;
					}
				}
				
				//Replacing the value1 with previous temporary values.
				
				A1 = A1.replaceAll(Value1, Value2);
				A2 = A2.replaceAll(Value1, Value2);
				if (!Finalizedlist.isEmpty())
				{
					List list_temp = new LinkedList();
					Iterator itr1 = Finalizedlist.iterator();
					while (itr1.hasNext()) 
					{
						String eq = (String) itr1.next();
						itr1.remove();
						String temp[] = eq.split(" = ");
						temp[1] = temp[1].replaceAll(Value1, Value2);
						String modified = temp[0] + " = " + temp[1];
						list_temp.add(modified);
					}
					Finalizedlist.addAll(list_temp);
				}
				addList_Value = Value1 + " = " + Value2; 	
				Finalizedlist.add(addList_Value);

			}
			if (!Value1.toUpperCase().equals(Value1)&& !Value2.toUpperCase().equals(Value2))
			{
				System.out.println("Given Expressions cannot be Unified");
				return;
			}
			if (!Value1.toUpperCase().equals(Value1))			//checking with value1,replacing and adding to the list
			{
				int k = -1;
				String func = "";
				if (Converttolist(A1).size() > i + 1) 
				{
					if (Converttolist(A1).get(i + 1).equals("("))
					{
						k = i;
						while (!(Converttolist(A1).get(k).equals(")"))) 
						{
							func = func + Converttolist(A1).get(k);
							k++;
						}
						func = func + ")";
					} 
					else 
					{
						constant = true;          					
					}
				} 
				else 
				{
					constant = true;								
				}
				
				if (Value2.toUpperCase().equals(Value2))				
				{ 			
					if (Converttolist(A2).size() > j + 1) 
					{
						if (Converttolist(A2).get(j + 1).equals("("))
						{
							System.out.println("Funtion names always should be in lower case ");
							return;
						}
					}

					if (constant == false)
					{
						if (func.contains(Value2)) 
						{			 
							A1 = temp_exp1;
							A2 = temp_exp2;
							System.out.println("Given Expressions cannot be unified");
							return;
						}
						else
						{
							A1 = A1.replaceAll(Value2, func);  
							A2 = A2.replaceAll(Value2, func);
							if (!Finalizedlist.isEmpty())
							{ 		
								List list_temp = new LinkedList();
								Iterator itr1 = Finalizedlist.iterator();
								while (itr1.hasNext()) 
								{
									String eq = (String) itr1.next();
									itr1.remove();
									String temp[] = eq.split("=");
									temp[1] = temp[1].replaceAll(Value2,func);
									String modified = temp[0] + " = "+ temp[1];
									list_temp.add(modified);
								}
								Finalizedlist.addAll(list_temp);
							}
							addList_Value = Value2 + " = " + func;
							Finalizedlist.add(addList_Value);
						}
					}
					else
					{
						A1 = A1.replaceAll(Value2, Value1);		
						A2 = A2.replaceAll(Value2, Value1);
						if (!Finalizedlist.isEmpty()) 
						{			
							List list_temp = new LinkedList();
							Iterator itr1 = Finalizedlist.iterator();
							while (itr1.hasNext())
							{
								String eq = (String) itr1.next();
								itr1.remove();
								String temp[] = eq.split("=");
								temp[1] = temp[1].replaceAll(Value2,Value1);
								String modified = temp[0] + " = " + temp[1];
								list_temp.add(modified);
							}
							Finalizedlist.addAll(list_temp);
						}
						addList_Value = Value2 + " = " + Value1;	
						Finalizedlist.add(addList_Value);
					}
				}
				if (k != -1)
					i = k + 1;
			}
			
			if (!Value2.toUpperCase().equals(Value2))					//checking with the value2, replacing and adding to final list
			{			
				int k = -1;
				String func = "";
				if (Converttolist(A2).size() > j + 1) 
				{
					if (Converttolist(A2).get(j + 1).equals("("))
					{
						k = j;
						while (!(Converttolist(A2).get(k).equals(")")))
						{
							func = func + Converttolist(A2).get(k);
							k++;
						}
						func = func + ")";
					} 
					else
					{
						constant = true;
					}
				}
				else 
				{
					constant = true;
				}
				if (Value1.toUpperCase().equals(Value1)) 			
				{		
					if (Converttolist(A1).size() > i + 1) 
					{
						if (Converttolist(A1).get(i + 1).equals("("))
						{
							System.out.println("Function names should always be in lower case");
							return;
						}
					}
					temp_exp1 = A1;
					temp_exp2 = A2;
					if (constant == false)
						
						
					{
						if (func.contains(Value1))
						{
							System.out.println("Given Expressions cannot be unified");
							return;
						} 
						else
						{
							A1 = A1.replaceAll(Value1, func);			
							A2 = A2.replaceAll(Value1, func);
							if (!Finalizedlist.isEmpty())
							{				
								List list_temp = new LinkedList();
								Iterator itr1 = Finalizedlist.iterator();
								while (itr1.hasNext())
								{
									String eq = (String) itr1.next();
									itr1.remove();
									String temp[] = eq.split("=");
									temp[1] = temp[1].replaceAll(Value1,func);
									String modified = temp[0] + " = "+ temp[1];
									list_temp.add(modified);
								}
								Finalizedlist.addAll(list_temp);
							}
							
							addList_Value = Value1 + " = " + func;			
							Finalizedlist.add(addList_Value);
						}
					} 
					else
					{
						A1 = A1.replaceAll(Value1, Value2);				
						A2 = A2.replaceAll(Value1, Value2);
						   if (!Finalizedlist.isEmpty())
						   {					
							   List list_temp = new LinkedList();
							   Iterator itr1 = Finalizedlist.iterator();
							   while (itr1.hasNext())
							   {
								   String eq = (String) itr1.next();
								   itr1.remove();
								   String temp[] = eq.split("=");
								   temp[1] = temp[1].replaceAll(Value1,Value2);
								   String modified = temp[0] + " = " + temp[1];
								   list_temp.add(modified);
							   }
							   Finalizedlist.addAll(list_temp);
						   }
						addList_Value = Value1 + " = " + Value2;			// adding replacing values to final output
						Finalizedlist.add(addList_Value);

					}
				}
				if (k != -1)
					j = k + 1;
			}
			i = 0;
			j = 0;
		}
		else
		{
			i++;
			j++;
		}

	}
	Iterator itr = Finalizedlist.iterator();
	while (itr.hasNext())
	{
		System.out.println(itr.next());
	}
	System.out.println("\n"+"Given Expression is unified");
}
public static void main(String args[]) {
	Scanner scan = new Scanner(System.in);
	System.out.println("Please enter the first expression");
	A1 = scan.nextLine();
	System.out.println("Please enter the second expression");
	A2 = scan.nextLine();
	scan.close();
	parentheses(A1);
	parentheses(A2);
	unify_algorithm(A1, A2);
}
}

   