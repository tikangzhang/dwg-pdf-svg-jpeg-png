package com.laozhang.cad.svg.content;

import com.laozhang.cad.svg.WaterMarkPoint;
import org.dom4j.Element;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractTileSVGMaker extends AbstractSVGMaker {
    private int contentWidth;
    private int contentHeight;
    private int horizontalInterval;
    private int verticalInterval;
    private int slopeOffset;

    protected AbstractTileSVGMaker(int contentWidth, int contentHeight){
        this.contentWidth = contentWidth;
        this.contentHeight = contentHeight;
        this.horizontalInterval = contentWidth;
        this.verticalInterval = contentHeight;
        this.slopeOffset = contentWidth;
    }

    public AbstractTileSVGMaker horizontalInterval(int interval){
        this.horizontalInterval = interval;
        return this;
    }

    public AbstractTileSVGMaker verticalInterval(int interval){
        this.verticalInterval = interval;
        return this;
    }

    public AbstractTileSVGMaker slopeOffset(int slopeOffset){
        this.slopeOffset = slopeOffset;
        return this;
    }

    public List<WaterMarkPoint> getWaterMarkBackGround(int targetX, int targetY){
        List<WaterMarkPoint> list = new LinkedList<WaterMarkPoint>();
        int tempX = 0,tempY = 5;
        while (tempY <= targetY + this.horizontalInterval) {
            list.add(new WaterMarkPoint(tempX,tempY));

            tempX = tempX + this.horizontalInterval + this.contentWidth;
            if(tempX >= targetX) {
                tempX = (tempX + slopeOffset) % targetX - targetX;
                tempY += this.contentHeight + this.verticalInterval;
            }
        }
        return list;
    }

    public abstract void tileRender(Element parent, int width, int height);
}
