import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
public class ContaRighe{
	public static void main(String[] args) throws IOException{
		int i = 0;
		File f = new File("input.txt");
		if(f.exists()){
			PrintWriter out = new PrintWriter("output.txt");
			String str;
			Scanner s = new Scanner(f);
			while(s.hasNextLine()){
				str = s.nextLine();
				str = ++i + ")\t" + str;
				out.println(str);
			}
			out.close();
			s.close();
		}
		else{
			System.out.println("Il file input.txt non esiste!");
		}
	}
}
