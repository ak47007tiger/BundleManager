package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class TestZhuanYI {

	public static void main(String[] args) throws FileNotFoundException {
		PrintStream out = new PrintStream(new File(System.getProperty("user.dir"),"/files/chars.txt"));
		int lineCount = 1;
		for(char i = Byte.MAX_VALUE; i <= Character.MAX_VALUE; i++){
			out.print(i);
			out.print(' ');
			if(lineCount == 20){
				lineCount = 1;
				out.println();
			}
		}
		out.close();
	}
}
