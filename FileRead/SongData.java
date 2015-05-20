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
		final boolean DEBUG_MODE=true;
		/*
		 * 
		 * TODO : add author field ?
		 * 
		 */
		long startTime=System.currentTimeMillis( );

		if(DEBUG_MODE){System.out.println("[DEBUG]Timer started at time : "+startTime);}
		BufferedReader br = null;

		final int MAXLENGTH=200;
		

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
				 * FIXED :
				 * 
				 * Next time, remember about string comparison, you moron
				 * 
				 */

				
				if(sCurrentLine.equals("BEGIN LYRICS")){
					lyricsRead=1;
					index=0;
					sCurrentLine = br.readLine();
					if(DEBUG_MODE){System.out.println("[DEBUG]Found lyrics beginning.");}
				}
				
				if(sCurrentLine.equals("END LYRICS")){
					lyricsRead=2;
					if(DEBUG_MODE){System.out.println("[DEBUG]Found lyrics ending. "+index+" lyrics found.");}
				}
				
				if(sCurrentLine.equals("BEGIN NOTES")){
					notesRead=1;
					index=0;
					sCurrentLine = br.readLine();
					if(DEBUG_MODE){System.out.println("[DEBUG]Found notes beginning.");}
				}
				
				if(sCurrentLine.equals("END NOTES")){
					notesRead=2;
					if(DEBUG_MODE){System.out.println("[DEBUG]Found notes ending. "+index+" notes found.");}
				}
				
				
				
				
				if(lyricsRead==1 && notesRead!=1){
					lyricsTime[index]=Float.parseFloat(sCurrentLine);
					
					if(index>0 && DEBUG_MODE && lyricsTime[index]<=lyricsTime[index-1])
					{
						System.out.println("[DEBUG]Lyrics timestamps in the wrong order");
					}
					
					sCurrentLine = br.readLine();
					lyricsData[index]=sCurrentLine;
				}

				if(lyricsRead!=1 && notesRead==1){
					notesTime[index]=Float.parseFloat(sCurrentLine);
					
					if(index>0 && DEBUG_MODE && notesTime[index]<=notesTime[index-1])
					{
						System.out.println("[DEBUG]Notes timestamps in the wrong order");
					}
					
					sCurrentLine = br.readLine();
					notesData[index]=sCurrentLine;
				}
				
				
				
				
				if(DEBUG_MODE && lyricsRead==1 && notesRead==1){
					
					System.out.println("[DEBUG]Something wrong happened.");
				
				}
				
				index++;
			}
			
			if(DEBUG_MODE){System.out.println("[DEBUG]EOF reached.");}
			
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
