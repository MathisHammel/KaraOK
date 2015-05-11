
public class TestThread {
	public static void main(){
		FreqThread freqmaster=new FreqThread();
		freqmaster.start();
		while(true)
		{
			System.out.println(freqmaster.freq);
		}
	}
}
