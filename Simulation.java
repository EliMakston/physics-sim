import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Simulation implements ActionListener{
    static double[] xpositions = {0};
    static double[] ypositions = {0};
    static int[] diameter = {0};
    static final int frameSize = 500;
    static Particle[] particles = {new Particle(50, frameSize, 50, 50), new Particle(10, frameSize, 50, 0), new Particle(20, frameSize, 30, 50)};
    static double collisionDamping = 1;
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
            double distance = Math.sqrt((Math.pow((particles[i].center()[0]) - (particle.center()[0]), 2) + Math.pow((particles[i].center()[1]) - (particle.center()[1]), 2)));
            if (distance <= (particle.radius) + (particles[i].radius)) {
                particle.x -= particle.velocity.x;
                particle.y -= particle.velocity.y;
                Vector2 toCollidedParticle = new Vector2(particles[i].center()[0] - particle.center()[0], particles[i].center()[1] - particle.center()[1]);
                toCollidedParticle = Vector2.normalize(toCollidedParticle);
                particle.velocity.add(toCollidedParticle.negative());
            } else {
            }
        }
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
