package com.laozhang.cad.svg.content.impl;

import com.laozhang.cad.svg.converter.Drawing;
import com.laozhang.cad.svg.converter.Resolution;
import org.dom4j.Element;

public class PictAddSVGMaker extends AbstractPictSVGMaker {
    protected String x;
    protected String y;

    public PictAddSVGMaker(String path, int pictWidth, int pictHeight, int x, int y){
        super(path,pictWidth,pictHeight);
        this.x = x + "";
        this.y = y + "";
    }

    @Override
    public void add(Element parent, Resolution resolution, Drawing drawing) {
        Element image = parent.addElement(NODE_TYPE);
        image.addAttribute(NODE_HREF_KEY, this.path);
        image.addAttribute(NODE_TYPE_KEY, this.type);
        image.addAttribute(NODE_X, this.x);
        image.addAttribute(NODE_Y, this.y );
        image.addAttribute(NODE_WIDTH_KEY, this.pictWidth + "");
        image.addAttribute(NODE_HEIGHT_KEY, this.pictHeight + "");
    }
}
