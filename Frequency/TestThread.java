package Frequency;
public class TestThread {
	public static void main(String[] args){
		FreqThread freqmaster=new FreqThread();
		freqmaster.start();
		while(true)
		{
			System.out.println(freqmaster.freq);
		}
	}
}
