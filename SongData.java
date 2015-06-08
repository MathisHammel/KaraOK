import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SongData extends Thread {
                        
        
        
                        final boolean DEBUG_MODE=true;
        
                        
        String currNote="";
        String currLyrics="";
                        
        static String filepath="";
        final int MAXLENGTH=1000;
                

        String[] lyricsData=new String[MAXLENGTH];
        double[] lyricsTime=new double[MAXLENGTH];
        String[] notesData=new String[MAXLENGTH];
        double[] notesTime=new double[MAXLENGTH];
		double[] notesEnd=new double[MAXLENGTH];
		double[] notesDuration=new double[MAXLENGTH];
		
		
                
        int lyricsRead=0;
        int notesRead=0;
        
        long startTime;
        double endTime=1000;
        boolean end=false;
        double elapsedTime=0;
        
        public SongData(String path){
                filepath=path;



                if(DEBUG_MODE){System.out.println("[DEBUG]Timer started at time : "+startTime);}
                BufferedReader br = null;


                
                try {
 
                        String sCurrentLine;
                        br = new BufferedReader(new FileReader(filepath));
                        int index=0;
 
                        while ((sCurrentLine = br.readLine()) != null) {
                                
                                
                                //System.out.println(sCurrentLine);


                                
                                if(sCurrentLine.equals("BEGIN LYRICS")){
                                        lyricsRead=1;
                                        index=0;
                                        sCurrentLine = br.readLine();
                                        if(DEBUG_MODE){System.out.println("[DEBUG]Found lyrics beginning.");}
                                }
                                
                                if(sCurrentLine.equals("END")){
                                        sCurrentLine = br.readLine();
                                        endTime=Float.parseFloat(sCurrentLine);
                                        if(DEBUG_MODE){System.out.println("[DEBUG]Found endtime.");}
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
										sCurrentLine = br.readLine();
										System.out.println(sCurrentLine);
                                        notesEnd[index]=Float.parseFloat(sCurrentLine);
										notesDuration[index]=notesEnd[index]-notesTime[index];
                                        sCurrentLine = br.readLine();
                                        notesData[index]=sCurrentLine;
										System.out.println(notesTime[index]+" "+notesEnd[index]+" "+notesData[index]);
										if(index>0 && DEBUG_MODE && (notesTime[index]<=notesTime[index-1] || notesEnd[index]<=notesEnd[index-1]))
                                        {
                                                System.out.println("[DEBUG]Notes timestamps in the wrong order");
                                        }
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
                if(notesRead==2 && lyricsRead==2 && DEBUG_MODE==true)
                {
                        System.out.println("[DEBUG]File read went smooth as fuck.");
                }
        }
        
        
        
        
        
        public void run()
        {
                
                
                int indexLyrics=-1;
                int indexNotes=-1;
                
                while(!end)
                {                

                        //if(DEBUG_MODE&&elapsedTime%1==0){System.out.println(elapsedTime);}
                        
                                while(lyricsTime[indexLyrics+1]<=elapsedTime && lyricsTime[indexLyrics+1]>=0.1)
                                {
                                        indexLyrics++;
                                        currLyrics=lyricsData[indexLyrics];
										System.out.println(currLyrics);
                                        if(DEBUG_MODE){System.out.println("[DEBUG]Lyrics : "+currLyrics);}
                                }
                        

                                while(notesTime[indexNotes+1]<=elapsedTime && notesTime[indexNotes+1]>=0.1)
                                {
                                        indexNotes++;
										if(notesEnd[indexNotes]>elapsedTime)
										{
											currNote=notesData[indexNotes];
                                        }
										else
										{
											currNote="";
										}
										if(DEBUG_MODE){System.out.println("[DEBUG]Note : "+currNote);}
                                }
                        
                        
                        if(elapsedTime>endTime)
                        {
                                if(DEBUG_MODE){System.out.println("[DEBUG]Reached end of song.");}
                                end=true;
                        }

                }
                
        }
}