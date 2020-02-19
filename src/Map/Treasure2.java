package Map;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Treasure2 extends Token {

    public Treasure2(){
        hiddenValue = (int)(Math.random()*4+ 4);
    }
    public JLayeredPane getTokenPanel(){
        p.setPreferredSize(new Dimension(52, 52));



        JPanel c = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;

                Shape rect = new Rectangle2D.Double(0,0,50,50);

                g2.setColor(Color.LIGHT_GRAY);
                g2.fill(rect);




            }
        };
        //c.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.setBounds(0, 0, 50, 50);
        p.add(c, 0);

        shape = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {

                Graphics2D g2 = (Graphics2D) g;

                Shape rect = new Rectangle2D.Double(0,0,50,50);

                g2.setColor(Color.LIGHT_GRAY);
                g2.fill(rect);



            }
        };

        return p;

    }
}
