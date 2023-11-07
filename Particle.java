public class Particle {
    double xposition = 0;
    double yposition = 0;
    int diameter = 50;
    double xacceleration = 0;
    double yacceleration = 0;
    double yvelocity = 0;
    double xvelocity = 0;
    int mass = 0;
    int frameSize = 0;
    Particle(int size, int frameSize, int xStart, int yStart) {
        diameter = size;
        this.frameSize = frameSize;
        xposition = xStart;
        yposition = yStart;
        mass = size;
    }
}
