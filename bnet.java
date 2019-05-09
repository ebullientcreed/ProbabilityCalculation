import java.io.*;
import java.util.*;
import java.lang.*;
import java.text.NumberFormat;
//Computes and prints out the probability of any combination of events given any other combination of events.
public class bnet{
	//Given values in bayesian network
	static double p_b=0.001,p_nb=0.999,p_e=0.002,p_ne=0.998;
	static double p_a_be=0.95,p_a_bne=0.94,p_a_nbe=0.29,p_a_nbne=0.001;
	static double p_j_a=0.9,p_j_na=0.05,p_m_a=0.7,p_m_na=0.01;
	//To store the set of symbols and truth values  
	public static HashSet<Character> set=new HashSet<Character>();
	static HashMap<Character,Boolean> comp=new HashMap<Character,Boolean>();
	static HashMap<Character,Boolean> givcomp=new HashMap<Character,Boolean>();
	
	//Method to compute function should return the joint probability of the five events,
	// for the model with truth values to each symbols
	public static double computeProbability(Boolean b,Boolean e,Boolean a,Boolean j,Boolean m){
		double pb,pe,pa,pj,pm,jp;
		if(b==true){
			pb=p_b;
		}
		else{
			pb=p_nb;
		}
		if(e==true){
			pe=p_e;
		}
		else{
			pe=p_ne;
		}
		if(b==true && e==true){
			if(a==true){
				pa=p_a_be;
			}
			else{
				pa=1-p_a_be;
			}
		}
		else if(b==true && e==false){
			if(a==true){
				pa=p_a_bne;
			}
			else{
				pa=1-p_a_bne;
			}
		}
		else if(b==false && e==true){
			if(a==true){
				pa=p_a_nbe;
			}
			else{
				pa=1-p_a_nbe;
			}
		}
		else{
			if(a==true){
				pa=p_a_nbne;
			}
			else{
				pa=1-p_a_nbne;
			}
		}
		if(j==true){
			if(a==true){
				pj=p_j_a;
			}
			else{
				pj=p_j_na;
			}
		}
		else{
			if(a==true){
				pj=1-p_j_a;
			}
			else{
				pj=1-p_j_na;
			}
		}
		if(m==true){
			if(a==true){
				pm=p_m_a;
			}
			else{
				pm=p_m_na;
			}
		}
		else{
			if(a==true){
				pm=1-p_m_a;
			}
			else{
				pm=1-p_m_na;
			}
		}
		//Computing the joint probability
		jp=pb*pe*pa*pj*pm;
		return jp;
	}
	//Function assigning truth value for each symbols and then for each model,
	//computing the probability by invoking the computeProbability
	public static double assignVal(HashSet symb, HashMap<Character,Boolean> model,Boolean deno){
		//If symbol set is empty , ie is model assigned values to each symbol then
		if(symb.isEmpty()){
			int k=0;
			Boolean b=true,e=true,a=true,j=true,m=true;
			b=model.get('B');
			e=model.get('E');
			a=model.get('A');
			j=model.get('J');
			m=model.get('M');
			double ires=computeProbability(b,e,a,j,m);
			return ires;
		}	
		else {
			Character p=null;
			int c=0;
			// set to hold the rest(symbols)
			HashSet<Character> rest=new HashSet<Character>();
			Iterator<Character> k=symb.iterator();
			while(k.hasNext()){
				if(c==0){
					//the first element is extracted to p
					p=k.next();
				}
				else{
					//rest in to set rest
					rest.add(k.next());
				}
				c++;
			}
			//If conditional probability 
			if(deno==true){
				if(givcomp.containsKey(p)){
					//set the model with p as given through command line
					model.put(p,givcomp.get(p));
					//and recursively call assignVal with p as given through command line in each model
					return assignVal(rest,model,deno);
				}
				else{
					//else recursively call assignVal with p set as true and adding with p set as false
					return (assignVal(rest,EXTEND(p,true,model),deno))+(assignVal(rest,EXTEND(p,false,model),deno));
				}
			}
			//If not conditional probability 
			else{
				if(comp.containsKey(p)){
					//set the model with p as given through command line
					model.put(p,comp.get(p));
					//and recursively call assignVal with p as given through command line in each model
					return assignVal(rest,model,deno);
				}
				else{
					//else recursively call assignVal with p set as true and adding with p set as false
					return (assignVal(rest,EXTEND(p,true,model),deno))+(assignVal(rest,EXTEND(p,false,model),deno));
				}
			}			
		}
	}	
	//method to add the value of p to the model by extending
	public static HashMap<Character,Boolean> EXTEND(Character p,Boolean value, HashMap<Character,Boolean> model){
		model.put(p,value);
		return model;
	}
	public static void main(String args[]){
		try{
			//bnet takes 1 to 6(no more, no fewer) command line arguments
			if(args[0]!=null && args.length!=0 && args.length<=6){
				String[] c1=new String[args.length];
				Boolean given=false;
				int j=0;
				set.add('B');
				set.add('E');
				set.add('A');
				set.add('J');
				set.add('M');

				for(int i=0;i<args.length;i++){
					c1[i]=args[i];
					//Checking if each argument is of 2 letters or not
					if(c1[i].length()!=2 && !c1[i].equals("given")){
						System.out.println("Each of these arguments should be a string with two letters.");
						return;
					}
					//In case the argument is given, then get out of this for loop.
					if(c1[i].equals("given")){
						j=i+1;
						i=args.length;
						given=true;
					}
					//Checking the corner cases for each arguments and assigning the truth values for the symbols given
					else{
						if(!set.contains(c1[i].charAt(0))){
							System.out.println("The first letter should be  B (for Burglary), E (for Earthquake), A (for Alarm), J (for JohnCalls) or M (for MaryCalls). ");
							return;
						}
						if(c1[i].charAt(1)=='t'){
							comp.put(c1[i].charAt(0),true);
						}
						else if((c1[i].charAt(1)=='f')) {
							comp.put(c1[i].charAt(0),false);
						}
						else{
							System.out.println("The second letter should be t (for true) or f (for false)");
							return;
						}
					}
				}
				//if the given flag is set to true
				if(given){
					String[] c2=new String[args.length];
					while(j<args.length){
						c2[j]=args[j];
						if(c2[j].length()!=2){
							System.out.println("Each of these arguments should be a string with two letters.");
							return;
						}
						if(!set.contains(c2[j].charAt(0))){
							System.out.println("The first letter should be  B (for Burglary), E (for Earthquake), A (for Alarm), J (for JohnCalls) or M (for MaryCalls). ");
							return;
						}
						//Updating the comp hashmap and givcomp hashmap
						if(c2[j].charAt(1)=='t'){
							comp.put(c2[j].charAt(0),true);
							givcomp.put(c2[j].charAt(0),true);
						}
						else if((c2[j].charAt(1)=='f')) {
							comp.put(c2[j].charAt(0),false);
							givcomp.put(c2[j].charAt(0),false);
						}
						else{
							System.out.println("The second letter should be t (for true) or f (for false)");
							return;
						}
						j++;
					}
				}
				HashMap<Character,Boolean> mod= new HashMap<Character,Boolean> ();
				//Invoke assignVal method with empty mod
				double num=assignVal(set,mod,false);
				//If the given is set as true, then value of deno is computed
				if(given==true){
					HashMap<Character,Boolean> den= new HashMap<Character,Boolean> ();
					double deno=assignVal(set,den,true);
					// Referred the link https://stackoverflow.com/questions/12806278/double-decimal-formatting-in-java
					NumberFormat nf = NumberFormat.getInstance();
					nf.setMinimumFractionDigits(9);
					double res=num/deno;
					//Printing the probability after computing denominator and numerator
					//P(Burglary=true and Alarm=false | MaryCalls=false).
					System.out.println("Probability:"+nf.format(res));
				}
				else{
					NumberFormat nf = NumberFormat.getInstance();
					nf.setMinimumFractionDigits(9);
					//Printing the probability after computing numerator
					System.out.println("Probability:"+nf.format(num));
				}
			}
		}
		catch(Exception e){
			System.out.println("Number of arguments not correct");
			e.printStackTrace();			
		}	
	}
}