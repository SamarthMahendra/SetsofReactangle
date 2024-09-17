package box;
import java.util.ArrayList;

public class RectangleShape implements Shape {
    private double x;  // x-coordinate of the bottom-left corner
    private double y;  // y-coordinate of the bottom-left corner
    private double width;  // Width of the rectangle
    private double height;  // Height of the rectangle

    public RectangleShape(double x, double y, double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive");
        }
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    public RectangleShape intersection(RectangleShape other) {
        double x1 = Math.max(this.x, other.x);
        double y1 = Math.max(this.y, other.y);
        double x2 = Math.min(this.x + this.width, other.x + other.width);
        double y2 = Math.min(this.y + this.height, other.y + other.height);
        double width = x2 - x1;
        double height = y2 - y1;
        if (width <= 0 || height <= 0) {
            return null;
        }
        return new RectangleShape(x1, y1, width, height);
    }

    public RectangleShape[] subtract(RectangleShape other) {
        // contained difference
        // if other is contained in this, we get 4 rectangles
        // if other is in corner, we get 2
        // if other is bottom or top, we get 1

        if (this.x <= other.x && this.y <= other.y && this.x + this.width >= other.x + other.width && this.y + this.height >= other.y + other.height) {
            ArrayList<RectangleShape> res = new ArrayList<>();
            if (this.x < other.x) {
                res.add(new RectangleShape(this.x, this.y, other.x - this.x, this.height));
            }
            if (this.y < other.y) {
                res.add(new RectangleShape(this.x, this.y, this.width, other.y - this.y));
            }
            if (this.x + this.width > other.x + other.width) {
                res.add(new RectangleShape(other.x + other.width, this.y, this.x + this.width - other.x - other.width, this.height));
            }
            if (this.y + this.height > other.y + other.height) {
                res.add(new RectangleShape(this.x, other.y + other.height, this.width, this.y + this.height - other.y - other.height));
            }
            return res.toArray(new RectangleShape[0]);
        }
        return new RectangleShape[]{this};
    }


    public int[] getCoordinates() {
        return new int[]{(int) x, (int) y, (int) width, (int) height};
    }
}


