package com.laozhang.cad.svg.converter.impl;

import com.aspose.pdf.Document;
import com.aspose.pdf.Page;
import com.aspose.pdf.SvgSaveOptions;
import com.laozhang.cad.svg.converter.AbstractSVGConverter;
import com.laozhang.cad.svg.converter.Drawing;
import com.laozhang.cad.svg.converter.Resolution;
import com.laozhang.cad.svg.content.AbstractNormalSVGMaker;
import com.laozhang.cad.svg.content.AbstractTileSVGMaker;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.*;
import java.util.List;


public class PDF2SVGConverter extends AbstractSVGConverter {
    private Resolution resolution;
    private Drawing drawing;

    public PDF2SVGConverter(String sourcePath, String targetPath) {
        this(sourcePath,targetPath,Resolution.RESOLUTION_1920_1080);
    }

    public PDF2SVGConverter(String sourcePath, String targetPath, Resolution resolution) {
        super(sourcePath,targetPath);
        this.resolution = resolution;
        // 默认图纸大小就是被设置的分辨率大小
        this.drawing = new Drawing(resolution.getWidth(),resolution.getHeight());
    }

    @Override
    public void buildSvgWithMaker(AbstractTileSVGMaker maker) {
        try {
            org.dom4j.Document doc = getSvgDocument();
            maker.tileRender(doc.getRootElement().addElement("g"),this.resolution);
            saveSvg(doc);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void buildSvgWithMaker(AbstractNormalSVGMaker maker) {
        try {
            org.dom4j.Document doc = getSvgDocument();
            maker.add(doc.getRootElement().addElement("g"),this.resolution,this.drawing);
            saveSvg(doc);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private org.dom4j.Document getSvgDocument() throws Exception{
        SAXReader reader = new SAXReader();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        getSvgOutputStream(bos);
        org.dom4j.Document doc = reader.read(new ByteArrayInputStream(bos.toByteArray()));
        Element root = doc.getRootElement();

        // 获取图纸大小
        int svgWidth = Integer.parseInt(root.attribute("width").getValue());
        int svgHeight = Integer.parseInt(root.attribute("height").getValue());
        this.drawing.setWidth(svgWidth);
        this.drawing.setHeight(svgHeight);

        Element firstLevelGElement = root.element("g");
        Element secondLevelGElement = firstLevelGElement.element("g");
        List text = secondLevelGElement.elements("text");
        Element temp;
        String textContent;
        for(Object child : text){
            temp = (Element)child;
            textContent = temp.getText();
            if(textContent.contains("Evaluation only") || textContent.contains("Aspose")){
                secondLevelGElement.remove(temp);
            }
        }
        return doc;
    }

    private void getSvgOutputStream(OutputStream outputStream) throws Exception{
        Document doc = new Document(this.sourcePath);
        Page item = doc.getPages().get_Item(1);
//        item.setPageSize(this.resolution.getWidth(),this.resolution.getHeight());
//        item.getPageInfo().setMargin(new MarginInfo(this.resolution.getWidth()/2,this.resolution.getWidth()/2,this.resolution.getHeight()/2,this.resolution.getHeight()/2));
        doc = new Document();
        doc.getPages().add(item);
        SvgSaveOptions saveOptions = new SvgSaveOptions();

        saveOptions.ScaleToPixels = true;
        saveOptions.TryMergeAdjacentSameBackgroundImages = true;
        saveOptions.TreatTargetFileNameAsDirectory = false;
        saveOptions.CompressOutputToZipArchive = false;
        saveOptions.setExtractOcrSublayerOnly(true);

        doc.save(outputStream, saveOptions);
    }
}