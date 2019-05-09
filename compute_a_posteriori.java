import java.io.*;
import java.util.*;
import java.lang.*;
//compute_a_posteriori class to compute posterior probablities
public class compute_a_posteriori{
	static double p0_h1=0,p0_h2=0,p0_h3=0,p0_h4=0,p0_h5=0;
	static double p_qc0=0,p_ql0=0;
	static double p_qc_h1=0,p_qc_h2=0,p_qc_h3=0,p_qc_h4=0,p_qc_h5=0;
	static double p_ql_h1=0,p_ql_h2=0,p_ql_h3=0,p_ql_h4=0,p_ql_h5=0;
	public static void main(String args[]){
		try{
			if(args[0]!=null && args.length!=0){
				String obs=args[0];
				int k=0;
				Boolean check=true;
				//Validating the observations made
				while(k<obs.length()){
					if(obs.charAt(k)!='c' && obs.charAt(k)!='l' && obs.charAt(k)!='C' && obs.charAt(k)!='L'){
						check=false;
						System.out.println("!!Enter proper observations(Only C/L)!!");
						return;
					}
					k++;
				}
				//Base Case calculations (Given)
				p0_h1=0.1;p0_h2=0.2;p0_h3=0.4;p0_h4=0.2;p0_h5=0.1;
				p_qc_h1= 1;p_qc_h2= 0.75;p_qc_h3= 0.5;p_qc_h4= 0.25;p_qc_h5= 0;
				p_ql_h1= 0;p_ql_h2= 0.25;p_ql_h3= 0.5;p_ql_h4= 0.75;p_ql_h5= 1;
				p_qc0=(p_qc_h1*p0_h1)+(p_qc_h2*p0_h2)+(p_qc_h3*p0_h3)+(p_qc_h4*p0_h4)+(p_qc_h5*p0_h5);
				p_ql0=1-p_qc0;
				//Base Case Ends
				if(obs.length()>0){
					//Probablity that bag chosen is h1 or h2 or h3 or h4 or h5, given the observation (C/L)
					double p_h1[]=new double[obs.length()+1];
					double p_h2[]=new double[obs.length()+1];
					double p_h3[]=new double[obs.length()+1];
					double p_h4[]=new double[obs.length()+1];
					double p_h5[]=new double[obs.length()+1];
					//Probablity that next chosen candy is c or l
					double p_qc[]=new double[obs.length()+1];
					double p_ql[]=new double[obs.length()+1];
					try {
						//Result if stored in result.txt
					    BufferedWriter output = new BufferedWriter(
										       new FileWriter( "result.txt" ) );
					    p_h1[0]=p0_h1;p_h2[0]=p0_h2;p_h3[0]=p0_h3;p_h4[0]=p0_h4;p_h5[0]=p0_h5;
						p_qc[0]=p_qc0;p_ql[0]=p_ql0;
						int c=1,l=1;
						//Writing to the file
						output.write("Observation sequence Q:"+obs+"\n");
						output.write("Length of Q:"+obs.length()+"\n");
						for(int i=0;i<obs.length();i++){
							//Probablity that bag chosen is h1 or h2 or h3 or h4 or h5, given the observation C
							if(obs.charAt(i)=='c' ||obs.charAt(i)=='C' ){
								output.write("\nAfter Observation  "+i+"= C:\n");
								p_h1[i+1]=(p_qc_h1*p_h1[i])/p_qc[i];	
								p_h2[i+1]=(p_qc_h2*p_h2[i])/p_qc[i];	
								p_h3[i+1]=(p_qc_h3*p_h3[i])/p_qc[i];	
								p_h4[i+1]=(p_qc_h4*p_h4[i])/p_qc[i];	
								p_h5[i+1]=(p_qc_h5*p_h5[i])/p_qc[i];
								p_qc[i+1]=(p_qc_h1*p_h1[i+1]) +
											(p_qc_h2*p_h2[i+1])+
												(p_qc_h3*p_h3[i+1])+
													(p_qc_h4*p_h4[i+1])+
														(p_qc_h5*p_h5[i+1]);
								p_ql[i+1]=1-p_qc[i+1];
								output.write("\nP(h1 | Q) ="+p_h1[i+1]+"\n");
								output.write("P(h2 | Q) ="+p_h2[i+1]+"\n");
								output.write("P(h3 | Q) ="+p_h3[i+1]+"\n");
								output.write("P(h4 | Q) ="+p_h4[i+1]+"\n");
								output.write("P(h5 | Q) ="+p_h5[i+1]+"\n");
								output.write("\nProbability that the next candy we pick will be C, given Q:"+p_qc[i+1]+"\n");
								output.write("Probability that the next candy we pick will be L, given Q:"+p_ql[i+1]+"\n");
							}
							//Probablity that bag chosen is h1 or h2 or h3 or h4 or h5, given the observation L
							else if(obs.charAt(i)=='l' || obs.charAt(i)=='L' ){
								output.write("\nAfter Observation  "+i+"= L:\n");
								p_h1[i+1]=(p_ql_h1*p_h1[i])/p_ql[i];	
								p_h2[i+1]=(p_ql_h2*p_h2[i])/p_ql[i];	
								p_h3[i+1]=(p_ql_h3*p_h3[i])/p_ql[i];	
								p_h4[i+1]=(p_ql_h4*p_h4[i])/p_ql[i];	
								p_h5[i+1]=(p_ql_h5*p_h5[i])/p_ql[i];
								p_qc[i+1]=(p_qc_h1*p_h1[i+1]) +
											(p_qc_h2*p_h2[i+1])+
												(p_qc_h3*p_h3[i+1])+
													(p_qc_h4*p_h4[i+1])+
														(p_qc_h5*p_h5[i+1]);
								p_ql[i+1]=1-p_qc[i+1];
								output.write("\nP(h1 | Q) ="+p_h1[i+1]+"\n");
								output.write("P(h2 | Q) ="+p_h2[i+1]+"\n");
								output.write("P(h3 | Q) ="+p_h3[i+1]+"\n");
								output.write("P(h4 | Q) ="+p_h4[i+1]+"\n");
								output.write("P(h5 | Q) ="+p_h5[i+1]+"\n");
								output.write("\nProbability that the next candy we pick will be C, given Q:"+p_qc[i+1]+"\n");
								output.write("Probability that the next candy we pick will be L, given Q:"+p_ql[i+1]+"\n");
							}					
						} 
					    output.close();
					} catch( IOException e ) {
					    System.out.println("\nProblem writing to the result file!\n" +
							       "Try again.");
					    e.printStackTrace();
					}
					
				}
			}
		}
		//In case the command line argument is not made, write no observations not made to the results.txt
		catch(Exception e){
			try {
				BufferedWriter output = new BufferedWriter(
											       new FileWriter( "result.txt" ) );
				output.write("No observations yet made");
				output.close();
				return;
			}
			catch( IOException ex ) {
			    System.out.println("\nProblem writing to the result file!\n" +
						       "Try again.");
			    ex.printStackTrace();
			}
		}
	}
}

//if(obs.matches(".*[cClL].*"))