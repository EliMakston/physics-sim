public class Vector2 {
    public double x;
    public double y;
    public double magnitude;

    public Vector2(double initX, double initY) {
        x = initX;
        y = initY;
        magnitude = this.getMagnitude();
    }

    public static Vector2 zero() {
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

    public static double dotProduct(Vector2 vector1, Vector2 vector2) {
        return (vector1.x * vector2.x) + (vector1.y * vector2.y);
    }

    public static double angleBetween(Vector2 vector1, Vector2 vector2) {
        double dotProduct = dotProduct(vector1, vector2);
        return Math.acos(dotProduct / (vector1.magnitude * vector2.magnitude));
    }

    public double getMagnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public static Vector2 normalize(Vector2 vector) {
        return new Vector2(vector.x / vector.magnitude, vector.y / vector.magnitude);
    }
}
