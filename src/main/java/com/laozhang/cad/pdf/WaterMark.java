package com.laozhang.cad.pdf;

public interface WaterMark {
    /**
     * 获取渲染的点集
     * @param pageWidth 页宽
     * @param pageHeight 页高
     * @return 点集数组
     */
    float[][] getPointSet(float pageWidth, float pageHeight);

    /**
     * 渲染
     * @param source 源文件
     * @param target 输出文件
     */
    void render(String source, String target);
}
