import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        this.setTitle("Painting");
        this.setBounds(250, 300, 300, 250);

        this.getContentPane().add(panelPaint);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }

    private PanelPaint panelPaint = new PanelPaint();
}

class PanelPaint extends JPanel {

    public PanelPaint() {
        super();

        this.add(new JButton("Test") {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                System.out.println(i++);
            }
        });

    }
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawString("Start",0,40);
        g.drawImage(new ImageIcon("ball.gif").getImage(),40,80,null);
        g.drawRect(40,40,120,80);
        g.drawLine(60,60,40,60);

    }

    public static int i = 0;
}
