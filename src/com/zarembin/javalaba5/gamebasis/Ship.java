package com.zarembin.javalaba5.gamebasis;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Ship {

    private ArrayList<Cell> cells;

    public Ship(List<Point> shipCells) {
        cells = new ArrayList<>();
        for (Point point : shipCells) {
            cells.add(new Cell(point, CellType.SHIP));
        }
    }

    public boolean contains(Point p) {
        for (Cell cell : cells) {
            if (cell.getPos().equals(p))
                return true;
        }
        return false;
    }

    public boolean isAlive() {
        for (Cell cell : cells) {
            if (cell.getType() == CellType.SHIP)
                return true;
        }
        return false;
    }

    public void shootAt(Point p) {
        for (Cell cell : cells) {
            if (cell.getPos().equals(p))
                cell.setType(CellType.INJURED);
        }
    }
}
