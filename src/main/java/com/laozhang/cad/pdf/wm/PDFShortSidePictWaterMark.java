package com.laozhang.cad.pdf.wm;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;

public class PDFShortSidePictWaterMark extends PDFPictWaterMark {
    public PDFShortSidePictWaterMark(String pictPath){
        super(pictPath);
    }

    public PDFShortSidePictWaterMark(String pictPath, int pictWidth, int pictHeight){
        super(pictPath,pictWidth,pictHeight);
    }

    @Override
    public float[][] getPointSet(float pageWidth,float pageHeight){
        float[][] corners = new float[2][2];
        if(pageWidth >= pageHeight) {
            corners[0][0] = corners[1][0] = pageWidth - getPictWidth();
            corners[0][1] = 0;
            corners[1][1] = pageHeight - getPictHeight();
        }else{
            corners[0][1] = corners[1][1] = pageHeight - getPictWidth();
            corners[0][0] = 0;
            corners[1][0] = pageWidth - getPictWidth();
        }
        return corners;
    }

    @Override
    protected Rectangle constructNewPageSize(PdfReader pdfReader){
        Rectangle pageSize = pdfReader.getPageSize(1);
        float pageWidth = pageSize.getWidth();
        float pageHeight = pageSize.getHeight();
        if(pageWidth >= pageHeight) {
            return new Rectangle(pageSize.getWidth() + getPictWidth(), pageSize.getHeight());
        }else{
            return new Rectangle(pageSize.getWidth(), pageSize.getHeight() + getPictHeight());
        }
    }

    @Override
    protected float[] offsetFromNewPage(){
        return new float[]{0.0f,0.0f};
    }
}
