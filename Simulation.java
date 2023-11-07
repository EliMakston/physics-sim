import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Simulation implements ActionListener{
    static double[] xpositions = {0};
    static double[] ypositions = {0};
    static int[] diameter = {0};
    static double[] xvelocity = {0};
    static double[] yvelocity = {0};
    static double[] xacceleration = {0};
    static double[] yacceleration = {0};
    static final int frameSize = 500;
    static Particle[] particles = {new Particle(50, frameSize)};
    static double collisionDamping = 1;
    static final int FPS = 60;
    static final double gravity = 9.8;
    public static void main(String[] args) {
        Window frame = new Window("Simulation");
        int delay = (1000 / FPS);
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                runSimulation();
                frame.runSimulation(xpositions, ypositions, diameter);
            }
        };
        new Timer(delay, taskPerformer).start();
    }

    public static void runSimulation() {
        double[] newx = new double[particles.length];
        double[] newy = new double[particles.length];
        int[] newdiameter = new int[particles.length];
        for (int i = 0; i < particles.length; i++) {
            newdiameter[i] = particles[i].diameter;
            particles[i].xvelocity += particles[i].xacceleration;
            particles[i].yvelocity += particles[i].yacceleration  + (gravity / FPS);
            particles[i].xposition += (particles[i].xvelocity);
            particles[i].yposition += (particles[i].yvelocity);
            resolveCollisions(particles[i], i);
            newx[i] = particles[i].xposition;
            newy[i] = particles[i].yposition;
        }
        xpositions = newx.clone();
        ypositions = newy.clone();
        diameter = newdiameter.clone();
    }


    public static void resolveCollisions(Particle particle, int index) {
        if (particle.xposition + particle.diameter > frameSize) {
            particle.xposition = frameSize - (particle.diameter);
            particle.xvelocity = -1 * particle.xvelocity * collisionDamping;
        }

        if (particle.xposition < 0) {
            particle.xposition = 0;
            particle.xvelocity = -1 * particle.xvelocity * collisionDamping;
        }

        if (particle.yposition + particle.diameter > frameSize) {
            particle.yposition = frameSize - (particle.diameter);
            particle.yvelocity = -1 * particle.yvelocity * collisionDamping;
        }

        if (particle.yposition < 0) {
            particle.yposition = 0;
            particle.yvelocity = -1 * particle.yvelocity * collisionDamping;
        }
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
