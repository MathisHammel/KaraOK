public class Score {
    
    
    public static void calculScore(int currentscore,int scoremax, String note, double frequency){
        if(note.equals("")){
            
        }else{
        double freq= frequency-660;
        double noteFreq = FreqThread.getFreq(note)-660;
        currentscore+= 10-Math.abs((Math.abs(freq)- Math.abs(noteFreq)))/22;
        scoremax+=10;
        }
    }
}
