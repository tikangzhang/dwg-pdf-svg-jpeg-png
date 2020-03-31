package com.laozhang.cad.svg.converter;

public enum Resolution {
    RESOLUTION_1920_1080(1920,1080),
    RESOLUTION_1680_1050(1680,1050),
    RESOLUTION_1600_900(1600,900),
    RESOLUTION_1440_900(1440,900),
    RESOLUTION_1280_1024(1280,1024),
    RESOLUTION_1024_768(1024,768),
    RESOLUTION_800_600(800,600);

    private int width;
    private int height;

    Resolution(int width,int height){
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
