import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Panel extends JPanel{
    Panel() {
        this.setPreferredSize(new Dimension(500, 500));
    }

    private double[] xpositions = {0};
    private double[] ypositions = {0};
    private int[] diameter = {0};

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        for (int i = 0; i < xpositions.length; i++) {
            g2D.drawOval((int) xpositions[i], (int) ypositions[i], diameter[i], diameter[i]);
        }
    }

    public void setPositions(double[] xpositions, double[] ypositions, int[] diameter) {
        this.xpositions = xpositions.clone();
        this.ypositions = ypositions.clone();
        this.diameter = diameter.clone();
    }
}
