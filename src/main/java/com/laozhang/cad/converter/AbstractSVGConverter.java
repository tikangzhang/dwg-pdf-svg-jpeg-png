package com.laozhang.cad.converter;

import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;

public abstract class AbstractSVGConverter implements IConverter{
    //源文件全路径
    protected String sourcePath;
    protected String targetPath;

    protected AbstractSVGConverter(String sourcePath, String targetPath){
        this.sourcePath = sourcePath;
        this.targetPath = targetPath;
    }
    protected void saveSvg(org.dom4j.Document doc) throws Exception{
        File f = new File(this.targetPath);
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        XMLWriter writer = new XMLWriter(new FileOutputStream(f),format);
        //设置是否转义。默认true，代表转义
        writer.setEscapeText(false);
        writer.write(doc);
        writer.close();
    }
}
