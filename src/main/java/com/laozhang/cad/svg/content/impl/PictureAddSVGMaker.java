package com.laozhang.cad.svg.content.impl;

import com.laozhang.cad.svg.content.AbstractNormalSVGMaker;
import org.dom4j.Element;

public class PictureAddSVGMaker extends AbstractNormalSVGMaker {
    public static final String NODE_TYPE = "image";
    public static final String NODE_HREF_KEY = "xlink:href";
    public static final String NODE_TYPE_KEY = "type";
    public static final String NODE_WIDTH_KEY = "width";
    public static final String NODE_HEIGHT_KEY = "height";

    private String path;
    private String type;
    private String pictWidth;
    private String pictHeight;
    private String x;
    private String y;

    public PictureAddSVGMaker(String path,int x,int y, int width, int height){
        this.path = "file://" + path;
        this.type = "image/" + inferType(path);
        this.x = x + "";
        this.y = y + "";
        this.pictWidth = width + "";
        this.pictHeight = height + "";
    }

    public PictureAddSVGMaker path(String path){
        this.path = path;
        return this;
    }

    public PictureAddSVGMaker type(String type){
        this.type = type;
        return this;
    }

    public PictureAddSVGMaker pictWidth(String pictWidth){
        this.pictWidth = pictWidth;
        return this;
    }

    public PictureAddSVGMaker pictHeight(String pictHeight){
        this.pictHeight = pictHeight;
        return this;
    }

    @Override
    public void add(Element parent) {
        Element image = parent.addElement(NODE_TYPE);
        image.addAttribute(NODE_HREF_KEY, this.path);
        image.addAttribute(NODE_TYPE_KEY, this.type);
        image.addAttribute(NODE_X, this.x);
        image.addAttribute(NODE_Y, this.y );
        image.addAttribute(NODE_WIDTH_KEY, this.pictWidth);
        image.addAttribute(NODE_HEIGHT_KEY, this.pictHeight);
    }
}
