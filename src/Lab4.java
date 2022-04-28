import javax.swing.*;
import java.awt.*;
import java.applet.*;
import java.util.Scanner;


public class Lab4 extends JFrame {
    float[][] STM = new float [entries][outputs];
    int [][] LOG = new int[ent_max][2];
    String game = new String;
    int i, k, j, situation, play, turn, times,times_max;

    //Basic Loop
    public Lab4(){
        System.out.println("Tic Tac toe");
        Scanner scan = new Scanner(System.in);
        System.out.println("Amount of sessions:");
        times_max = scan.nextInt();
        System.out.println("Beta value:");
        double b = scan.nextDouble();
        for (times = 1; times <= times_max; times = times + 1){
            game = "Playing";
            situation = 0;
            turn = 0;
            while (game == "Playing"){
                turn = turn + 1;
                play = selection(situation, STM);
            }

        }
        void compensation(float STM[],int LOG[],string game,double b){
            float reward, punish,normal;
            for(turn=1; turn <= LOG.length; turn = turn+1){
                i= LOG[turn][1];
                k= LOG[turn][2];
                nplays = STM[turn][0];
                //possible plays
                //In Reward increase probability
                if(game == "Won"){
                    reward = b*(1-STM[i][k] );
                    STM[i][k] = STM[i][k] + reward;
                    normal = reward/(nplays-1);
                    for ( j = 1; j <= outputs ; j = j+ 1 )
                    if (( j <> k ) & ( STM[i][j] <> 0 )){
                        STM[i][j] = STM[i, j]-normal;
                    }
                    // In Punishment reduce probability
                    else { punish = b/2*STM[i][k];
                        STM[i][k] = STM[i][k] - punish;
                        normal = punish/(nplays-1);
                        for ( j= 1; j <= outputs ; j = j+ 1 )
                        {
                            if (( j <> k ) & ( STM[i][j] <> 0 )){
                            STM[i][j] = STM[i,j] + normal } } }
                }

            }

        }
        }
    public static void main(String[] args){
        Lab4 start = new Lab4;
    }

}

