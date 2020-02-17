package Map;

import player.Player;

import javax.swing.*;
import java.awt.*;

import java.awt.geom.Rectangle2D;

public abstract class Token {
    private static Token token;

    protected int hiddenValue;

    protected JLayeredPane p = new JLayeredPane();
    protected JPanel shape;
    private Token previous;
    private Token next;
    private boolean hasPlayer;
    private Player player;

    public static Token getToken(int i) {

        switch (i) {
            case 1:
                token = new Treasure1();
                break; //옅은 삼각형
            case 2:
                token = new Treasure2();
                break; //굵은 사각형
            case 3:
                token = new Treasure3();
                break; //옅은 동그라미
            case 4:
                token = new Treasure4();
                break; //굵은동그라미
        }

        return token;
    }

    public int getHiddenValue() {
        return hiddenValue;
    }

    public abstract JLayeredPane getTokenPanel();

    //구매되었을때 시꺼멓게
    public void purchased() {
        p.removeAll();
        p.removeAll();
        p.setPreferredSize(new Dimension(52, 52));


        JPanel c = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;

                Shape rect = new Rectangle2D.Double(0, 0, 50, 50);

                g2.setColor(Color.BLACK);
                g2.fill(rect);


            }
        };
        //c.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.setBounds(0, 0, 50, 50);
        p.add(c, 0);
        addPlayer(player);


    }

    public void addPlayer(Player player) {

        //여기다넣으시오

        player.getPlayerAvartar().setBounds(-12, 10, 50, 50);
        p.add(player.getPlayerAvartar(), JLayeredPane.POPUP_LAYER);

        hasPlayer = true;
        this.player = player;
        p.repaint();


    }

    public void removePlayer(Player player) {

        p.getParent().getParent().getParent().repaint();//하도안되서 치트키썻다 ㅋㅋ

        hasPlayer = false;
        this.player = null;
        p.repaint();

    }

    public void setNext(Token t) {
        next = t;
    }

    public void setPrevious(Token t) {
        previous = t;
    }

    public Token getPrevious() {
        return previous;
    }

    public Token getNext() {
        return next;
    }

    public boolean isHasPlayer() {

        return hasPlayer;
    }

    public JPanel getShape() {
        return shape;
    }
}
