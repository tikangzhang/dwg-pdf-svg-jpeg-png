package com.laozhang.cad.svg;

import java.util.LinkedList;
import java.util.List;

public class WaterMarkPoint {
    private int x;
    private int y;

    public WaterMarkPoint(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public String X() {
        return x + "";
    }

    public String Y() {
        return y + "";
    }

    @Override
    public String toString() {
        return "WaterMarkPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
