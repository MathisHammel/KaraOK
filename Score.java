/**class Score
 * this class calculate the score
 */
public class Score {


    /**this static method calulate the score
     * @param currentScore the score calculated
     * @param note the note expected
     * @param frequency the frequency given by the micro
     * @return currentScore or -1 if no note was expected
     */
    public static int calculScore(String note, double frequency){
        if(!note.equals("")){
                        int currentScore;
			double freq= frequency-660;
			double noteFreq = FreqThread.getFreq(note)-660;
			
			currentScore = 10-((int)(Math.abs((Math.abs(freq)- Math.abs(noteFreq))))*2/11);
			
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
