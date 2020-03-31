package com.laozhang.cad.svg.content.impl;

import com.laozhang.cad.svg.converter.Drawing;
import com.laozhang.cad.svg.converter.Resolution;

public class PictFourCornerMidSVGMaker extends PictFourCornerSVGMaker {
    public PictFourCornerMidSVGMaker(String path, int pictWidth, int pictHeight){
        super(path,pictWidth,pictHeight);
    }

    @Override
    protected void calcCoordinate(Resolution resolution, Drawing drawing){
        double resolutionSlopeRate = 1.0 * resolution.getWidth() / resolution.getWidth();
        double drawingSlopeRate = 1.0 * drawing.getWidth() / drawing.getWidth();
        if(drawingSlopeRate <= resolutionSlopeRate){// 高占优势
            Double width = 1.0 * resolution.getHeight() / drawing.getHeight() * drawing.getWidth();
            int leftTopX = (resolution.getWidth() - width.intValue()) / 2;
            this.coordinate[0][0] = this.coordinate[2][0] = leftTopX;
            this.coordinate[1][0] = this.coordinate[3][0] = leftTopX + width.intValue();
            this.coordinate[0][1] = this.coordinate[1][1] = this.pictHeight;
            this.coordinate[2][1] = this.coordinate[3][1] = resolution.getHeight() - this.pictHeight * 2;
        }else{// 宽占优势
            Double height = 1.0 * resolution.getWidth() / drawing.getWidth() * drawing.getHeight();
            int leftTopY = (resolution.getHeight() - height.intValue()) / 2;
            this.coordinate[0][0] = this.coordinate[2][0] = this.pictWidth;
            this.coordinate[1][0] = this.coordinate[3][0] = resolution.getWidth() - this.pictWidth * 2;
            this.coordinate[0][1] = this.coordinate[1][1] = this.pictHeight;
            this.coordinate[2][1] = this.coordinate[3][1] = resolution.getHeight() - this.pictHeight * 2;
        }
    }
}
