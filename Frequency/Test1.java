import java.io.IOException;
import java.io.File;

import java.io.*;

import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.AudioFileFormat;


public class Test1 {

	/*
	 * TODO
	 * Modifier le constructeur de target
	 *
	 */


    public static void main(String[] args){
    	//AudioFormat.Encoding encoding = AudioFormat.Encoding.ALAW;
    	float sampleRate = 44100;
    	int sampleSizeInBits = 8;
    	int channels = 1;
    	boolean signed = true;
    	//int framerate=1000;
    	boolean bigEndian = true;
    	AudioFormat format = new AudioFormat(
    			sampleRate,
    			sampleSizeInBits,
    			channels,
    			signed,
    			bigEndian);


    	double mes=0;
    	double prev=0;

   	 DataLine.Info    info = new DataLine.Info(TargetDataLine.class, format);
   	 TargetDataLine    target = null;
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
   	 System.out.println("Line available");



  // Assume that the TargetDataLine, line, has already
  // been obtained and opened.

  /*
  ByteArrayOutputStream out  = new ByteArrayOutputStream();
  */
  int numBytesRead;


  byte[] data = new byte[1024];
  System.out.println("Src buffer size : "+target.getBufferSize());

  // Begin audio capture.
  target.start();
 int i=0,j=0;
 double avg;
 boolean stopped=false;
  // Here, stopped is a global boolean set by another thread.
  while (!stopped) {
	  avg=0.0;
	 //System.out.print("\n\n\n");


	  /*if(i==10){
	  stopped=true;
	 }
	 i++;*/



	  // Read the next chunk of data from the TargetDataLine.
     numBytesRead =  target.read(data, 0, data.length);

     // Save this chunk of data.
     for(j=0;j<data.length;j++)
     {
    	 avg+=data[j];
    	// System.out.print(data[j]+";");
     }




     avg/=data.length;
     for(j=0;j<data.length;j++)
     {
    	 data[j]/=avg;
     }

     //System.out.print(avg);

     /*
      * Here is a function displaying the volume of the sample
      *
     if(avg<=1)
     {
    	 System.out.println("[=     ]");
     }
     else if(avg<=2)
     {
    	 System.out.println("[==    ]");
     }
     else if(avg<=4)
     {
    	 System.out.println("[===   ]");
     }
     else if(avg<=6)
     {
    	 System.out.println("[====  ]");
     }
     else
     {
    	 System.out.println("[======]");
     }
  }
  */

  //CustomFFT.compute(data);
    mes=Autocorrelate.freq(data);
    /*if(mes==-44100.0){
    	mes=prev;
    }
    else*/
    {
    	prev=mes;
    }
     System.out.println("\n"+mes);

  }
 }
}
