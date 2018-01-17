package com.zarembin.javalaba5.gamebasis;

import java.awt.*;

public class ParserCoordinate {
    public static Point parseCoordinates(String coordinate) {
        int x = 0;
        int y = 0;
        try {
            x = Integer.parseInt(coordinate.substring(0, 1));
            y = Integer.parseInt(coordinate.substring(2, 3));
            return new Point(x, y);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return new Point(x, y);
    }
}
