package com.laozhang.cad.converter.impl;

import java.io.*;
import java.util.List;

import com.aspose.cad.Image;
import com.aspose.cad.fileformats.cad.CadDrawTypeMode;
import com.aspose.cad.imageoptions.CadRasterizationOptions;
import com.aspose.cad.imageoptions.SvgOptions;
import com.aspose.cad.imageoptions.UnitType;
import com.laozhang.cad.converter.AbstractSVGConverter;
import com.laozhang.cad.svg.content.AbstractNormalSVGMaker;
import com.laozhang.cad.svg.content.AbstractTileSVGMaker;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class DWG2SVGConverter extends AbstractSVGConverter {
    private int sourceWidth;
    private int sourceHeight;

    public DWG2SVGConverter(String sourcePath, String targetPath) {
        super(sourcePath,targetPath);
    }

    @Override
    final public void buildSvgWithMaker(AbstractTileSVGMaker maker){
        try {
            Document doc = getSvgDocument();
            maker.tileRender(doc.getRootElement().addElement("g"),this.sourceWidth,this.sourceHeight);
            saveSvg(doc);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    final public void buildSvgWithMaker(AbstractNormalSVGMaker maker) {
        try {
            Document doc = getSvgDocument();
            maker.add(doc.getRootElement().addElement("g"));
            saveSvg(doc);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private Document getSvgDocument() throws Exception{
        SAXReader reader = new SAXReader();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        getSvgOutputStream(bos);
        Document doc = reader.read(new ByteArrayInputStream(bos.toByteArray()));
        Element root = doc.getRootElement();
        List elements = root.elements();
        Element element = (Element)elements.get(1);
        root.remove(element);
        return doc;
    }

    private void getSvgOutputStream(OutputStream outputStream) throws Exception{
        Image image = Image.load(this.sourcePath);
        int width = image.getWidth();
        int height = image.getHeight();
        int zoom = 1008000 / width / height;//100万像素等比倍数
        this.sourceWidth = image.getWidth() * zoom;
        this.sourceHeight = image.getHeight() * zoom;

        CadRasterizationOptions cadRasterizationOptions = new CadRasterizationOptions();
        cadRasterizationOptions.setPageWidth(this.sourceWidth);
        cadRasterizationOptions.setPageHeight(this.sourceHeight);
        cadRasterizationOptions.setDrawType(CadDrawTypeMode.UseObjectColor);
        SvgOptions options = new SvgOptions();
        options.setVectorRasterizationOptions(cadRasterizationOptions);
            //options.setColorType(SvgColorMode.Grayscale);
            //options.setTextAsShapes(true);
        image.save(outputStream,options);
    }


    private static boolean IsMetric(int initial){
        boolean isMetric = true;
        switch (initial){
            case UnitType.Inch:
            case UnitType.MicroInch:
            case UnitType.Mil:
            case UnitType.Foot:
            case UnitType.Yard:
            case UnitType.Mile:
            case UnitType.Unitless:
                isMetric = false;
        }
        return isMetric;
    }

}