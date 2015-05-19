package FileRead;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SongData extends Thread {
	
	static String filepath="";
	
	public SongData(String path){
		filepath=path;
	}
	
	
	public static String[] getFile() {
		 
		BufferedReader br = null;
		String[] filedata=new String[200];
		try {
 
			String sCurrentLine;
			int i=0;
			br = new BufferedReader(new FileReader(filepath));

 
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				filedata[i]=sCurrentLine;
				i++;
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return(filedata);
	}

	
	
	public void run()
	{
		getFile();
	}
}
