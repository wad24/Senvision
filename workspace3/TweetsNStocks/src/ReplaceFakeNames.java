import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReplaceFakeNames {

	
	
	public static void fixFile(File fileToChange,String outputfile) throws IOException{
		BufferedReader bR = new BufferedReader(new FileReader(fileToChange));
		BufferedWriter bW = new BufferedWriter(new FileWriter(outputfile));
		
		String nextLine;
		while((nextLine=bR.readLine())!=null){
			String nextLine1 = nextLine.replaceAll("#", " ");
//			System.out.println(nextLine1);
			String nextLine2 = nextLine1.replaceAll("\\$", " ");
			String nextLine3 = nextLine2.replaceAll("@", " ");
//			System.out.println(nextLine3);
			bW.write(nextLine3.replace("^", "")+"\r\n");
		}
		bR.close();
		bW.close();
	}
	
	
	public static void showFiles(File[] files) {
	    for (File file : files) {
	        if (file.isDirectory()) {
	            System.out.println("Directory: " + file.getName());
	            File newDir = new File("../../AllTweets/filteredTweets/"+file.getName());
	            newDir.mkdir();
	            showFiles(file.listFiles()); // Calls same method again.
	        } else {
	            try {
					fixFile(file,"../../AllTweets/filteredTweets/"+file.getParent().substring(file.getParentFile().toString().lastIndexOf("/"))+"/"+file.getName());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }
	}
	public static void main(String[] args){
		File TweetsFolder = new File("../../AllTweets/noDupliTweets");
		File[] TweetsArray = TweetsFolder.listFiles();
		showFiles(TweetsArray);
	}
	
}
