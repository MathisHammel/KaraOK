import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SongData extends Thread {
	
	static String filepath="";
	
	public SongData(String path){
		filepath=path;
	}
	
	
	
	
	
	public void run()
	{
		/*
		 * 
		 * TODO : add author field ?
		 * 
		 */
		final int MAXLENGTH=200;
		BufferedReader br = null;
		String[] lyricsData=new String[MAXLENGTH];
		double[] lyricsTime=new double[MAXLENGTH];
		String[] notesData=new String[MAXLENGTH];
		double[] notesTime=new double[MAXLENGTH];
		int lyricsRead=0;
		int notesRead=0;
		try {
 
			String sCurrentLine;
			br = new BufferedReader(new FileReader(filepath));
			int index=0;
 
			while ((sCurrentLine = br.readLine()) != null) {
				
				
				//System.out.println(sCurrentLine);
				
				/*
				 * FIXME :
				 * WHAT THE HELL, DETECTION OF THESE SPECIFIC LINES FAIL
				 */
				if(sCurrentLine=="BEGIN LYRICS"){
					lyricsRead=1;
					index=0;
					System.out.println("[DEBUG]Found lyrics beginning.");
				}
				if(sCurrentLine=="END LYRICS"){
					lyricsRead=2;
					System.out.println("[DEBUG]Found lyrics ending. "+index+" lyrics found.");
				}
				if(sCurrentLine=="BEGIN NOTES"){
					notesRead=1;
					index=0;
					System.out.println("[DEBUG]Found notes beginning.");
				}
				if(sCurrentLine=="END NOTES"){
					notesRead=2;
					System.out.println("[DEBUG]Found notes ending "+index+" notes found.");
				}
				
				
				
				
				if(lyricsRead==1 && notesRead!=1){
					lyricsTime[index]=Float.parseFloat(sCurrentLine);
					sCurrentLine = br.readLine();
					lyricsData[index]=sCurrentLine;
				}

				if(lyricsRead!=1 && notesRead==1){
					notesTime[index]=Float.parseFloat(sCurrentLine);
					sCurrentLine = br.readLine();
					notesData[index]=sCurrentLine;
				}
				
				
				
				
				if(lyricsRead==1 && notesRead==1){
					
					System.out.println("[DEBUG]Something wrong happened.");
				
				}
				
				index++;
			}
			System.out.println("[DEBUG]EOF reached.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		if(notesRead==2 && lyricsRead==2)
		{
			System.out.println("[DEBUG]File read went smooth as fuck.");
		}
	}
}
