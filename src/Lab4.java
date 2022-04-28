import javax.swing.*;
import java.awt.*;
import java.applet.*;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.random;


public class Lab4 extends JFrame {
    int outputs = 9;
    //[entries][outputs]
    float[][] STM = new float[1000][outputs];
    //[ent_max][2]
    int[][] LOG = new int[1000][2];
    String game;
    int i, k, j, situation, play, turn, times, turnmx, times_max, nplays;
    String mode;
    double b;
    //Basic Loop that plays a game times_max times
    public Lab4() {
        System.out.println("Tic Tac toe");
        Scanner scan = new Scanner(System.in);
        System.out.println("Amount of sessions: ");
        times_max = scan.nextInt();
        System.out.println("Beta value: ");
        b = scan.nextDouble();
        System.out.println("Mode: ");
        mode = scan.next();

        for (times = 1; times <= times_max; times = times + 1) {
            game = "Playing";
            situation = 0;
            turn = 0;
            while (game == "Playing") {
                turn = turn + 1;
                play = selection(STM, situation);
                LOG[turn][1] = situation;
                LOG[turn][2] = play;
                game = evalua(situation);
                if (game == "Playing") {
                    situation = otherPlay(play);
                    game = evalua(situation);
                }
            }
            turnmx = turn;
            compensation(STM, LOG, game);
        }
    }
    //placeholder
    private int otherPlay(int play) {
        return play;
    }
    //placeholder
    private String evalua(int situation) {
        return "";
    }

    // Selection Process (mode: random or max )
    int selection(float[][] STM, Integer situation) {
        int selectionMade;
        float cumulative, number;
        if (mode == "max") {
            int max = 1;
            for (j = 1; j <= outputs; j = j + 1) {
                if (STM[situation][j] > STM[situation][max]) {
                    max = j;
                    selectionMade = max;
                }
            }
        }
        else {
            Random random = new Random();
            random.setSeed(7);
            number = (float) random();
            cumulative = 0;
            selectionMade = 0;
            j = 0;
            while (selectionMade <> 0){
                j = j + 1;
                cumulative = cumulative + STM[situation][j];
                if (cumulative > number)
                    selectionMade = j;
            }
        }
        return selectionMade;
    }

    void compensation(float[][] STM, int[][] LOG, String game) {
        float reward, punish, normal;
        for (turn = 1; turn <= turnmx; turn = turn + 1) {
            i = LOG[turn][1];
            k = LOG[turn][2];
            nplays = (int) STM[turn][0]; //possible plays
            //In Reward increase probability
            if (game == "Won") {
                reward = (float) (b * (1 - STM[i][k]));
                STM[i][k] = STM[i][k] + reward;
                normal = reward / (nplays - 1);
                for (j = 1; j <= outputs; j = j + 1)
                    if ((j < > k) & (STM[i][j] <> 0 )){
                    STM[i][j] = STM[i][j] - normal;
                }
            }
            // In Punishment reduce probability
            else{
                punish = (float) (b / 2 * STM[i][k]);
                STM[i][k] = STM[i][k] - punish;
                normal = punish / (nplays - 1);
                for (j = 1; j <= outputs; j = j + 1) {
                    if ((j <> k) & (STM[i][j] <> 0 )){
                        STM[i][j] = STM[i][j] + normal;
                    }
                }
            }

        }
    }
    public static void main(String[] args) {
        Lab4 lab = new Lab4();
    }
}
