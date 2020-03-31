package com.laozhang.cad.util;

public class CornerUtil {
    public static int[][] getPictCorner(int pageWidth,int pageHeight,int pictWidth,int pictHeight,int offset){
        int[][] corners = new int[4][2];
        corners[0][0] = corners[2][0] = offset;
        corners[1][0] = corners[3][0] = pageWidth - pictWidth - offset;

        corners[0][1] = corners[1][1] = pageHeight - pictHeight - offset;
        corners[2][1] = corners[3][1] = offset;
        return corners;
    }

    public static int[][] getPictCorner(int pageWidth,int pageHeight,int pictWidth,int pictHeight){
        int[][] corners = new int[4][2];
        corners[0][0] = corners[2][0] = 0;
        corners[1][0] = corners[3][0] = pageWidth - pictWidth;

        corners[0][1] = corners[1][1] = pageHeight - pictHeight;
        corners[2][1] = corners[3][1] = 0;
        return corners;
    }
}
