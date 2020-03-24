import com.aspose.pdf.Document;

import com.aspose.pdf.devices.JpegDevice;
import com.aspose.pdf.devices.Resolution;

public class TestPdf2Jpeg {
    public static void main(String[] args) throws Exception {

        Document pdfDocument = new Document("D:\\IDEA\\cad\\17.pdf");
        java.io.OutputStream imageStream = new java.io.FileOutputStream("D:\\IDEA\\cad\\17.jpg");
        // Create JPEG device with specified attributes
        // Quality [0-100], 100 is Maximum
        // Create Resolution object
        Resolution resolution = new Resolution(300);
        // Create JpegDevice object where second argument indicates the quality of resultant image
        JpegDevice jpegDevice = new JpegDevice(resolution, 100);
        // Convert a particular page and save the image to stream
        jpegDevice.process(pdfDocument.getPages().get_Item(1), imageStream);
        // Close the stream
        imageStream.close();
    }
}
