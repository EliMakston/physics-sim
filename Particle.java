public class Particle {
    Vector2 velocity = Vector2.zero();
    Vector2 acceleration = Vector2.zero();
    double x = 0;
    double y = 0;
    int diameter = 50;
    double radius;
    int mass = 0;
    int frameSize = 0;
    Particle(int size, int frameSize, int xStart, int yStart) {
        diameter = size;
        radius = diameter / 2;
        this.frameSize = frameSize;
        x = xStart;
        y = yStart;
        mass = size;
    }
    public double[] findCoordsOfIntersection(double x, double y) {
        double vX = x - (this.x + (diameter / 2));
        double vY = y - (this.y + (diameter / 2));
        double magV = Math.sqrt(vX*vX + vY*vY);
        double aX = ((this.x + (diameter / 2)) + vX) / (magV * (diameter / 2));
        double aY = ((this.y + (diameter / 2)) + vY) / (magV * (diameter / 2));
        return new double[] {aX, aY};
    }

    public double[] center() {
        return new double[] {x + radius, y + radius};
    }
}
