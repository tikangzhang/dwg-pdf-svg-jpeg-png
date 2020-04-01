package com.laozhang.cad.pdf.wm;

public class PDFFourCornerPictWaterMark extends PDFPictWaterMark {
    public PDFFourCornerPictWaterMark(String pictPath){
        super(pictPath);
    }

    public PDFFourCornerPictWaterMark(String pictPath, int pictWidth, int pictHeight){
        super(pictPath,pictWidth,pictHeight);
    }

    @Override
    public float[][] getPointSet(float pageWidth,float pageHeight){
        float[][] corners = new float[4][2];
        corners[0][0] = corners[2][0] = 0;
        corners[1][0] = corners[3][0] = pageWidth - getPictWidth();

        corners[0][1] = corners[1][1] = pageHeight - getPictHeight();
        corners[2][1] = corners[3][1] = 0;
        return corners;
    }
}
