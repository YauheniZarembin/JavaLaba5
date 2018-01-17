package com.zarembin.javalaba5.gamebasis;

public enum CellType {

    SHIP, INJURED, SEA, UNKNOWN, PAST;

    @Override
    public String toString() {
        switch (this) {
            case SHIP:
                return "[]";
            case INJURED:
                return "[x]";
            case SEA:
                return "море";
            case UNKNOWN:
                return "?";
            case PAST:
                return "o";
        }
        return null;
    }
}
