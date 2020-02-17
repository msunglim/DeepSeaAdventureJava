package Map;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Treasure3 extends Token {

    public Treasure3(){
        hiddenValue = (int)(Math.random()*4+8);
    }

    public JLayeredPane getTokenPanel(){
        p.setPreferredSize(new Dimension(52, 52));


        JPanel c = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                Shape circle = new Ellipse2D.Double(0, 0, 50, 50);

                g2.setColor(Color.CYAN);
                g2.fill(circle); //색으로 꽉찬 동그라미




            }
        };
        //c.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.setBounds(0, 0, 50, 50);
        p.add(c, 0);
        shape = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                Shape circle = new Ellipse2D.Double(0, 0, 50, 50);

                g2.setColor(Color.CYAN);
                g2.fill(circle); //색으로 꽉찬 동그라미

            }
        };
        c.setBounds(0, 0, 50, 50);
        return p;

    }
}
