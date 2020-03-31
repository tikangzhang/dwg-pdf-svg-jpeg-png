package com.laozhang.cad.app;

import com.aspose.cad.*;
import com.aspose.cad.fileformats.cad.CadDrawTypeMode;
import com.aspose.cad.imageoptions.CadRasterizationOptions;
import com.aspose.cad.imageoptions.PdfOptions;
import com.aspose.cad.imageoptions.UnitType;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.laozhang.cad.pdf.Pict2PDFAdder;
import com.laozhang.cad.util.CornerUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;

public class BatchDwg2PdfAddPict {
    private final static String pictPath = "D:\\IDEA\\cad\\202003230930.jpg";
    private final static int pictWidth = 120;
    private final static int pictHeight = 60;
    private final static int margin = 20;
    public static void main(String[] args) throws Exception {
        String sourcePath = "C:\\Users\\admin\\Desktop\\now\\drawing\\total_java";
        String targetPath = "C:\\Users\\admin\\Desktop\\now\\drawing\\total_java_convert";

        File sourceDir = new File(sourcePath);
        File[] files = sourceDir.listFiles(new FileFilter() {
            String fileName;
            String postfix;
            @Override
            public boolean accept(File pathname) {
                fileName = pathname.getName();
                postfix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                if(".dwg".equals(postfix)){
                    return true;
                }
                return false;
            }
        });
        String fileName,fileNamePost;
        for (File file : files) {
            fileNamePost = file.getName();
            fileName = fileNamePost.substring(0,fileNamePost.lastIndexOf("."));
            if (fileNamePost.endsWith("dwg") || fileNamePost.endsWith("DWG")) {
                new Pict2PDFAdder(pictPath,pictWidth,pictHeight,margin).doing(getDwg2PdfByte(sourcePath + "\\" + fileNamePost) ,targetPath + "\\" +  fileName + ".pdf");
            }
        }
    }

    private static byte[] getDwg2PdfByte(String sourcePath){
        LoadOptions loadOptions = new LoadOptions();
        loadOptions.setSpecifiedEncoding(CodePages.SimpChineseOtherGb2312);

        com.aspose.cad.Image image = com.aspose.cad.Image.load(sourcePath,loadOptions);

        // Create an instance of PdfOptions
        PdfOptions pdfOptions = new PdfOptions();

        CadRasterizationOptions cadRasterizationOptions = new CadRasterizationOptions();
        cadRasterizationOptions.setPageHeight(1080);
        cadRasterizationOptions.setPageWidth(1920);
        cadRasterizationOptions.setDrawType(CadDrawTypeMode.UseObjectColor);
        cadRasterizationOptions.setUnitType(UnitType.Unitless);

        cadRasterizationOptions.getGraphicsOptions().setSmoothingMode(SmoothingMode.HighQuality);
        cadRasterizationOptions.getGraphicsOptions().setTextRenderingHint(TextRenderingHint.AntiAliasGridFit);
        cadRasterizationOptions.getGraphicsOptions().setInterpolationMode(InterpolationMode.HighQualityBicubic);
        pdfOptions.setVectorRasterizationOptions(cadRasterizationOptions);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        image.save(bos, pdfOptions);
        return bos.toByteArray();
    }
}
