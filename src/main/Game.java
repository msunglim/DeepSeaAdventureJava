package main;

import Panels.PlayPanel;
import Panels.WelcomePanel;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game {

    private JFrame jf;
    private WelcomePanel wp;
    private static int round = 1;
    private static PlayPanel pp;
    private static ArrayList<Player> playerList;
    private Color [] colorList= {Color.red, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.blue, Color.GRAY };
    public static void main(String[] args) {
        new Game();
    }

    public Game() {

        jf = new JFrame();
        jf.setPreferredSize(new Dimension(1280, 820));

        jf.pack();//이것은 알아서 resize를 해준다.
        jf.setVisible(true);
        jf.setLocationRelativeTo(null); //이것은 창을 모니터 가운데에 띄우게해준다.

        showWelcomePanel();
    }

    private void showWelcomePanel() {
        wp = new WelcomePanel(this);

        jf.setContentPane(wp);
        jf.validate();
    }

    public void showPlayPanel(int numberOfPlayer) {
        jf.remove(wp);
        playerList = new ArrayList<>();
        for (int i = 0; i < numberOfPlayer; i++) {
            playerList.add(new Player(colorList[i]));

        }
       // System.out.println("총 플레이어는 "+ numberOfPlayer +"명입니다.");
        pp = new PlayPanel(this, numberOfPlayer);

        jf.setContentPane(pp);
        jf.validate();

    }

    private void nextRound() {
        round++;
    }

    public int getRound() {
        return round;
    }

    public static ArrayList<Player> getPlayerList(){
        return playerList;
    }

    public static PlayPanel getPlayPanel(){
        return pp;
    }
}
