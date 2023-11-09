import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Simulation implements ActionListener{
    static double[] xpositions = {0};
    static double[] ypositions = {0};
    static int[] diameter = {0};
    static final int frameSize = 500;
    static Particle[] particles = {new Particle(50, frameSize, 0, 50), new Particle(10, frameSize, 0, 0)};
    static double collisionDamping = 0.9;
    static final int FPS = 60;
    static Vector2 gravity = new Vector2(0, 9.8 / FPS);
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
            particles[i].velocity.add(particles[i].acceleration.sum(gravity));
            particles[i].x += (particles[i].velocity.x);
            particles[i].y += (particles[i].velocity.y);
            resolveCollisions(particles[i], i);
            newx[i] = particles[i].x;
            newy[i] = particles[i].y;
        }
        xpositions = newx.clone();
        ypositions = newy.clone();
        diameter = newdiameter.clone();
    }


    public static void resolveCollisions(Particle particle, int index) {
        if (particle.x + particle.diameter > frameSize) {
            particle.x = frameSize - (particle.diameter);
            particle.velocity.x = -1 * particle.velocity.x * collisionDamping;
        }

        if (particle.x < 0) {
            particle.x = 0;
            particle.velocity.x = -1 * particle.velocity.x * collisionDamping;
        }

        if (particle.y + particle.diameter > frameSize) {
            particle.y = frameSize - (particle.diameter);
            particle.velocity.y = -1 * particle.velocity.y * collisionDamping;
        }

        if (particle.y < 0) {
            particle.y = 0;
            particle.velocity.y = -1 * particle.velocity.y * collisionDamping;
        }

        for (int i = 0; i < particles.length; i++) {
            if (i == index) {
                continue;
            }
            // Thank Ronald :)
            double distance = Math.sqrt((Math.pow((particles[i].x + particles[i].radius) - (particle.x + particle.radius), 2) + Math.pow((particles[i].y + particles[i].radius) - (particle.y + particle.radius), 2)));
            if (distance <= (particle.radius) + (particles[i].radius)) {
                // Move particle back along the line of resistance out of the circle (take direvative of distance function?) 
                System.out.println("Collision detected");
                particle.x -= particle.velocity.x;
                particle.y -= particle.velocity.y;
                particle.velocity.y = particle.velocity.negative().y * collisionDamping;
                // double[] newcoords = particles[i].findCoordsOfIntersection(particle.x, particle.y);
                // System.out.println(newcoords[0] + particle.diameter);
                // System.out.println(newcoords[1] + particle.diameter);
                // particle.x = newcoords[0] + particle.diameter + particles[i].x;
                // particle.y = newcoords[1] + particle.diameter + particles[i].y;
                //particle.velocity.y = -particle.velocity.y * collisionDamping;
            } else {
                System.out.println("No collision");
            }
        }
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
