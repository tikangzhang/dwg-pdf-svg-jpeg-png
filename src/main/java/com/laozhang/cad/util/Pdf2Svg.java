package com.laozhang.cad.util;

import com.laozhang.cad.converter.impl.PDF2SVGConverter;
import com.laozhang.cad.svg.content.impl.PictureTileSVGMaker;

public class Pdf2Svg {

    public static void main(String[] args) throws Exception {
        String sourcePath = "D:\\IDEA\\cad\\17.pdf";
        String targetPath = "D:\\IDEA\\cad\\17.pdf.svg";
        String pictPath = "D:\\IDEA\\cad\\202003230930.jpg";

        PDF2SVGConverter converter = new PDF2SVGConverter(sourcePath,targetPath);

//        TextTileSVGMaker maker = new TextTileSVGMaker("10000000 11101100",10,10);
//        maker.horizontalInterval(50);
//        maker.verticalInterval(10);
//        maker.slopeOffset(100);
        PictureTileSVGMaker maker = new PictureTileSVGMaker(pictPath, 400, 300);
        //PictureAddSVGMaker maker = new PictureAddSVGMaker(pictPath, 50, 50, 40, 20);
        converter.buildSvgWithMaker(maker);
    }
}
