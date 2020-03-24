package com.laozhang.cad.svg.content.impl;

import com.laozhang.cad.svg.WaterMarkPoint;
import com.laozhang.cad.svg.content.AbstractTileSVGMaker;
import org.dom4j.Element;

import java.util.List;

public class TextTileSVGMaker extends AbstractTileSVGMaker {
    public static final String NODE_TYPE = "text";

    public static final String NODE_STYLE_KEY = "style";
    //public static final String NODE_STYLE_VALUE_DEFAULT = "fill:#000000;font-family:Arial;";
    public static final String NODE_STYLE_VALUE_DEFAULT = "fill:#000000;";

    public static final String NODE_FONT_FAMILY_KEY = "font-family";
    public static final String NODE_FONT_FAMILY_DEFAULT_VALUE = "Arial";

    public static final String NODE_FONT_SIZE_KEY = "font-size";
    public static final String NODE_FONT_SIZE_DEFAULT_VALUE = "3.75";

    private String content;
    private String style;
    private String fontFamily;
    private String fontSize;

    public TextTileSVGMaker(String content){
        super(30,30);
        this.content = content;
        this.style = NODE_STYLE_VALUE_DEFAULT;
        this.fontFamily = NODE_FONT_FAMILY_DEFAULT_VALUE;
        this.fontSize = NODE_FONT_SIZE_DEFAULT_VALUE;
    }

    public TextTileSVGMaker(String content,int width,int height){
        super(width,height);
        this.content = content;
        this.style = NODE_STYLE_VALUE_DEFAULT;
        this.fontFamily = NODE_FONT_FAMILY_DEFAULT_VALUE;
        this.fontSize = NODE_FONT_SIZE_DEFAULT_VALUE;
    }

    public TextTileSVGMaker style(String style){
        this.style = style;
        return this;
    }

    public TextTileSVGMaker fontFamily(String fontFamily){
        this.fontFamily = fontFamily;
        return this;
    }

    public TextTileSVGMaker fontSize(String fontSize){
        this.fontSize = fontSize;
        return this;
    }

    @Override
    public void tileRender(Element parent,int width,int height){
        List<WaterMarkPoint> list = getWaterMarkBackGround(width,height);
        for(WaterMarkPoint point : list) {
            Element text = parent.addElement(NODE_TYPE);
            text.addAttribute(NODE_X, point.X());
            text.addAttribute(NODE_Y, point.Y());
            text.addAttribute(NODE_STYLE_KEY, this.style);
            text.addAttribute(NODE_FONT_FAMILY_KEY, this.fontFamily);
            text.addAttribute(NODE_FONT_SIZE_KEY, this.fontSize);
            text.setText(content);
        }
    }
}
