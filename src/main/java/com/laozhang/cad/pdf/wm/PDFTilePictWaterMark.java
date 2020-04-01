package com.laozhang.cad.pdf.wm;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;

import java.util.LinkedList;
import java.util.List;

public class PDFTilePictWaterMark extends PDFPictWaterMark {
    private float slopOffset;

    public PDFTilePictWaterMark(String pictPath,float slopOffset){
        super(pictPath);
        this.slopOffset = slopOffset;
    }

    public PDFTilePictWaterMark(String pictPath, int pictWidth, int pictHeight,float slopOffset){
        super(pictPath,pictWidth,pictHeight);
        this.slopOffset = slopOffset;
    }

    @Override
    public float[][] getPointSet(float pageWidth,float pageHeight){
        int pictWidth = getPictWidth();
        int pictHeight = getPictHeight();

        float x = 0,y = 0;
        List<float[]> result = new LinkedList<>();
        float[] temp;
        while (y < pageHeight) {
            if(x > pageWidth){
                y += pictHeight + this.slopOffset;
                x %= pageWidth;
                x = pictWidth * 2 + this.slopOffset - x;
            }

            temp = new float[]{x,y};
            result.add(temp);
            x += pictWidth * 2 + this.slopOffset;
        }
        float[][] corners = new float[result.size()][];
        return result.toArray(corners);
    }

    @Override
    protected Rectangle constructNewPageSize(PdfReader pdfReader){
        return pdfReader.getPageSize(1);
    }

    @Override
    protected float[] offsetFromNewPage(){
        return new float[]{0.0f,0.0f};
    }
}
