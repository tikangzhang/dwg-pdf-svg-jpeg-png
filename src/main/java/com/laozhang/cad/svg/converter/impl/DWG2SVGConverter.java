package com.laozhang.cad.svg.converter.impl;

import java.io.*;
import java.util.List;

import com.aspose.cad.CodePages;
import com.aspose.cad.Image;
import com.aspose.cad.LoadOptions;
import com.aspose.cad.fileformats.cad.CadDrawTypeMode;
import com.aspose.cad.imageoptions.CadRasterizationOptions;
import com.aspose.cad.imageoptions.SvgOptions;
import com.aspose.cad.imageoptions.UnitType;
import com.laozhang.cad.svg.converter.AbstractSVGConverter;
import com.laozhang.cad.svg.converter.Drawing;
import com.laozhang.cad.svg.converter.Resolution;
import com.laozhang.cad.svg.content.AbstractNormalSVGMaker;
import com.laozhang.cad.svg.content.AbstractTileSVGMaker;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class DWG2SVGConverter extends AbstractSVGConverter {
    private Resolution resolution;
    private Drawing drawing;

    public DWG2SVGConverter(String sourcePath, String targetPath) {
        this(sourcePath,targetPath,Resolution.RESOLUTION_1920_1080);
    }

    public DWG2SVGConverter(String sourcePath, String targetPath, Resolution resolution) {
        super(sourcePath,targetPath);
        this.resolution = resolution;
        // 默认图纸大小就是被设置的分辨率大小
        this.drawing = new Drawing(resolution.getWidth(),resolution.getHeight());
    }

    @Override
    final public void buildSvgWithMaker(AbstractTileSVGMaker maker){
        try {
            Document doc = getSvgDocument();
            maker.tileRender(doc.getRootElement().addElement("g"),this.resolution);
            saveSvg(doc);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    final public void buildSvgWithMaker(AbstractNormalSVGMaker maker) {
        try {
            Document doc = getSvgDocument();
            maker.add(doc.getRootElement().addElement("g"),this.resolution,this.drawing);
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
        LoadOptions loadOptions = new LoadOptions();
        loadOptions.setSpecifiedEncoding(CodePages.Utf8);

        Image image = Image.load(this.sourcePath,loadOptions);
        // 获取图纸大小
        this.drawing.setWidth(image.getWidth());
        this.drawing.setHeight(image.getHeight());

        CadRasterizationOptions cadRasterizationOptions = new CadRasterizationOptions();
        cadRasterizationOptions.setPageWidth(this.resolution.getWidth());
        cadRasterizationOptions.setPageHeight(this.resolution.getHeight());
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