package com.laozhang.cad.pdf.wm;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

public abstract class PDFPictWaterMark extends PictWaterMark {

    public PDFPictWaterMark(String pictPath){
        super(pictPath);
    }
    public PDFPictWaterMark(String pictPath, int pictWidth, int pictHeight){
        super(pictPath,pictWidth,pictHeight);
    }

    @Override
    public void render(String source,String target) {
        try {
            PdfReader.unethicalreading = true;
            PdfReader pdfReader = new PdfReader(source);
            byte[] newPdfBytes = getNewPdfWithMargin(pdfReader);
            coverWithWaterMark(newPdfBytes, target);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     *  构造新页尺寸
     */
    protected Rectangle constructNewPageSize(PdfReader pdfReader){
        int pictWidth = getPictWidth();
        int pictHeight = getPictHeight();

        Rectangle pageSize = pdfReader.getPageSize(1);
        return new Rectangle(pageSize.getWidth() + pictWidth * 2,pageSize.getHeight() + pictHeight * 2);
    }
    /**
     *  旧页加入新页的偏移
     */
    protected float[] offsetFromNewPage(){
        return new float[]{getPictWidth(),getPictHeight()};
    }

    protected byte[] getNewPdfWithMargin(PdfReader pdfReader) throws Exception{
        Rectangle pageGroundSize = constructNewPageSize(pdfReader);
        Document document = new Document(pageGroundSize);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, bos);
        document.open();

        float[] offset = offsetFromNewPage();
        PdfContentByte cb = writer.getDirectContent();
        int n = pdfReader.getNumberOfPages();
        for (int j = 1; j <= n; j++) {
            document.newPage();
            PdfImportedPage page = writer.getImportedPage(pdfReader, j);
            cb.addTemplate(page, offset[0], offset[1]);
        }
        document.close();
        return bos.toByteArray();
    }

    protected void coverWithWaterMark(byte[] newPdf, String targetPdf)throws Exception{
        PdfReader pdfReader = new PdfReader(newPdf);
        Rectangle pageSize = pdfReader.getPageSize(1);
        int pageWidth = (int)pageSize.getWidth();
        int pageHeight = (int)pageSize.getHeight();

        PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(targetPdf));
        int n = pdfReader.getNumberOfPages();
        for (int i = 1; i <= n; i++) {
            PdfContentByte under = pdfStamper.getOverContent(i);
            float[][] corners = getPointSet(pageWidth,pageHeight);
            for(float[] point : corners) {
                under.addImage(getImage(point[0],point[1]));
            }
        }

        pdfStamper.setFormFlattening(true);
        pdfStamper.close();
    }

    private Image getImage(float x, float y)throws Exception{
        Image image = Image.getInstance(getPictPath());
        image.scaleAbsolute(getPictWidth(), getPictHeight());
        image.setAbsolutePosition(x, y);
        return image;
    }
}
