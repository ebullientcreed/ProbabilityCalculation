# ProbabilityCalculation
Posterior probability of different hypotheses and probability of any combination of events
Programming Language used: Java

How the code is structured.
			-Programmed in Java.
			-TASK1: In compute_a_posteriori.java the following is performed
				1)Determined Posterior probability of different hypotheses, given priors for these hypotheses,
				 	and given a sequence of observations.
				2)Also determined the probability that the next observation will be of a specific type,
					priors for different hypotheses, and given a sequence of observations. is calculated.
				3)Result stored in created results.txt	
			-TASK2:	In bnet.java the following is performed
				1)Computes and prints out the probability of any combination of events given any other combination of events
				2)bnet class with the following methods
					- computeProbability():Method to compute function should return the joint probability of the five events,
								 for the model with truth values to each symbols
					- assignVal():Function assigning truth value for each symbols and then for each model,
							computing the probability by invoking the computeProbability
					- EXTEND(): Method to add the value of p to the model by extending
				
Files		   :	-Submited a ZIPPED directory called assignment9_nxt6413.zip in the blackboard. 
			-This zip directory includes compute_a_posteriori.java, bnet.java and readme.txt files.
			-Keep all input files in the same directory.
How to run the code: 	TASK 1:	
				1)Compile the compute_a_posteriori.java using the following command
					javac compute_a_posteriori.java
				 
		    		2)Run using the following command 
					java compute_a_posteriori <observations>
					EG: java compute_a_posteriori CCLLLL
			TASK 2:	1)Compile the bnet.java using the following command
					javac bnet.java
				 
		    		2)Run using the following command 
					java bnet <Combination of C1 events> <Optional given> <Combination of C2 events>
					EG:- java bnet Bt Af given Mf
					   - java bnet Bt Af Mf Jt Et			
			

References	   : 	1) Artificial Intelligence A modern Approach Second Edition by Russel, Norvig
			2) To format the double values, followed the link : https://stackoverflow.com/questions/12806278/double-decimal-formatting-in-java 
		
		
			
			
			




