public class Vector2 {
    public double x;
    public double y;

    public Vector2(double initX, double initY) {
        x = initX;
        y = initY;
    }

    public Vector2 zero() {
        return new Vector2(0.00, 0.00);
    }

    public void add(Vector2 additive) {
        this.x = additive.x + this.x;
        this.y = additive.y + this.y;
    }

    public Vector2 negative() {
        return new Vector2(-this.x, -this.y);
    }

    public Vector2 sum(Vector2 additive) {
        double x = additive.x + this.x;
        double y = additive.y + this.y;
        return new Vector2(x, y);
    }
}
