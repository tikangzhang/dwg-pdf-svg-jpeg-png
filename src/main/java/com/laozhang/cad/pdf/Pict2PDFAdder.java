package com.laozhang.cad.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.laozhang.cad.util.CornerUtil;
import java.io.ByteArrayOutputStream;

import java.io.FileOutputStream;

public class Pict2PDFAdder {
    private String pictPath;
    private int pictWidth;
    private int pictHeight;
    private int margin;

    public Pict2PDFAdder(String pictPath, int pictWidth, int pictHeight, int margin){
        this.pictPath = pictPath;
        this.pictWidth = pictWidth;
        this.pictHeight = pictHeight;
        this.margin = margin;
    }

    public void doing(String sourcePdf,String targetPdf)throws Exception{
        PdfReader pdfReader = new PdfReader(sourcePdf);
        doing(pdfReader,targetPdf);
    }
    public void doing(byte[] sourcePdf,String targetPdf)throws Exception{
        PdfReader pdfReader = new PdfReader(sourcePdf);
        doing(pdfReader,targetPdf);
    }

    public void doing(PdfReader pdfReader,String targetPdf)throws Exception{
        Rectangle pageSize = pdfReader.getPageSize(1);

        Rectangle pageGroundSize = new Rectangle(pageSize.getWidth() + pictWidth * 2,pageSize.getHeight() + pictHeight * 2);
        Document document = new Document(pageGroundSize,pictWidth,pictWidth,pictHeight,pictHeight);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, bos);
        document.open();

        PdfContentByte cb = writer.getDirectContent();
        int n = pdfReader.getNumberOfPages();
        for (int j = 1; j <= n; j++) {
            document.newPage();
            PdfImportedPage page = writer.getImportedPage(pdfReader, j);
            cb.addTemplate(page, pictWidth, pictHeight);
        }

        document.close();

        pdfReader = new PdfReader(bos.toByteArray());
        pageSize = pdfReader.getPageSize(1);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(targetPdf));
        PdfContentByte under = pdfStamper.getOverContent(1);

        int[][] corners = CornerUtil.getPictCorner((int)pageSize.getWidth(),(int)pageSize.getHeight(),this.pictWidth,this.pictHeight);
        for(int[] point : corners) {
            under.addImage(getImage(point[0],point[1]));
        }

        pdfStamper.setFormFlattening(true);
        pdfStamper.close();
    }

    protected Image getImage(int x, int y)throws Exception{
        Image image = Image.getInstance(pictPath);
        image.scaleAbsolute(pictWidth, pictHeight);
        image.setAbsolutePosition(x, y);
        return image;
    }
}
