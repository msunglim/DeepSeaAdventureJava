package Panels;

import main.Game;
import player.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DicePanel extends JPanel {

    int d1;
    int d2;
    DicePanel thisthis;
    JLabel jl;

    public DicePanel(Player player) {
        thisthis = this;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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

        add(roll);
        add(jl);
    }

    public void updateText() {
        jl.setText("Dice1:" + d1 + " Dice2:" + d2);
        this.validate();
    }

}
