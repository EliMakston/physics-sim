public class Particle {
    double xposition = 50;
    double yposition = 50;
    int diameter = 50;
    double xacceleration = 0;
    double yacceleration = 0;
    double yvelocity = 0;
    double xvelocity = 0;
    int frameSize = 0;
    Particle(int size, int frameSize) {
        diameter = size;
        this.frameSize = frameSize;
    }
}
