public class DataTest {
	public static void main(String[] args){
		double start = System.currentTimeMillis();
	
		SongData reader = new SongData("We_are_the_champions_debug.txt");
		reader.start();
		while(true)
		{
			reader.elapsedTime=(System.currentTimeMillis()-start)/1000;
		}
	}
}

