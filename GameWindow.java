
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.io.File;

import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.Clip;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.SwingConstants;


/**class GameWindow extends JFrame
 * this is the class of the game 
 */
public class GameWindow extends JFrame{
    
        //time
        double startTime; // date when the game start
        double elapsedTime;
        double pauseStart; // date when a pause start
        double pauseDuration=0.0;
        int startSet=0;
        double songEnd;
        
        //song data
        SongData songMaster;
        String currentLyrics="Lyrics";
        String currNote="";
        
        //timer
        Timer timer;
        long time;
	
        //scores	
        int score=1;
        int scoreMax=1;
        int scorePercent=100;
        double scoreTemp;
		
        // components 
        JLabel title, lyrics;
        LinkedList <Note> note;
        Pointeur pointeur;
        
        // frame
        BufferedImage ArrierePlan;
        Graphics buffer;
        Rectangle Ecran;
        
        // song
        Clip clip;
        int song;
        
        //State
        static Karaok.State state;
        
        
  
        
        



        public GameWindow(int asong) {

            /** affichage plein écran !!! */
            // frame
            setTitle("Kara-OK");
            setUndecorated(true);
            this.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
            this.setLayout(null);
            getContentPane().setBackground(new Color(Color.TRANSLUCENT));
            
            //icon of the window
            this.setIconImage(new ImageIcon(Content.icon).getImage());
            
            // set the mic curseur 
            Toolkit tk = Toolkit.getDefaultToolkit();
            Image img = tk.getImage(Content.cursor);
            Cursor cursor = tk.createCustomCursor(img, new Point(0, 0), "micro");
            this.setCursor(cursor);
            
            // buffer
            Ecran=new Rectangle(getInsets().left,getInsets().top,getSize().width-getInsets().right-getInsets().left,getSize().height-getInsets().bottom-getInsets().top);
            ArrierePlan =new BufferedImage(getSize().width,getSize().height,BufferedImage.TYPE_INT_RGB);
            buffer = ArrierePlan.getGraphics();
            
            // variables
            time = 1;
            score = 1;
            
           //STATE 
            state = Karaok.State.Game;

            pointeur = new Pointeur(Ecran);
            
            // music
            song=asong;
            
            //song data
            songMaster=new SongData(Content.titles[2][song]);
            songMaster.start();
            startTime=System.currentTimeMillis();
   
            songEnd=Content.songEnd[song];
   
            //song 
            try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Content.titles[1][song]).getAbsoluteFile());
                    clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    //clip.start();
                } catch(Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }

            // key adapter
            this.addKeyListener(new GameKeyAdapter());		

            // timer
            timer = new Timer(10,new TimerAction());
            
            //song title
            title = new JLabel(Content.titles[0][song],SwingConstants.CENTER);
            title.setOpaque(false);
            title.setForeground(Color.white);
            title.setFont(Content.font.deriveFont((float)Ecran.getHeight()/20));
            title.setBounds(0,0,(int)Ecran.getWidth(),(int)Ecran.getHeight()/15);
            getContentPane().add(title);
            
            //lyrics Label
            lyrics = new JLabel("Lyrics",SwingConstants.CENTER);
            lyrics.setOpaque(false);
            lyrics.setForeground(Color.white);
            lyrics.setFont(Content.font.deriveFont((float)Ecran.getHeight()/15));
            lyrics.setBounds((int)Ecran.getWidth()/6,(int)(Ecran.getHeight()*0.7),(int)(Ecran.getWidth()*5/6),(int)(Ecran.getHeight()*0.3));
            lyrics.setText("");
            getContentPane().add(lyrics);
            
            // initialisation of note:
            note= new LinkedList<Note>();

            setVisible(true);


        }

    /**Override paint method
     * paint all the components and all objects on the frame
     * @param g Gtaphics
     */
    public void paint(Graphics g){

            paintBackGround(buffer,Ecran);
            for(int i=0;i<note.size();i++){
                note.get(i).draw(time,buffer);
            }
            
            paintAdd(buffer, Ecran);
            getContentPane().paintComponents(buffer);
            pointeur.draw(time,buffer); 
            buffer.setColor(Color.white);
            buffer.setFont(Content.font.deriveFont((float)Ecran.getHeight()/20));
            buffer.drawString("score: "+ scorePercent+"%", (int)(Ecran.getWidth()*0.80), (int)Ecran.getHeight()/20);
            g.drawImage(ArrierePlan,0,0,this);
        }

    /**this method is used to paint the back ground
     * the background color depend of the song played
     * @param g Graphics
     * @param aframe the frame Rectangle
     */
    public void paintBackGround(Graphics g, Rectangle aframe){
            g.setColor(Content.background[song*2]);
            g.fillRect(0, 0, (int)aframe.getWidth(),(int) aframe.getHeight());
            g.setColor(Content.background[song*2+1]);
            for(int i=0; i<13; i++){
                fillRectY(buffer,0, (int)(aframe.getHeight()*(1+i*0.5)*0.1) , (int)aframe.getWidth(), (int)(aframe.getHeight()/(100+100*(i%2))));

            }
        }

    /**this method is used to paint the second part of the background
     * always paint after note, to hide them when they passed the pointeur
     * @param g Graphics 
     * @param aframe the frame Rectangle
     */
    public void paintAdd(Graphics g, Rectangle aframe){
            g.setColor(Content.background[song*2]);
            g.fillRect(0,0,(int)aframe.getWidth()/6,(int)aframe.getHeight());
            g.setColor(Content.background[song*2+1]);
            fillRectX(g,(int)aframe.getWidth()/6,0,(int)aframe.getWidth()/100,(int)aframe.getHeight());
        }

        

    /**this method fill a rectangle centered on Y pos
     * @param g Graphics
     * @param x x position
     * @param y y position
     * @param l length
     * @param h height
     */
    public void fillRectY(Graphics g, int x, int y, int l, int h){
            g.fillRect(x,y-h/2,l,h);
    }
        /**this method fill a rectangle centered on X pos
         * @param g Graphics
         * @param x x position
         * @param y y position
         * @param l length
         * @param h height
         */
    public void fillRectX(Graphics g, int x, int y, int l, int h){
            g.fillRect(x-l/2,y,l,h);
    }
        
        /**
         * class TimerAction implements ActionListener
         * this class is used to set actions displayed at each timerAction
         */
        private class TimerAction implements ActionListener {
            /**
             * Action performed by the timer
             * @param e ActionEvent
             */
            public void actionPerformed(ActionEvent e) {
                game_display();
                time++;
            }


        }


    /**actions performed on each timer action
     */
    public void game_display(){
            // needed for timer initialisation
            if(startSet==0)
            {
                startSet++;
				songMaster.currNote="";
				songMaster.currLyrics="";
            }
            if(startSet==1)
            {
                startSet++;
                startTime=System.currentTimeMillis();
				if (song==1) //We are the champions is strangely offset, so we have to do this
				{
					startTime+=5000;
					songMaster.currLyrics="I've paid my dues";
				}
            }
            
            // Pause management for time
            if(pauseStart!=0.0 && System.currentTimeMillis()-pauseStart>3000)
            {
                pauseDuration=(System.currentTimeMillis()-pauseStart);
                startTime+=pauseDuration;
                pauseStart=0.0;
            }
            
            // update the Notes into LinkedList
            boolean find=false;
            for(int i=0;i<note.size();i++){
                //move
                note.get(i).move(elapsedTime);
                //destroye
                if(note.get(i).destroy && note.size()!=1){
                    note.remove(i);
                }
                //get the currNote if foundd
                if(note.get(i).onCurseur){
                    currNote=note.get(i).note;
                    find = true;
                }
            }
            if(!find){
                currNote="";
            }
            
            //create a new Note in the LinkedList
            if (songMaster.seen==0)
			{
				note.add(new Note(songMaster.notesData[songMaster.indexRect],this.Ecran,songMaster.notesDuration[songMaster.indexRect],elapsedTime));
				songMaster.seen=1;
			}
			
            // score            
            scoreTemp=Score.calculScore(currNote,Karaok.freqmaster.mainFreq);
            if(scoreTemp!=-1)
			{
					score+=scoreTemp;
					scoreMax+=10;
                                        scorePercent=100*score/scoreMax;
			}
            
            pointeur.move(time,Karaok.freqmaster.mainFreq);
            pointeur.changeColor(scoreTemp);
            elapsedTime=(System.currentTimeMillis()-startTime)/1000;
            songMaster.elapsedTime=elapsedTime;
            lyrics.setText(songMaster.currLyrics);
            repaint();
   
            if(elapsedTime>songEnd)
            {
                state=Karaok.State.Win; 
                this.dispose();
            }

        }

     
    private class GameKeyAdapter extends KeyAdapter{


        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            switch (code){
            case KeyEvent.VK_ESCAPE: 
    pauseStart=System.currentTimeMillis();
                state=Karaok.State.Pause;
                PopUp jop = new PopUp(GameWindow.this);            
                break;
            
            case KeyEvent.VK_P:
    pauseStart=System.currentTimeMillis();
                state=Karaok.State.Pause;
                }
            }
        }
    }




