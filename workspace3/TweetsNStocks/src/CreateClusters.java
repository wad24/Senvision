import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CreateClusters {

	public static void iterateClusters(String inputfolder, String outputfolder) {
		File vocabfolder = new File(inputfolder);
		File[] vocabFiles = vocabfolder.listFiles();
		for (File vocabfile : vocabFiles) {
			try {
				writeClusters(vocabfile, new File(outputfolder + "/"
						+ vocabfile.getName().substring(0, vocabfile.getName().indexOf(".txt")) + "_clustered.txt"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void writeClusters(File inputfile, File file) throws IOException {
		BufferedReader bR = new BufferedReader(new FileReader(inputfile));
		BufferedWriter bW = new BufferedWriter(new FileWriter(file));
		ArrayList<String> allLists = new ArrayList<String>();
		String nextLine;
		while ((nextLine = bR.readLine()) != null) {
			if (!nextLine.isEmpty())
				allLists.add(nextLine);
		}
		for (int i = 0; i < allLists.size(); i++) {
			for (int j = 0; j < i; j++) {
				bW.write(allLists.get(i) + " " + allLists.get(j) + "\r\n");
				System.out.println(allLists.get(i) + " " + allLists.get(j));
				// bW.write(allLists.get(i)+" "+allLists.get(j));
			}
		}
		bW.close();
	}

	public static void main(String[] args) throws IOException {
		iterateClusters("../../Vocab", "../../Vocab_Clusters");
	}

}
