import com.aspose.cad.*;
import com.aspose.cad.fileformats.cad.CadDrawTypeMode;
import com.aspose.cad.imageoptions.CadRasterizationOptions;
import com.aspose.cad.imageoptions.PdfOptions;
import com.aspose.cad.imageoptions.UnitType;

public class TestDwg2Pdf {
    public static void main(String[] args) throws Exception {
        String sourcePath = "D:\\IDEA\\cad\\17.dwg";
        String targetPath = "D:\\IDEA\\cad\\17.pdf";
        String targetAPath = "D:\\IDEA\\cad\\17a.pdf";
        String targetBPath = "D:\\IDEA\\cad\\17b.pdf";
        String targetCPath = "D:\\IDEA\\cad\\17c.pdf";


        LoadOptions loadOptions = new LoadOptions();
        loadOptions.setSpecifiedEncoding(CodePages.SimpChineseOtherGb2312);

        Image image = Image.load(sourcePath,loadOptions);

        // Create an instance of PdfOptions
        PdfOptions pdfOptions = new PdfOptions();

        CadRasterizationOptions cadRasterizationOptions = new CadRasterizationOptions();
        cadRasterizationOptions.setPageHeight(1080);
        cadRasterizationOptions.setPageWidth(1920);
        cadRasterizationOptions.setDrawType(CadDrawTypeMode.UseObjectColor);
        cadRasterizationOptions.setUnitType(UnitType.Unitless);

        cadRasterizationOptions.getGraphicsOptions().setSmoothingMode(SmoothingMode.HighQuality);
        cadRasterizationOptions.getGraphicsOptions().setTextRenderingHint(TextRenderingHint.AntiAliasGridFit);
        cadRasterizationOptions.getGraphicsOptions().setInterpolationMode(InterpolationMode.HighQualityBicubic);
        pdfOptions.setVectorRasterizationOptions(cadRasterizationOptions);

        image.save(targetPath, pdfOptions);
//
//
//        PdfDocumentOptions pdfDocumentOptions = new PdfDocumentOptions();
//        pdfOptions.setCorePdfOptions(pdfDocumentOptions);
//
//
//        pdfDocumentOptions.setCompliance(PdfCompliance.PdfA1a);
//        image.save(targetAPath, pdfOptions);
//
//        pdfDocumentOptions.setCompliance(PdfCompliance.PdfA1b);
//        image.save(targetBPath, pdfOptions);
//
//        pdfDocumentOptions.setCompliance(PdfCompliance.Pdf15);
//        image.save(targetCPath, pdfOptions);
    }
}
