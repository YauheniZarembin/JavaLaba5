package com.zarembin.javalaba5.gamebasis;

import com.zarembin.javalaba5.util.Constant;

import javax.swing.*;
import java.awt.*;

public class EnemyField {

    private CellType[][] enemyTypesField;

    public EnemyField() {
        enemyTypesField = new CellType[Constant.HEIGHT_FIELD][Constant.WIDTH_FIELD];
        for (int i = 0; i < Constant.HEIGHT_FIELD; i++) {
            for (int j = 0; j < Constant.WIDTH_FIELD; j++)
                enemyTypesField[i][j] = CellType.UNKNOWN;
        }
    }


    public boolean isGoodShot(String coordinate, JTextArea infoTextArea) {
        Point point = ParserCoordinate.parseCoordinates(coordinate);
        if (enemyTypesField[point.x][point.y] != CellType.UNKNOWN) {
            infoTextArea.append("You already shot here. Choose another cell.\n");
            return false;
        }
        return true;
    }

    public void simulateMove(String coordinate, String message) {
        Point point = ParserCoordinate.parseCoordinates(coordinate);
        if (message.equals(Constant.MISSED)) {
            enemyTypesField[point.x][point.y] = CellType.PAST;
        }
        else {
            enemyTypesField[point.x][point.y] = CellType.INJURED;
        }
    }

    public CellType[][] getEnemyTypesField() {
        return enemyTypesField;
    }
}
