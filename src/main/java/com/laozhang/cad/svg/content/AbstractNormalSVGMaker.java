package com.laozhang.cad.svg.content;

import org.dom4j.Element;

public abstract class AbstractNormalSVGMaker extends AbstractSVGMaker {
    protected AbstractNormalSVGMaker(){
    }

    public abstract void add(Element parent);
}
