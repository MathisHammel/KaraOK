import java.awt.Color;

public class Content {
    // this class contein all the extra !
    
    public     static String[][] files = {{"songs\\Fun.wav","songs\\queen.wav","songs\\beyonce.wav" }
                               ,{"img\\fun-we-are-young.png","img\\WATC.png", "img\\WATW.png"}
                               ,{"<html><center>FUN<br> we are young","<html><center>QUEEN<br> we are the champions", "<html><center>USA FOR AFRICA<br> we are the world"}}; 
   
    // frame elements
    public static String icon = "img\\microphone_converted.png";
    public static String cursor = "img\\mic_converted.png";
    // button elemetns 
    public static String close = "img\\Close";
    public static String minimize = "img\\Minimize";
    public static String prev= "img\\NextPrev\\Prev01"; 
    public static String next= "img\\NextPrev\\Next01";
    public static String play="img\\Play";
    
    // Colors palet
    static Color[] colors = {new Color(26,188,156),new Color(22,160,133),new Color(241,196,15),new Color(243,156,18),new Color(46,204,113),new Color(39,174,96),new Color(230,126,34), new Color(211,84,0),new Color(52,152,219),new Color(41,128,185),new Color(231,76,60),new Color(192,57,43), new Color(155,89,182),new Color(142,68,173)};
                           //     0: blue/green       1: darker blue/green     2: yellow                3:  carrot         4: green/blue       5: green             6:    orange        7:  red/orange          8:  brightblue        9:  blue             10: red           11: dark red           12: violet            13:  darker violet
    static Color mainColor =new Color(127,140,141); // grey
    
    // notes stuff
    static String[] notes ={"A","Bb","B","C","Db","D","Eb","E","F","Gb","G","Ab"};
}
