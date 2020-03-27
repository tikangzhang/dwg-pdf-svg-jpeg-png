package com.laozhang.cad.util;

import com.laozhang.cad.converter.Resolution;
import com.laozhang.cad.converter.impl.DWG2SVGConverter;
import com.laozhang.cad.converter.impl.PDF2SVGConverter;
import com.laozhang.cad.svg.content.impl.PictFourCornerMidSVGMaker;

import java.io.File;
import java.io.FileFilter;

public class BatchDwgPdf2Svg {

    public static void main(String[] args){
        String sourcePath = "C:\\Users\\admin\\Desktop\\now\\drawing\\total_java";
        String targetPath = "C:\\Users\\admin\\Desktop\\now\\drawing\\total_java_convert";
        String pictPath = "D:\\IDEA\\cad\\202003230930.jpg";

        File sourceDir = new File(sourcePath);
        File[] files = sourceDir.listFiles(new FileFilter() {
            String fileName;
            String postfix;
            @Override
            public boolean accept(File pathname) {
                fileName = pathname.getName();
                postfix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                if(".dwg".equals(postfix)||".pdf".equals(postfix)){
                    return true;
                }
                return false;
            }
        });

        PictFourCornerMidSVGMaker maker = new PictFourCornerMidSVGMaker(pictPath,60, 30);
        String fileName;
        for (File file : files) {
            fileName = file.getName();
            if(fileName.endsWith("pdf") || fileName.endsWith("PDF")){
                new PDF2SVGConverter(sourcePath + "\\" + fileName ,targetPath + "\\" +  fileName, Resolution.RESOLUTION_1920_1080).buildSvgWithMaker(maker);
            }else{
                new DWG2SVGConverter(sourcePath + "\\" +  fileName ,targetPath + "\\" +  fileName, Resolution.RESOLUTION_1920_1080).buildSvgWithMaker(maker);
            }
            System.out.println(fileName + " -- done!");
        }
    }
}
