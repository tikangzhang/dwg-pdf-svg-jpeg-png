import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;

public class TestPdfAddPict01 {
    private final static String sourcePdf = "C:\\Users\\admin\\Desktop\\now\\drawing\\total_java\\4A18025A001A0.pdf";
    private final static String targetPdf = "C:\\Users\\admin\\Desktop\\now\\drawing\\total_java_convert\\4A18025A001A0.pdf";

    private final static String pictPath = "D:\\IDEA\\cad\\202003230930.jpg";
    private final static int pictWidth = 120;
    private final static int pictHeight = 60;
    private final static int margin = 20;
    public static void main(String[] args) throws Exception {

        PdfReader pdfReader = new PdfReader(sourcePdf);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(targetPdf));

        PdfContentByte under = pdfStamper.getOverContent(1);

        Rectangle pageSize = pdfReader.getPageSize(1);
        int[][] corners = getCorner((int)pageSize.getWidth(),(int)pageSize.getHeight(),margin);
        for(int[] point : corners) {
            under.addImage(getImage(point[0],point[1]));
        }

        pdfStamper.setFormFlattening(true);
        pdfStamper.close();
    }

    private static Image getImage(int x,int y)throws Exception{
        Image image = Image.getInstance(pictPath);
        image.scaleAbsolute(pictWidth, pictHeight);
        image.setAbsolutePosition(x, y);
        return image;
    }

    private static int[][] getCorner(int pageWidth,int pageHeight,int offset){
        int[][] corners = new int[4][2];
        corners[0][0] = corners[2][0] = offset;
        corners[1][0] = corners[3][0] = pageWidth - pictWidth - offset;

        corners[0][1] = corners[1][1] = pageHeight - pictHeight - offset;
        corners[2][1] = corners[3][1] = offset;
        return corners;
    }
}
