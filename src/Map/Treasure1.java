package Map;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;


public class Treasure1 extends Token {


    public Treasure1() {
        hiddenValue = (int) (Math.random() * 4);
    }

    public JLayeredPane getTokenPanel() {
        p.setPreferredSize(new Dimension(52, 52));



        JPanel c = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {

                Polygon poly = new Polygon(
                        new int[]{25, 50, 0},
                        new int[]{0, 50, 50},
                        3);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(Color.pink);
                g2d.fill(poly);


            }
        };
        //c.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.setBounds(0, 0, 50, 50);
        p.add(c,0);
        shape = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {

                Polygon poly = new Polygon(
                        new int[]{25, 50, 0},
                        new int[]{0, 50, 50},
                        3);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(Color.pink);
                g2d.fill(poly);


            }
        };
        c.setBounds(0, 0, 50, 50);
        return p;
    }
}
