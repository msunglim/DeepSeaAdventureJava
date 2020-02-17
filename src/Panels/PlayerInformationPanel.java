package Panels;

import main.Game;

import javax.swing.*;
import java.awt.*;

public class PlayerInformationPanel extends JPanel {

    public PlayerInformationPanel(int numberOfPlayer){

        setPreferredSize(new Dimension(100, 115));


        //generate player
        for (int i = 0; i < Game.getPlayerList().size(); i++){

            add(Game.getPlayerList().get(i).getPlayerPanel());
        }


    }
}
