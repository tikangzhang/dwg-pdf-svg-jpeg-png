package com.laozhang.cad.svg.content.impl;

import com.laozhang.cad.converter.Drawing;
import com.laozhang.cad.converter.Resolution;
import org.dom4j.Element;

public class PictFourCornerSVGMaker extends AbstractPictSVGMaker {
    protected int[][] coordinate;

    public PictFourCornerSVGMaker(String path, int pictWidth, int pictHeight){
        super(path,pictWidth,pictHeight);
        this.coordinate = new int[4][2];
    }
    @Override
    public void add(Element parent, Resolution resolution, Drawing drawing) {
        calcCoordinate(resolution, drawing);
        for (int i = 0, len = this.coordinate.length; i < len; i++) {
            Element image = parent.addElement(NODE_TYPE);
            image.addAttribute(NODE_HREF_KEY, this.path);
            image.addAttribute(NODE_TYPE_KEY, this.type);
            image.addAttribute(NODE_X, this.coordinate[i][0] + "");
            image.addAttribute(NODE_Y, this.coordinate[i][1] + "");
            image.addAttribute(NODE_WIDTH_KEY, this.pictWidth + "");
            image.addAttribute(NODE_HEIGHT_KEY, this.pictHeight + "");
        }
    }
    protected void calcCoordinate(Resolution resolution, Drawing drawing){
        this.coordinate[0][0] = this.coordinate[2][0]= this.pictWidth;
        this.coordinate[1][0] = this.coordinate[3][0]= drawing.getWidth()  - this.pictWidth * 2;

        this.coordinate[0][1] = this.coordinate[1][1] = this.pictHeight;
        this.coordinate[2][1] = this.coordinate[3][1] = drawing.getHeight() - this.pictHeight;
    }
}
