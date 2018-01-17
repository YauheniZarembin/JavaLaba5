package com.zarembin.javalaba5.gamebasis;

import com.zarembin.javalaba5.util.Constant;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserField {

    private CellType[][] userTypesField;
    private List<Ship> ships;

    public UserField(String[][] shipsLocation){
        userTypesField = new CellType[Constant.HEIGHT_FIELD][Constant.WIDTH_FIELD];
        for (int i = 0; i < Constant.HEIGHT_FIELD; i++) {
            for (int j = 0; j < Constant.WIDTH_FIELD; j++) {
                if (CellType.SEA.toString().equals(shipsLocation[i][j])) {
                    userTypesField[i][j] = CellType.SEA;
                } else if (CellType.SHIP.toString().equals(shipsLocation[i][j])) {
                    userTypesField[i][j] = CellType.SHIP;
                } else {
                    System.err.println("Incorrect cell type: " + i + " " + j + " " + shipsLocation[i][j]);
                }
            }
        }

        CellType[][] tmp = new CellType[Constant.HEIGHT_FIELD][Constant.WIDTH_FIELD];
        for (int i = 0; i < Constant.HEIGHT_FIELD; i++)
            for (int j = 0; j < Constant.WIDTH_FIELD; j++)
                tmp[i][j] = userTypesField[i][j];

        ships = parseShips(tmp);
    }


    private static List<Ship> parseShips(CellType[][] map) {
        final int[][] STEPS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        List<Ship> ships = new ArrayList<>();
        for (int x = 0; x < Constant.HEIGHT_FIELD; x++)
            for (int y = 0; y < Constant.WIDTH_FIELD; y++) {
                if (map[x][y] == CellType.SHIP) {
                    LinkedList<Point> queue = new LinkedList<>();
                    queue.add(new Point(x, y));
                    map[x][y] = CellType.SEA;
                    List<Point> shipCells = new ArrayList<>();
                    while (!queue.isEmpty()) {
                        Point p = queue.pollFirst();
                        shipCells.add(p);
                        for (int[] step: STEPS) {
                            int cx = p.x + step[0], cy = p.y + step[1];
                            if (cx > -1 && cx < Constant.WIDTH_FIELD && cy > -1 && cy < Constant.HEIGHT_FIELD
                                    && map[cx][cy] == CellType.SHIP) {
                                map[cx][cy] = CellType.SEA;
                                queue.add(new Point(cx, cy));
                            }
                        }
                    }
                    ships.add(new Ship(shipCells));
                }
            }
        return ships;
    }


    private boolean hasAliveShip() {
        for (Ship ship: ships) {
            if (ship.isAlive())
                return true;
        }
        return false;
    }

    private boolean isShipAlive(Point p) {
        for (Ship ship: ships) {
            if (ship.contains(p))
                return ship.isAlive();
        }
        return false;
    }

    public String executeMove(String coordinate) {
        System.out.println("execute Move");
        Point point = ParserCoordinate.parseCoordinates(coordinate);
        int x = point.x;
        int y = point.y;
        String result;
        if (userTypesField[x][y] == CellType.SEA) {
            userTypesField[x][y] = CellType.PAST;
            result = Constant.MISSED;
        }
        else {
            userTypesField[x][y] = CellType.INJURED;
            shootAt(point);
            if (isShipAlive(point))
                result = Constant.HIT;
            else if (hasAliveShip())
                result = Constant.SUNK;
            else result = Constant.YOU_WIN;
        }
        return result;
    }

    private void shootAt(Point p) {
        for (Ship ship: ships) {
            ship.shootAt(p);
        }
    }

    public CellType[][] getUserTypesField() {
        return userTypesField;
    }

}
