import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.FileOutputStream;

public class TestPdfAddPict01 {
    private final static String sourcePdf = "C:\\Users\\admin\\Desktop\\now\\drawing\\total_dwg_dxf_pdf\\4A18025A001A0.pdf";
    // private final static String sourcePdf = "D:\\IDEA\\cad\\4A18025A001A0.pdf";
    private final static String tempPdf = "D:\\IDEA\\cad\\4A18025A001A0_temp.pdf";
    //private final static String targetPdf = "D:\\IDEA\\cad\\4A18025A001A0_temp.pdf";
    private final static String targetPdf = "D:\\IDEA\\cad\\4A18025A001A0.pdf";

    //private final static String pictPath = "D:\\IDEA\\cad\\202003230930.jpg";
    private final static String pictPath = "D:\\IDEA\\cad\\202003301330.png";
    private final static int pictWidth = 120;
    private final static int pictHeight = 120;

    public static void main(String[] args) throws Exception {

        PdfReader pdfReader = new PdfReader(sourcePdf);PdfReader.unethicalreading = true;
        Rectangle pageSize = pdfReader.getPageSize(1);

        Rectangle pageGroundSize = new Rectangle(pageSize.getWidth() + pictWidth * 2,pageSize.getHeight() + pictHeight * 2);
        Document document = new Document(pageGroundSize,pictWidth,pictWidth,pictHeight,pictHeight);

        ByteOutputStream bos = new ByteOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, bos);
        document.open();

        PdfContentByte cb = writer.getDirectContent();
        int n = pdfReader.getNumberOfPages();
        for (int j = 1; j <= n; j++) {
            document.newPage();
            PdfImportedPage page = writer.getImportedPage(pdfReader, j);
            cb.addTemplate(page, pictWidth, pictHeight);
        }

        document.close();

        pdfReader = new PdfReader(bos.toByteArray());
        pageSize = pdfReader.getPageSize(1);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(targetPdf));

        pdfStamper.setEncryption(null,"xuanyu".getBytes(),PdfWriter.ALLOW_PRINTING,PdfWriter.ENCRYPTION_AES_128);
        PdfContentByte under = pdfStamper.getOverContent(1);

        int[][] corners = getCorner((int)pageSize.getWidth(),(int)pageSize.getHeight());
        for(int[] point : corners) {
            under.addImage(getImage(point[0] ,point[1] ));
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

    private static int[][] getCorner(int pageWidth,int pageHeight){
        int[][] corners = new int[4][2];
        corners[0][0] = corners[2][0] = 0;
        corners[1][0] = corners[3][0] = pageWidth - pictWidth;

        corners[0][1] = corners[1][1] = pageHeight - pictHeight;
        corners[2][1] = corners[3][1] = 0;
        return corners;
    }
}
