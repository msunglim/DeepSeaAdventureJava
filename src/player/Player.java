package player;

import Map.Token;
import Panels.DicePanel;
import Panels.OxygenPanel;
import main.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Player {
    private int point;
    private ArrayList<Integer> tokenList;
    private Color playerColor;

    private JPanel p; //네모
    private JPanel playerAvartar; //복제품


    private int location;
    private boolean returning; //돌아간다
    private boolean gotIn; //들어왔따

    private JLabel totalPoint; //총합산 하는거 라운드끝나구
    private JLabel numberOfTokens; //라운드내에서 가지고있는 토큰

    public Player(Color color) {
        point = 0;
        location = 0;
        tokenList = new ArrayList<>();
        playerColor = color;
        returning = false;
        gotIn = false;
        //System.out.println("생성확인");
    }

    public int getPoint() {
        return point;
    }

    public int getNumberOfTokens() {
        return tokenList.size();
    }

    public JPanel getPlayerPanel() {
        p = new JPanel();
        p.setPreferredSize(new Dimension(80, 110));
        p.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        p.add(Box.createRigidArea(new Dimension(0, 5)));
        JPanel c = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;


                Shape circle = new Ellipse2D.Double(20, 0, 40, 40);

                g2.setColor(playerColor);
                g2.fill(circle); //색으로 꽉찬 동그라미


            }
        };
        p.add(c);

        playerAvartar = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;


                Shape circle = new Ellipse2D.Double(25, 0, 25, 25);

                g2.setColor(playerColor);
                g2.fill(circle); //색으로 꽉찬 동그라미


            }
        };

        totalPoint = new JLabel("Total Point:" + point);
        totalPoint.setFont(new Font("Serif", Font.CENTER_BASELINE, 10));
        numberOfTokens = new JLabel("# of Tokens:" + tokenList.size());
        numberOfTokens.setFont(new Font("Serif", Font.CENTER_BASELINE, 10));
        p.add(totalPoint);
        p.add(numberOfTokens);

        return p;
    }


    public JPanel getPlayerAvartar() {
        playerAvartar.setPreferredSize(new Dimension(25, 25));
        playerAvartar.setOpaque(false);
        return playerAvartar;
    }


    public Color getPlayerColor() {
        return playerColor;
    }

    public void changeColor(Color color) {

        p.setBorder(BorderFactory.createLineBorder(color));

        p.validate();
    }


    public void move(int sum) {
        sum -= tokenList.size();


        for (int i = 0; i < sum; i++) {
            int jump = 0;

            //누군가를 스킵했다면 점프하니까 그 점프하기전의 이미지를 지울려면 저렇게해야하고
            // System.out.println(location);
            if (location < 0) {

                (Game.getPlayPanel().getTokenLinkedList().get(location + 1)).getTokenPanel().remove(0);
                (Game.getPlayPanel().getTokenLinkedList().get(location + 1)).getTokenPanel().repaint();
                gotIn = true;

                break;
            }

            if (!returning && location < 32) {
                //        System.out.println("전진뿐");
                if (location != 0) {
                    //남겨진 그림자 제거
                    (Game.getPlayPanel().getTokenLinkedList().get(location - 1)).removePlayer(this);
                }
                //전진전진


                //미숙한부분이있다.
                while(Game.getPlayPanel().getTokenLinkedList().get(location+jump).isHasPlayer()){
                    jump++;
                }


                (Game.getPlayPanel().getTokenLinkedList().get(location+jump)).addPlayer(this);

                location= location+jump+1;//이친구도 나중에 점프한만큼 올라가게해야지. 그러게위해선 token 이 hasPlyaer같은ㄱ ㅔ있어야해.
                if (location == 32) {
                    returning = true;
                    location = 31;

                }
            } //종착역찍었거나 리턴선언뒤
            else if (returning && location >= 0) {
                //    System.out.println("뺄떈빼야지");

                (Game.getPlayPanel().getTokenLinkedList().get(location)).removePlayer(this);
                //   System.out.println("나ㄴㅏ가요" + location);

                if (location != 0) {
                    (Game.getPlayPanel().getTokenLinkedList().get(location - 1)).addPlayer(this);
                    //       System.out.println("여따가넣었소" + (location));
                }
                location--;

            }
            //       System.out.println("---");
            if (location < 0) {

                (Game.getPlayPanel().getTokenLinkedList().get(location + 1)).getTokenPanel().remove(0);
                (Game.getPlayPanel().getTokenLinkedList().get(location + 1)).getTokenPanel().repaint();
                gotIn = true;

                break;
            }

        }
        Game.getPlayPanel().getOxygenPanel().decreaseOxygen(tokenList.size());
        if (sum > 0) {
            //구매여부물어봄
            if (!gotIn) {


                JFrame purchaseFrame = new JFrame();
                purchaseFrame.setPreferredSize(new Dimension(700, 400));
                JPanel purchasePanel = new JPanel();

                purchasePanel.setLayout(new BoxLayout(purchasePanel, BoxLayout.Y_AXIS));
                JLabel ask = new JLabel("Would you purchase the token?", SwingConstants.CENTER);


                JButton yes = new JButton("YES");
                JButton no = new JButton("NO");


                Token curr;

                if (returning) {
                    curr = Game.getPlayPanel().getTokenLinkedList().get(location);
                    //    System.out.println("현재는 " + location);
                    JPanel tokenImage = Game.getPlayPanel().getTokenLinkedList().get(location).getShape();
                    tokenImage.setAlignmentX(Component.CENTER_ALIGNMENT);
                    purchasePanel.add(tokenImage);


                } else {
                    curr = Game.getPlayPanel().getTokenLinkedList().get(location - 1);
                    //         System.out.println("현재는 w전진" + (location - 1));
                    JPanel tokenImage = Game.getPlayPanel().getTokenLinkedList().get(location - 1).getShape();
                    tokenImage.setAlignmentX(Component.CENTER_ALIGNMENT);
                    purchasePanel.add(tokenImage);
                }

                yes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tokenList.add(curr.getHiddenValue());
                        curr.purchased();
                        //          System.out.println("지점 " + Game.getPlayPanel().getTokenLinkedList().indexOf(curr) + "은 먹혔다");

                        updateNumberOfToken();
                        purchaseFrame.dispose();

                    }
                });
                no.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        purchaseFrame.dispose();
                    }
                });

                if (!curr.isPurchased()) {
                    //    System.out.println("먹히지않았소");
                    purchasePanel.add(ask);
                    purchasePanel.add(yes);
                    purchasePanel.add(no);

                    purchaseFrame.add(purchasePanel);
                    purchaseFrame.pack();
                    purchaseFrame.setVisible(true);
                    purchaseFrame.setLocationRelativeTo(null);
                }
            }
        }

        //after move, next turn
        Game.getPlayPanel().nextTurn();
    }

    private void updateNumberOfToken() {
        numberOfTokens.setText("# of Tokens:" + tokenList.size());
        p.repaint();
    }

    public void setReturning(boolean tf) {
        if (returning) {
            System.out.println("The player has already declared Returning");
        }
        returning = tf;
    }

    public void setLocation(int i) {
        location = i;
    }

    public int getLocation() {
        return location;
    }
}
