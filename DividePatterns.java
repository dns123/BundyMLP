/*  This program was created to have an utility which can divide the number of patterns in two files, One called the train
set which includes the patterns to be used for training and the other one called test set which includes the patterns
to be used for testing.
*/



import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class DividePatterns {
	public static void main (String[] args ) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println(" Type name of patterns in file " );
 	    	String patFileName = sc.next();
 	    	File patFile = new File(patFileName);
 	    	if ( ! ( patFile.exists() && patFile.canRead() ) ) {
			System.out.println(patFileName+" Does not exist or is not readable " );
			System.exit(0) ;
		}
		System.out.println(" Type the percentage of patterns to be transferred to test file " );
 	    	int testPercent =  sc.nextInt();
 	    	if ( testPercent < 10 || testPercent > 50 ) {
 	    		testPercent = 20 ;
 	    	}
 	    	String testFileName = "test"+patFileName;
 	    	String trainFileName = "train"+patFileName;  	    	
		System.out.println(testPercent+ " % of patterns to be moved to file  "+testFileName+ " remaining to "+trainFileName) ;
		FileReader fr = new FileReader( patFileName);
		BufferedReader br = new BufferedReader( fr);
		Vector <String> patterns = new Vector <String>() ;
		/* Here the number of patterns in the file may not be known so how to initialize the matrix which can store the
	data in the file. To over come that we have used a data type called Vector which does not require the 
	size of the data to be initiated. !!!!!  So it will read the file line by line as String object and add
	it to the Vector patterns as shown below.	*/
		String patternString = br.readLine() ;
		while ( patternString != null ) {
			patterns.add( patternString ) ;
			patternString = br.readLine() ;
		}
		int numberOfPatterns = patterns.size();
		int numberOfTestPatterns = numberOfPatterns * testPercent / 100;
		int numberOfTrainPatterns = numberOfPatterns - numberOfTestPatterns;
		System.out.println(numberOfTestPatterns+ " patterns in file "+  testFileName);
		System.out.println(numberOfTrainPatterns+ " patterns in file "+  trainFileName);
		
		FileWriter fw = new FileWriter( testFileName);
		PrintWriter testw = new PrintWriter( fw);
		fw = new FileWriter( trainFileName);
		PrintWriter trainw = new PrintWriter( fw);		
		
		Vector<Integer> moved = new Vector<Integer>() ;
		int np = 0 ;
		while (np < numberOfTestPatterns ) {
			
			// Suppose we have to divide the normailised data in 80:20 ratio for training and testing.
			/* Then we will randomly select 20 percent of total patterns and put them into test set
			and the rest of them will be part of train set.
			*/

			// a random number is generated.
			
			int rp =(int) Math.round( Math.random() * numberOfPatterns );
			if ( ! ( moved.contains ( rp ) )) {
				moved.add(rp) ;
				patternString = patterns.get(rp); 
				testw.println(patternString);
				np ++ ;
			}
		}
		for( int p=0; p < numberOfPatterns; p ++ ) {
			if ( ! ( moved.contains ( p ) ) ) {
				patternString = patterns.get(p); 
				trainw.println(patternString);
			}
		}
		
		sc.close();
		br.close();
		fr.close();
		testw.close();
		trainw.close();
		fw.close();
		
	}
}	

