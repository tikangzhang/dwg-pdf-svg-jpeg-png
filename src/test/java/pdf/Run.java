package pdf;

import com.laozhang.cad.pdf.util.PdfUtil;
import com.laozhang.cad.pdf.wm.*;

public class Run {
    public static void main(String[] args) {
        String sourcePath = "C:\\Users\\admin\\Desktop\\now\\drawing\\total_dwg_dxf_pdf\\SXFZ-4030-A-03-03-012A.pdf";
        String targetPath = "D:\\IDEA\\cad\\SXFZ-4030-A-03-03-012A.pdf";
        String pictPath = "D:\\IDEA\\cad\\202003301330.png";

        //new PdfUtil(sourcePath,targetPath).waterMark(new PDFFourCornerPictWaterMark(pictPath)).action();
        new PdfUtil(sourcePath,targetPath).waterMark(new PDFShortSidePictWaterMark(pictPath)).action();
        //new PdfUtil(sourcePath,targetPath).waterMark(new PDFTilePictWaterMark(pictPath,60.0f)).action();
    }
}
