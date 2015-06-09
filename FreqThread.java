import java.io.IOException;
import java.io.File;
import java.math.*;
import java.io.*;

import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.AudioFileFormat;

/**
class FreqThread
*This class is a thread that manages audio lines for signal sampling and voice acquisition analysis
*
*/
public class FreqThread extends Thread{

        double freq;
        double mainFreq;
        String note;
        
        //these params define the audio line settings
        float sampleRate = 44100;
        int sampleSizeInBits = 8;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(
                        sampleRate,
                        sampleSizeInBits,
                        channels,
                        signed,
                        bigEndian);

        int numBytesRead;
		  
        double mes=0;
        double prev=0;
        
         int i=0,j=0;
         double avg;
         boolean stopped=false;
         
         byte[] data = new byte[1024];
         
         DataLine.Info    info = new DataLine.Info(TargetDataLine.class, format);
         TargetDataLine    target = null;
         
        
        public FreqThread(){

            
            try
            {
                    target = (TargetDataLine) AudioSystem.getLine(info);
                    target.open(format);
                    target.toString();
            }
            catch (LineUnavailableException e)
            {
                    System.out.print("unable to get a recording line");
                    e.printStackTrace();
                    System.exit(1);
            }



            // Begin audio capture.
            target.start();

  
        }
        
        public void run(){

            while (!stopped) {
            avg=0.0;
    
            // Read the next chunk of data from the TargetDataLine.
            numBytesRead =  target.read(data, 0, data.length);

            // Compute the chunk amplitude
            for(j=0;j<data.length;j++)
            {
                avg+=data[j];
            }
                avg/=data.length;
    
            //normalize samples to an overall absolute average of 1
            for(j=0;j<data.length;j++)
            {
                data[j]/=avg;
            }

            //compute the autocorrelation and store result in variables
            mes=Autocorrelate.freq(data);
            if(mes==-44100.0){
                    mes=prev;
            }
            else
            {
                    prev=mes;
            }
        
            this.freq=mes;
            this.mainFreq=getMainFreq(mes);
            this.note=getNote(this.mainFreq);
        
            }
        }
        /**
	*public getMainFreq
	*this method computes a main frequency between 440.0 and 880.0 for any given frequency
	*
	*/
	
        public static double getMainFreq(double in)
        {
                if(in < 440.0 && in > 0)
                {
                        while(in<440.0)
                        {
                                in*=2;
                        }
                }
                if(in >= 880.0 && in > 0)
                {
                        while(in>=880.0)
                        {
                                in/=2;
                        }
                }
                return(in);
        }
        
		

        //This method returns a string note if you give any mainfreq
        public static String getNote(double in){
                int i;
                int ecart=50;
                in=getMainFreq(in);
                String out="";
                String[] notes = {"A","Bb","B","C","Db","D","Eb","E","F","Gb","G","Ab","A"};
                double[] freq = {440.0 , 466.16 , 493.88 , 523.25 , 554.37 , 587.33 , 622.25 , 659.25 , 698.46 , 739.99 , 783.99 , 830.61 , 880.0};
                //here, we iterate to see which frequency is closest
                for(i=0;i<notes.length;i++)
                {
                        if(Math.abs(getMainFreq(in)-freq[i])<ecart)
                        {
                                ecart=Math.abs((int)(getMainFreq(in)-freq[i]));
                                out=notes[i];
                        }
                }
                
                return out;
        }
        
        //This method is the opposite of getNote, it returns the frequency associated to the input note
        public static double getFreq(String in){
                String[] notes = {"A","Bb","B","C","Db","D","Eb","E","F","Gb","G","Ab"};
                double[] freq = {440.0 , 466.16 , 493.88 , 523.25 , 554.37 , 587.33 , 622.25 , 659.25 , 698.46 , 739.99 , 783.99 , 830.61};
                int i;
                double out=-1;
                for(i=0;i<freq.length;i++)
                {
                        if(notes[i].equals(in)){
                                out=freq[i];
                        }
                }
                return(out);
        }
        
    
    
}
