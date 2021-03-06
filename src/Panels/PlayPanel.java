package Panels;

import Map.Token;
import main.Game;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class PlayPanel extends JPanel {


    private int turn;
    private Game game;
    private int numberOfPlayer;
    private static LinkedList<Token> tokenLinkedList = new LinkedList<>();
    private OxygenPanel op;
    private DicePanel dp;

    public PlayPanel(Game game, int numberOfPlayer) {
        this.game = game;
        this.numberOfPlayer = numberOfPlayer;
        turn = 0;
        setLayout(new BorderLayout());


        //NORTH
        PlayerInformationPanel pip = new PlayerInformationPanel(numberOfPlayer);

        add(pip, BorderLayout.NORTH);
        game.getPlayerList().get(turn % numberOfPlayer).changeColor(Color.CYAN);

        //WEST
        op = new OxygenPanel();
        add(op, BorderLayout.WEST);
//op.decreaseOxygen(5);

        //EAST
         dp = new DicePanel(game.getPlayerList().get(turn % numberOfPlayer));

        add(dp, BorderLayout.EAST);


        //CENTER
        JPanel map = new JPanel();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                Token token = Token.getToken(i + 1);
                map.add(token.getTokenPanel());
                tokenLinkedList.add(token);


            }
        }

        add(map, BorderLayout.CENTER);

        ListIterator<Token> iterator = tokenLinkedList.listIterator();
        while (iterator.hasNext()) {
            Token t = tokenLinkedList.listIterator().next();

            if (!tokenLinkedList.listIterator().hasPrevious()) {
                t.setPrevious(null);
            } else {
                t.setPrevious(tokenLinkedList.listIterator().previous());
            }
            if (!tokenLinkedList.listIterator().hasNext()) {
                t.setNext(null);
            } else {
                t.setNext(tokenLinkedList.listIterator().next());
            }
            iterator.set(iterator.next());
        }
    }

    public void nextTurn() {
//current player 찾는 공식임 ㅇㅇ
        game.getPlayerList().get(turn % numberOfPlayer).changeColor(Color.GRAY);

        turn++;
        int jump = 0;
        while(jump < numberOfPlayer && game.getPlayerList().get((turn +jump)% numberOfPlayer).isGotIn()){
            jump++;
        }
        game.getPlayerList().get((turn+jump) % numberOfPlayer).changeColor(Color.CYAN);
        dp.setPlayer(game.getPlayerList().get((turn+jump) % numberOfPlayer));

        turn+= jump;
    }

    public static LinkedList<Token> getTokenLinkedList() {
        return tokenLinkedList;
    }

    public OxygenPanel getOxygenPanel(){
        return op;
    }
}
