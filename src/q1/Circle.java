package q1;

import java.awt.*;

public class Circle {
    int radius;
    int c_width;
    int c_height;
    int mid_x_coordinate;
    int mid_y_coordinate;
    Color currentColor;

    Circle(int r, int w, int h, int x, int y, Color color) {
        radius = r;
        c_width = w;
        c_height = h;
        mid_x_coordinate = x;
        mid_y_coordinate = y;
        currentColor = color;
    }
}
