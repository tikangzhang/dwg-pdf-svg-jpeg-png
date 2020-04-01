package com.laozhang.cad.pdf.wm;

import com.laozhang.cad.pdf.WaterMark;

public abstract class PictWaterMark implements WaterMark {
    private static final int DEFAULT_PICT_WIDTH = 60;
    private static final int DEFAULT_PICT_HEIGHT = 60;
    private String pictPath;
    private int pictWidth;
    private int pictHeight;

    public PictWaterMark(String pictPath){
        this(pictPath,DEFAULT_PICT_WIDTH,DEFAULT_PICT_HEIGHT);
    }
    public PictWaterMark(String pictPath,int pictWidth,int pictHeight){
        this.pictPath = pictPath;
        this.pictHeight = pictHeight;
        this.pictWidth = pictWidth;
    }
    public int getPictWidth(){
        return this.pictWidth;
    }
    public int getPictHeight(){
        return this.pictHeight;
    }
    public String getPictPath(){
        return this.pictPath;
    }
}
