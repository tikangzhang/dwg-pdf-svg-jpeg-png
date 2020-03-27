import com.aspose.pdf.Document;
import com.aspose.pdf.Page;
import com.aspose.pdf.SvgSaveOptions;

public class TestPdf2Svg {
    public static void main(String[] args) throws Exception {

        Document doc = new Document("C:\\Users\\admin\\Desktop\\now\\drawing\\total_java\\4A18025A001A0.pdf");
        //Document doc = new Document("C:\\Users\\admin\\Downloads\\Apache+Flink+v1.9.pdf");

        Page item = doc.getPages().get_Item(1);
        doc = new Document();
        doc.getPages().add(item);

        SvgSaveOptions saveOptions = new SvgSaveOptions();

        saveOptions.ScaleToPixels = true;
        saveOptions.TryMergeAdjacentSameBackgroundImages = true;
        saveOptions.TreatTargetFileNameAsDirectory = false;
        saveOptions.CompressOutputToZipArchive = false;
        saveOptions.setExtractOcrSublayerOnly(true);

        String outFileName = "C:\\Users\\admin\\Desktop\\now\\drawing\\total_java_convert\\Output.svg";
        doc.save(outFileName, saveOptions); }
}
