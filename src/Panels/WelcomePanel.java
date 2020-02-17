package Panels;

import main.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WelcomePanel extends JPanel {

    private int numberOfPlayer;

    public WelcomePanel(Game game) {
        setLayout(new BorderLayout());

        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        boxPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        JLabel ask = new JLabel("Number of Players is");
        ask.setAlignmentX(Component.CENTER_ALIGNMENT); //가운데정렬
        boxPanel.add(ask);

        JPanel answerPanel = new JPanel();


        //  answerPanel.setPreferredSize(new Dimension(1200, 300));
        //  answerPanel.setMinimumSize(new Dimension(1200, 300));
        answerPanel.setMaximumSize(new Dimension(200, 30));
        //   answerPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        JTextField tf = new JTextField(1);
        tf.setBorder(BorderFactory.createLineBorder(Color.blue));
        tf.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                if (tf.getText().length() >= 1) {// limit to 3 characters
                    e.consume(); //씹는다

                    System.out.println("Input should be one digit");

                } else if ((int) e.getKeyChar() - 48 < 2 || (int) e.getKeyChar() - 48 > 6) {
                    e.consume(); //씹는다

                    System.out.println("Input should be one digit integer from 2~6");

                }

            }
        });
        answerPanel.add(tf);

        //tf.getText();


        boxPanel.add(answerPanel);


        JButton next = new JButton("Confirm");
        next.setAlignmentX(Component.CENTER_ALIGNMENT); //가운데정렬
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tf.getText().length() != 1) {

                    System.out.println("Please type an input");
                } else {
                    numberOfPlayer = Integer.parseInt(tf.getText());
                    game.showPlayPanel(numberOfPlayer);
                }
            }
        });
        boxPanel.add(next);

        add(boxPanel, BorderLayout.CENTER);


        JPanel right = new JPanel();
        right.setPreferredSize(new Dimension(100, 300));

        JButton rule = new JButton("Rule");
        rule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Runtime.getRuntime().exec("cmd /c start http://games.everybookinchina.com/deepsea.php");
                } catch (Exception ex) {

                }
            }
        });

        right.add(rule);
        add(right, BorderLayout.EAST);

        JButton master = new JButton("Master");
        master.setAlignmentX(Component.CENTER_ALIGNMENT); //가운데정렬
        master.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                game.showPlayPanel(3);
            }
        });


        add(master, BorderLayout.SOUTH);
    }
}
