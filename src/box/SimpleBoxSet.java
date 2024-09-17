package box;

import java.awt.*;
import java.util.ArrayList;

public class SimpleBoxSet implements BoxSet{

    ArrayList<RectangleShape> boxes = new ArrayList<>();

    public SimpleBoxSet() {
    }

    @Override
    public void add(int x, int y, int width, int height) throws IllegalArgumentException {
        // Consider a set of rectangles. We wish to add a rectangle operation is an extension of subtraction:
        //to this set. As it turns out, this
        //1. Compute as .
        //2. Add to and return the result.

        RectangleShape newBox = new RectangleShape(x, y, width, height);
        ArrayList<RectangleShape> res = new ArrayList<>();
        for (RectangleShape box : this.boxes) {
            RectangleShape intersection = box.intersection(newBox);
            if (intersection != null) {
                RectangleShape[] sub = box.subtract(intersection);
                for (RectangleShape s : sub) {
                    res.add(s);
                }
            } else {
                res.add(box);
            }
        }
        res.add(newBox);
        this.boxes = res;

    }

    @Override
    public void subtract(int x, int y, int width, int height) throws IllegalArgumentException {
        ArrayList<RectangleShape> res = new ArrayList<>();
        for (RectangleShape box : this.boxes) {
            RectangleShape intersection = box.intersection(new RectangleShape(x, y, width, height));
            if (intersection != null) {
                RectangleShape[] sub = box.subtract(intersection);
                for (RectangleShape s : sub) {
                    res.add(s);
                }
            } else {
                res.add(box);
            }
        }
        this.boxes = res;
    }

    @Override
    public int[][] getBoxes() {
        // box.getCoordinates to get [x,y,width,height]
        int[][] res = new int[boxes.size()][4];
        for (int i = 0; i < boxes.size(); i++) {
            RectangleShape box = boxes.get(i);
            res[i] = box.getCoordinates();
        }
        return res;
    }


    @Override
    public int size() {
        return boxes.size();
    }
}
