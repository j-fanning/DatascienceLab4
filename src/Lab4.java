import javax.swing.*;
import java.awt.*;
import java.applet.*;
import java.util.Random;
import java.util.Scanner;


import static java.lang.Math.random;


public class Lab4 extends JFrame {
    FindAllBoards.Board[] boards = FindAllBoards.findAllBoards();
    int outputs = 9;
    String game;
    int i, k, j, play, turn, times, turnmx, times_max, nplays;
    int situation;
    String mode;
    double b;
    //Basic Loop that plays a game times_max times
    public Lab4() {
        //All possible boards that are not full or a player is more than 1 step ahead of other player
        for (int i = 0; i < boards.length; i++) {
            System.out.println(boards[i]);
        }
        System.err.println("Total unique boards: "+ boards.length);
        System.out.println("Tic Tac toe");
        Scanner scan = new Scanner(System.in);
        System.out.println("Amount of sessions: ");
        times_max = scan.nextInt();
        System.out.println("Beta value: ");
        b = scan.nextDouble();
        System.out.println("Mode: ");
        mode = scan.next();
        //[entries][outputs]
        float[][] STM = new float[916][outputs];
        //[ent_max][2]
        int[][] LOG = new int[9][2];
        //runs for how many games user said to run through (times_max)
        for (times = 1; times <= times_max; times = times + 1) {
            game = "Playing";
            //situation
            situation = 0;
            turn = 0;
            //ex: turn 1 player 1 goes
            while (game == "Playing") {
                turn = turn + 1;
                //stores the play of player 1 from that turn.
                play = selection(STM, situation);
                //situation is the state the board is in
                LOG[turn][1] = situation;
                //play is the move this player made in response to the situation.
                LOG[turn][2] = play;
                //check if there is a winner or tie
                game = evalua(situation);
                //ex: turn 1 player 2 goes
                if (game == "Playing") {
                    //situation is the state the board is in
                    situation = otherPlay(play);
                    //check if there is a winner or tie
                    game = evalua(situation);
                }
            }
            //how many turns have passed
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
        return "Playing";
    }

    // Selection Process (mode: random or max )
    int selection(float[][] STM, Integer situation) {
        int selectionMade = 0;
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
            while (selectionMade == 0){
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
            //i= state of board on turn
            i = LOG[turn][1];
            //k= move player made on turn
            k = LOG[turn][2];
            nplays = (int) STM[turn][0]; //possible plays
            //In Reward increase probability
            if (game == "Won") {
                reward = (float) (b * (1 - STM[i][k]));
                STM[i][k] = STM[i][k] + reward;
                normal = reward / (nplays - 1);
                for (j = 1; j <= outputs; j = j + 1)
                    if ((j != k) & (STM[i][j] != 0 )){
                    STM[i][j] = STM[i][j] - normal;
                }
            }
            // In Punishment reduce probability
            else{
                punish = (float) (b / 2 * STM[i][k]);
                STM[i][k] = STM[i][k] - punish;
                normal = punish / (nplays - 1);
                for (j = 1; j <= outputs; j = j + 1) {
                    if ((j != k) & (STM[i][j] != 0 )){
                        STM[i][j] = STM[i][j] + normal;
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        Lab4 lab = new Lab4();
        return;
    }
}
