package cad;

import com.laozhang.cad.pdf.util.CadUtil;

public class Run {
    public static void main(String[] args) {
        String sourcePath = "C:\\Users\\admin\\Desktop\\now\\drawing\\total_dwg_dxf_pdf\\TK1160202002A0.dwg";
        String targetPath = "D:\\IDEA\\cad\\TK1160202002A0.dwg.pdf";
        new CadUtil(sourcePath,targetPath).toPDF();
    }
}
