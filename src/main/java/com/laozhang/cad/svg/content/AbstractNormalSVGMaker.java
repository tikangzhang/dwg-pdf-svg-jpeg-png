package com.laozhang.cad.svg.content;

import com.laozhang.cad.svg.converter.Drawing;
import com.laozhang.cad.svg.converter.Resolution;
import org.dom4j.Element;

public abstract class AbstractNormalSVGMaker extends AbstractSVGMaker {
    public abstract void add(Element parent, Resolution resolution, Drawing drawing);
}
