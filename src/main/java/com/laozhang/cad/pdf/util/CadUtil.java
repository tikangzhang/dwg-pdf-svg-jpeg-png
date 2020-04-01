package com.laozhang.cad.pdf.util;

import com.laozhang.cad.pdf.converter.Cad2PdfConverter;
import com.laozhang.cad.pdf.Converter;

/**
 * 1.先设置环境变量：CAD_CONVERTER_HOME
 *   否则报错
 * 2.
 */
public class CadUtil {
    private String source;
    private String target;

    /**
     * @param source 源文件绝对路径
     * @param target 保存文件绝对路径
     */
    public CadUtil(String source,String target){
        this.source = source;
        this.target = target;
    }

    public void toPDF(){
        Converter converter = new Cad2PdfConverter();
        converter.save(this.source,target);
    }
}
