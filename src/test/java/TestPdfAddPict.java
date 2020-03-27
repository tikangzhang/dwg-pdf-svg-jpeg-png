import com.aspose.pdf.*;

public class TestPdfAddPict {
    public static void main(String[] args) throws Exception {
        Document pdfDocument = new Document("C:\\Users\\admin\\Desktop\\now\\drawing\\total_java\\4A18025A001A0.pdf");

        String pictPath = "D:\\IDEA\\cad\\202003230930.jpg";
        int lowerLeftX = 100;
        int lowerLeftY = 100;
        int upperRightX = 220;
        int upperRightY = 160;
        Page page = pdfDocument.getPages().get_Item(1);
        java.io.FileInputStream imageStream = new java.io.FileInputStream(new java.io.File(pictPath));
        page.getResources().getImages().add(imageStream);
        page.getContents().add(new com.aspose.pdf.operators.GSave());

        System.out.println(page.getRect().toString());
        Rectangle rectangle = new Rectangle(lowerLeftX, lowerLeftY, upperRightX, upperRightY);
        //Matrix matrix = new Matrix(new double[] { rectangle.getURX() - rectangle.getLLX(), 0, 0, rectangle.getURY() - rectangle.getLLY(), rectangle.getLLX(), rectangle.getLLY() });

        // Matrix(a,b,c,d,e,f) 平移只跟e f有关
        Matrix matrix = new Matrix(new double[] { rectangle.getURX() - rectangle.getLLX(), 0, 0, rectangle.getURY() - rectangle.getLLY(), 0,0 });
        System.out.println(matrix.toString());
        page.getContents().add(new com.aspose.pdf.operators.ConcatenateMatrix(matrix));
        XImage ximage = page.getResources().getImages().get_Item(page.getResources().getImages().size());
        page.getContents().add(new com.aspose.pdf.operators.Do(ximage.getName()));
        page.getContents().add(new com.aspose.pdf.operators.GRestore());
        pdfDocument.save("C:\\Users\\admin\\Desktop\\now\\drawing\\total_java_convert\\Output.pdf");
        // Close image stream
        imageStream.close();
    }
}
