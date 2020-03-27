package com.laozhang.cad.svg.content.impl;

import com.laozhang.cad.svg.content.AbstractNormalSVGMaker;
import org.dom4j.Element;

public abstract class AbstractPictSVGMaker extends AbstractNormalSVGMaker {
    public static final String NODE_TYPE = "image";
    public static final String NODE_HREF_KEY = "xlink:href";
    public static final String NODE_TYPE_KEY = "type";
    public static final String NODE_WIDTH_KEY = "width";
    public static final String NODE_HEIGHT_KEY = "height";

    protected String path;
    protected String type;
    protected int pictWidth;
    protected int pictHeight;

    public AbstractPictSVGMaker(String path, int pictWidth, int pictHeight){
        this.path = "file://" + path;
        this.type = "image/" + inferType(path);
        this.pictWidth = pictWidth;
        this.pictHeight = pictHeight;
    }
}
