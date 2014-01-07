import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Contatore{
	public static void main(String[] args) throws IOException{
		int numRows = 0;
		int numWords = 0;
		int numChars = 0;
		String str;
		File f = new File("input.txt");
		if(f.exists() ){
			Scanner s = new Scanner(f);
			while(s.hasNextLine()){
				s.nextLine();
				numRows++;
			}
			s.close();
			s = new Scanner(f);
			while(s.hasNext()){
				str = s.next();
				numWords++;
				numChars += str.length();
			}
			s.close();
			System.out.printf("Ilfile ha %d righe, %d parole e %d caratteri\n", numRows, numWords, numChars);
		}
		else{
			System.out.println("Il file input.txt non esiste!");
		}
	}
}
