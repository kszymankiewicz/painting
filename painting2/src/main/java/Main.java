import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class Main extends JFrame {
    public Main() {

        this.setTitle("Ball Animation");
        this.setBounds(250, 300, 300, 250);
        animationPanel.setBackground(Color.GRAY);
        JButton bStart = (JButton) buttonPanel.add(new JButton("Start"));

        bStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startAnimation();
            }
        });

        this.getContentPane().add(animationPanel);
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void startAnimation() {
        animationPanel.addBall();
    }

    private JPanel buttonPanel = new JPanel();
    private AnimationPanel animationPanel = new AnimationPanel();

    public static void main(String[] args) {
        new Main().setVisible(true);
    }

    class AnimationPanel extends JPanel {

        public void addBall() {
            listBall.add(new Ball());

            for (int i = 0; i < 2500; i++) {
                for (int j = 0; j < listBall.size(); j++) {
                    ((Ball) listBall.get(j)).moveBall(this);
                    this.paint(this.getGraphics());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (int i = 0; i < listBall.size(); i++) {

                g.drawImage(Ball.getImg(), ((Ball) listBall.get(i)).x, ((Ball) listBall.get(i)).y, null);
            }
        }

        ArrayList listBall = new ArrayList();
    }
}

class Ball {
    public static Image getImg() {
        return Ball.ball;
    }

    public void moveBall(JPanel container) {
        Rectangle boundsPanel = container.getBounds();

        x += dx;
        y += dy;

        if (y + yBalls >= boundsPanel.getMaxY()) {
            y = (int) (boundsPanel.getMaxY() - yBalls);
            dy = -dy;
        }
        if (x + xBalls >= boundsPanel.getMaxX()) {
            x = (int) (boundsPanel.getMaxX() - xBalls);
            dx = -dx;
        }
        if (y < boundsPanel.getMinY()) {
            y = (int) boundsPanel.getMinY();
            dy = -dy;
        }
        if (x < boundsPanel.getMinX()) {
            x = (int) boundsPanel.getMinX();
            dx = -dx;
        }
    }


    public static Image ball = new ImageIcon("/Users/krzesim/IdeaProjects/painting/painting2/src/main/resources/ball.gif").getImage();
    int x = 0;
    int y = 0;
    int dx = 1;
    int dy = 1;
    int xBalls = ball.getWidth(null);
    int yBalls = ball.getHeight(null);
}