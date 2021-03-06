package Panels;

import main.Game;
import player.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DicePanel extends JPanel {

    private int d1;
    private int d2;
    private DicePanel thisthis;
    private JLabel jl;

    private Player player;
    public DicePanel(Player p) {
        thisthis = this;
        player = p;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        JButton declareReturn = new JButton("Return!");
        declareReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             //   declareReturn.removeActionListener(declareReturn.getActionListeners()[0]);
                player.setReturning(true);
                player.setLocation(player.getLocation() -1); //리턴시 로케이션이 그전에 +1이기떄문에 1단계낮춰준다.
         //       System.out.println("리턴명령하달");
            }
        });

        JButton roll = new JButton("Roll Dice");


        jl = new JLabel();
        jl.setText("Dice1:" + d1 + " Dice2:" + d2);
        roll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Game.getPlayPanel().getOxygenPanel().getOxygen() <= 0) {
                    roll.removeActionListener(roll.getActionListeners()[0]);

                } else {

                    d1 = (int) (Math.random() * 3 + 1);


                    d2 = (int) (Math.random() * 3 + 1);

                    updateText();

                    //     player.rollDice(thisthis);

                    player.move(d1 + d2);
                }

            }
        });

        add(declareReturn);
        add(roll);
        add(jl);
    }

    public void updateText() {
        jl.setText("Dice1:" + d1 + " Dice2:" + d2);
        this.validate();
    }
    public void setPlayer(Player player){
        this.player = player;

    }

}
