package com.zarembin.javalaba5.gamebasis;

import java.awt.*;

public class Cell {

    private Point pos;
    private CellType type;

    public Cell(Point pos, CellType type) {
        this.pos = pos;
        this.type = type;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }
}
