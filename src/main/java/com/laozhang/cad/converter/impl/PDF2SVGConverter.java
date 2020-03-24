package com.laozhang.cad.converter.impl;

import com.aspose.pdf.Document;
import com.aspose.pdf.Page;
import com.aspose.pdf.SvgSaveOptions;
import com.laozhang.cad.converter.AbstractSVGConverter;
import com.laozhang.cad.svg.content.AbstractNormalSVGMaker;
import com.laozhang.cad.svg.content.AbstractTileSVGMaker;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.*;
import java.util.List;


public class PDF2SVGConverter extends AbstractSVGConverter {
    private int sourceWidth;
    private int sourceHeight;

    public PDF2SVGConverter(String sourcePath, String targetPath) {
        super(sourcePath,targetPath);
    }

    @Override
    public void buildSvgWithMaker(AbstractTileSVGMaker maker) {
        try {
            org.dom4j.Document doc = getSvgDocument();
            maker.tileRender(doc.getRootElement().addElement("g"),this.sourceWidth,this.sourceHeight);
            saveSvg(doc);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void buildSvgWithMaker(AbstractNormalSVGMaker maker) {
        try {
            org.dom4j.Document doc = getSvgDocument();
            maker.add(doc.getRootElement().addElement("g"));
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
        this.sourceWidth = Integer.parseInt(root.attribute("width").getValue());
        this.sourceHeight = Integer.parseInt(root.attribute("height").getValue());

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