import javax.swing.*;
import java.awt.*;
import java.applet.*;


public class Lab4 extends JFrame {
    float[][] STM = new float [entries][outputs];
    int [][] LOG = new int[ent_max][2];
    String game = new String;
    int i, k, j, situation, play, turn, times;

    //Basic Loop
    public Lab4(){
        for (times = 1; times <= times_max; times = times + 1){
            game = "Playing";
            situation = 0;
            turn = 0;
            while (game == "Playing"){
                turn = turn + 1;
                play = selection(situation, STM);
            }
        }
    }
}

