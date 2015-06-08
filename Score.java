public class Score {
    
    
    public static int calculScore(int currentScore,int scoremax, String note, double frequency){
        if(!note.equals("")){
			double freq= frequency-660;
			double noteFreq = FreqThread.getFreq(note)-660;
			System.out.println(freq+" "+noteFreq+ note);
			currentScore = 10-((int)(Math.abs((Math.abs(freq)- Math.abs(noteFreq))))/22);
			System.out.println(""+currentScore);
			if(currentScore<0)
			{
				currentScore=0;
			}
			return(currentScore);
        }
		else
		{
			return(-1);
		}
    }
}
