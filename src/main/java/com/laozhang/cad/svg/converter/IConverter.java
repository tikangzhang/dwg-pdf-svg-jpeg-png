package com.laozhang.cad.svg.converter;

import com.laozhang.cad.svg.content.AbstractNormalSVGMaker;
import com.laozhang.cad.svg.content.AbstractTileSVGMaker;

public interface IConverter {
    void buildSvgWithMaker(AbstractTileSVGMaker maker);

    void buildSvgWithMaker(AbstractNormalSVGMaker maker);
}
