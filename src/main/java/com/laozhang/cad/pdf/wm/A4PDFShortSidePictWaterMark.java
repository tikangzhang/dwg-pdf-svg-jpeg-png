package com.laozhang.cad.pdf.wm;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;

public class A4PDFShortSidePictWaterMark extends PDFShortSidePictWaterMark {
    private float scale;
    float pageWidth;
    float pageHeight;

    public A4PDFShortSidePictWaterMark(String pictPath){
        super(pictPath);
    }

    public A4PDFShortSidePictWaterMark(String pictPath, int pictWidth, int pictHeight){
        super(pictPath,pictWidth,pictHeight);
    }

    @Override
    protected Rectangle constructNewPageSize(PdfReader pdfReader){
        Rectangle pageSize = pdfReader.getPageSize(1);
        float pageWidth = pageSize.getWidth();
        float pageHeight = pageSize.getHeight();
        Rectangle a4 = PageSize.A4;
        if(pageWidth >= pageHeight) {
            return new Rectangle(a4.getHeight(),a4.getWidth());
        }else{
            return a4;
        }
    }

    @Override
    protected byte[] getNewPdfWithMargin(PdfReader pdfReader) throws Exception{
        Rectangle pageA4 = constructNewPageSize(pdfReader);
        Document document = new Document(pageA4);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, bos);
        document.open();

        PdfContentByte cb = writer.getDirectContent();
        int n = pdfReader.getNumberOfPages();
        for (int j = 1; j <= n; j++) {
            document.newPage();
            PdfImportedPage page = writer.getImportedPage(pdfReader, j);
            this.pageWidth = page.getWidth();
            this.pageHeight = page.getHeight();
            float scale = getScale(pageA4);

            if(this.pageWidth >= this.pageHeight) {
                cb.addTemplate(page, scale, 0.0, 0.0, scale, 0.0, (pageA4.getHeight() - this.pageHeight * scale) / 2);
            }else{
                cb.addTemplate(page, scale, 0.0, 0.0, scale, (pageA4.getWidth() - this.pageWidth * scale) / 2, 0.0);
            }
        }
        document.close();
        return bos.toByteArray();
    }

    private float getScale(Rectangle pageGroundSize) {
        float scaleX = (pageGroundSize.getWidth() - getPictWidth()) / this.pageWidth;
        float scaleY = (pageGroundSize.getHeight() - getPictWidth()) / this.pageHeight;

        this.scale = Math.min(scaleX, scaleY);
        return this.scale;
    }
}
