package com.laozhang.cad.svg.content.impl;

import com.laozhang.cad.svg.content.AbstractNormalSVGMaker;
import org.dom4j.Element;

public class TextAddSVGMaker extends AbstractNormalSVGMaker {
    public static final String NODE_TYPE = "text";

    public static final String NODE_STYLE_KEY = "style";
    public static final String NODE_STYLE_VALUE_DEFAULT = "fill:#000000;";

    public static final String NODE_FONT_FAMILY_KEY = "font-family";
    public static final String NODE_FONT_FAMILY_DEFAULT_VALUE = "Arial";

    public static final String NODE_FONT_SIZE_KEY = "font-size";
    public static final String NODE_FONT_SIZE_DEFAULT_VALUE = "3.75";

    private String content;
    private String style;
    private String fontFamily;
    private String fontSize;
    private String x;
    private String y;

    public TextAddSVGMaker(String content,int x,int y){
        this.content = content;
        this.x = x + "";
        this.y = y + "";
        this.style = NODE_STYLE_VALUE_DEFAULT;
        this.fontFamily = NODE_FONT_FAMILY_DEFAULT_VALUE;
        this.fontSize = NODE_FONT_SIZE_DEFAULT_VALUE;
    }

    public TextAddSVGMaker style(String style){
        this.style = style;
        return this;
    }

    public TextAddSVGMaker fontFamily(String fontFamily){
        this.fontFamily = fontFamily;
        return this;
    }

    public TextAddSVGMaker fontSize(String fontSize){
        this.fontSize = fontSize;
        return this;
    }

    @Override
    public void add(Element parent){
        Element text = parent.addElement(NODE_TYPE);
        text.addAttribute(NODE_X, this.x);
        text.addAttribute(NODE_Y, this.y);
        text.addAttribute(NODE_STYLE_KEY, this.style);
        text.addAttribute(NODE_FONT_FAMILY_KEY, this.fontFamily);
        text.addAttribute(NODE_FONT_SIZE_KEY, this.fontSize);
        text.setText(content);
    }
}
