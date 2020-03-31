package com.laozhang.cad.app;

import com.laozhang.cad.svg.converter.Resolution;
import com.laozhang.cad.svg.converter.impl.PDF2SVGConverter;
import com.laozhang.cad.svg.content.impl.PictFourCornerSVGMaker;

public class Pdf2Svg {

    public static void main(String[] args) throws Exception {
        String sourcePath = "D:\\IDEA\\cad\\17.pdf";
        String targetPath = "D:\\IDEA\\cad\\17.pdf.svg";
        String pictPath = "D:\\IDEA\\cad\\202003230930.jpg";

        PDF2SVGConverter converter = new PDF2SVGConverter(sourcePath,targetPath, Resolution.RESOLUTION_1920_1080);

//        TextTileSVGMaker maker = new TextTileSVGMaker("10000000 11101100",10,10);
//        maker.horizontalInterval(50);
//        maker.verticalInterval(10);
//        maker.slopeOffset(100);
        //PictTileSVGMaker maker = new PictTileSVGMaker(pictPath, 400, 300);
        //PictAddSVGMaker maker = new PictAddSVGMaker(pictPath, 50, 50, 40, 20);
        PictFourCornerSVGMaker maker = new PictFourCornerSVGMaker(pictPath,60, 30);
        converter.buildSvgWithMaker(maker);
    }
}
