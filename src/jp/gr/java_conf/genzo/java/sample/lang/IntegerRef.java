package jp.gr.java_conf.genzo.java.sample.lang;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class IntegerRef {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {

		Integer aaa = new Integer(1);
		Integer bbb = new Integer(1);

		//aaa = new Integer(2);

		System.out.println(String.format("aaa=[%d], bbb=[%d]", aaa, bbb));

		boolean ccc = true;
		boolean ddd = ccc;

		ccc = false;

		System.out.println(String.format("ccc=[%s], ddd=[%s]", ccc, ddd));
		
		
		try (BufferedReader br = new BufferedReader(new FileReader("d:/work/aaa.txt"))) {
		    
		    System.out.println(br.readLine());
		    
		}
	}

}
