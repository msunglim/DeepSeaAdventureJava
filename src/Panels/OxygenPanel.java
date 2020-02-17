package Panels;

import javax.swing.*;
import java.awt.*;

public class OxygenPanel extends JPanel {
    private int oxygen;
    JLabel oxygenLabel;
    JPanel[] boxList = new JPanel[25];

    public OxygenPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(100, 700));
        oxygen = 25;
        oxygenLabel = new JLabel("Oxygen: " + oxygen);
        oxygenLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(oxygenLabel);

        for (int i = 0; i < 25; i++) {
            JPanel box = new JPanel();
            box.setMaximumSize(new Dimension(40, 25));
            box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            box.setBackground(Color.blue);
            box.setAlignmentX(Component.CENTER_ALIGNMENT);
            boxList[i] = box;
            add(box);
        }

    }


    public void decreaseOxygen(int i) {


        for (int k = oxygen; k > oxygen - i; k--) {

            if (25 - k < 25) {
                boxList[25 - k].setBackground(Color.WHITE);
                boxList[25 - k].validate();
            }
        }


        oxygen -= i;
        oxygenLabel.setText("Oxygen: " + oxygen);
        validate();

    }
    public int getOxygen(){
        return oxygen;
    }
}
