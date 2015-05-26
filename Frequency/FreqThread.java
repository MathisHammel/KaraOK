import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;


public class FreqThread extends Thread {
	
	double freq;
	double mainFreq;
	String note;

	  public FreqThread()
	  {

	  }

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
	
	public static String getNote(double in){
		int i;
		int ecart=50;
		in=getMainFreq(in);
		String out="";
		String[] notes = {"A","Bb","B","C","Db","D","Eb","E","F","Gb","G","Ab","A"};
		double[] freq = {440.0 , 466.16 , 493.88 , 523.25 , 554.37 , 587.33 , 622.25 , 659.25 , 698.46 , 739.99 , 783.99 , 830.61 , 880.0};
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
	
	public static double getFreq(String in){
		String[] notes = {"A","Bb","B","C","Db","D","Eb","E","F","Gb","G","Ab"};
		double[] freq = {440.0 , 466.16 , 493.88 , 523.25 , 554.37 , 587.33 , 622.25 , 659.25 , 698.46 , 739.99 , 783.99 , 830.61};
		int i;
		double out=-1;
		for(i=0;i<freq.length;i++)
		{
			if(in==notes[i]){
				out=freq[i];
			}
		}
		return(out);
	}
	
	
    public void run(){
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

  byte[] data = new byte[1024];
  System.out.println("Src buffer size : "+target.getBufferSize());

  // Begin audio capture.
  target.start();
 int j=0;
 double avg;
 boolean stopped=false;
  // Here, stopped is a global boolean set by another thread.
  while (!stopped) {
	  avg=0.0;
	 //System.out.print("\n\n\n");

	  // Save this chunk of data.
     for(j=0;j<data.length;j++)
     {
    	 avg+=data[j];
     }




     avg/=data.length;
     for(j=0;j<data.length;j++)
     {
    	 data[j]/=avg;
     }


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
}
