package com.laozhang.cad.svg.content.impl;

import com.laozhang.cad.svg.converter.Resolution;
import com.laozhang.cad.svg.WaterMarkPoint;
import com.laozhang.cad.svg.content.AbstractTileSVGMaker;
import org.dom4j.Element;

import java.util.List;

public class PictTileSVGMaker extends AbstractTileSVGMaker {
    public static final String NODE_TYPE = "image";
    public static final String NODE_HREF_KEY = "xlink:href";
    public static final String NODE_TYPE_KEY = "type";
    public static final String NODE_WIDTH_KEY = "width";
    public static final String NODE_HEIGHT_KEY = "height";

    private String path;
    private String type;
    private String pictWidth;
    private String pictHeight;

    public PictTileSVGMaker(String path, int width, int height){
        super(width,height);
        this.path = "file://" + path;
        this.type = "image/" + inferType(path);
        this.pictWidth = width + "";
        this.pictHeight = height + "";
    }

    public PictTileSVGMaker path(String path){
        this.path = path;
        return this;
    }

    public PictTileSVGMaker type(String type){
        this.type = type;
        return this;
    }

    public PictTileSVGMaker pictWidth(String pictWidth){
        this.pictWidth = pictWidth;
        return this;
    }

    public PictTileSVGMaker pictHeight(String pictHeight){
        this.pictHeight = pictHeight;
        return this;
    }

    @Override
    public void tileRender(Element parent, Resolution resolution){
        List<WaterMarkPoint> list = getWaterMarkBackGround(resolution.getWidth(),resolution.getHeight());
        for(WaterMarkPoint point : list) {
            Element image = parent.addElement(NODE_TYPE);
            image.addAttribute(NODE_HREF_KEY, this.path);
            image.addAttribute(NODE_TYPE_KEY, this.type);
            image.addAttribute(NODE_X, point.X());
            image.addAttribute(NODE_Y, point.Y());
            image.addAttribute(NODE_WIDTH_KEY, this.pictWidth);
            image.addAttribute(NODE_HEIGHT_KEY, this.pictHeight);
        }
    }
}
