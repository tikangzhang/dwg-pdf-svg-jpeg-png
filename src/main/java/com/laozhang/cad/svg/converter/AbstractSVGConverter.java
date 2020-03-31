package com.laozhang.cad.svg.converter;

import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;

public abstract class AbstractSVGConverter implements IConverter{
    public static final String RESOLUTION_1920_1080 = "1920*1080";
    public static final String RESOLUTION_1680_1050 = "1680*1050";
    public static final String RESOLUTION_1600_900 = "1600*900";
    public static final String RESOLUTION_1440_900 = "1440*900";
    public static final String RESOLUTION_1280_1024 = "1280*1024";
    public static final String RESOLUTION_1024_768 = "1024*768";
    public static final String RESOLUTION_800_600 = "800*600";
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
