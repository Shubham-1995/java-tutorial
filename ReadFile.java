import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class ReadFile {
	private static String FILENAME = "C:/Users/t133/Desktop/statusByShubham.txt";
	public static void main(String[] args) {
		BufferedReader br = null;
		FileReader fr = null;
		try {

			fr = new FileReader(FILENAME);// create fileReader object which take filewhich
			System.out.println("fr by Shubham ------> "+fr.read());
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(fr);// create buffer reader which takes file reader object
			System.out.println("br by shubham ------------> "+br);
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			
			}
				br.close();
				fr.close();
		}catch (IOException e) {

			e.printStackTrace();

		}

	}

}
