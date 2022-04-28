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
        void compensation(STM,LOG,game){
            float reward, punish,normal;
            for(turn=1; turn <= turnmax; turn = turn+1){
                i= LOG[turn][1];
                k= LOG[turn][2];
                nplays= STM[turn][0];
                //possible plays
                //In Reward increase probability
                if(game == "Won"){
                    reward = ß*(1-STM[i][k] );
                    STM[i][k] = STM[i][k] + reward;
                    normal = reward/(nplays-1);
                    for ( j = 1; j <= outputs ; j = j+ 1 )
                    if (( j <> k ) & ( STM[i][j] <> 0 )){
                        STM[i][j] = STM[i, j]-normal;
                    }
                }

            }

        }
    }
}

