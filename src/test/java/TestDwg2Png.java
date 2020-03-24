import com.aspose.cad.Image;
import com.aspose.cad.fileformats.cad.CadDrawTypeMode;
import com.aspose.cad.imageoptions.CadRasterizationOptions;
import com.aspose.cad.imageoptions.PngOptions;
import com.aspose.cad.imageoptions.UnitType;

public class TestDwg2Png {
    public static void main(String[] args) throws Exception {
        String sourcePath = "D:\\IDEA\\cad\\17.dwg";
        String targetPath = "D:\\IDEA\\cad\\17.png";

        PngOptions pngOptions = new PngOptions();
        String prefix;
        pngOptions.setCompressionLevel(0);

        try {
            Image image = Image.load(sourcePath);
            int width = image.getWidth();
            int height = image.getHeight();
            int zoom = 1008000 / width / height;//100万像素等比倍数
            CadRasterizationOptions cadRasterizationOptions = new CadRasterizationOptions();
            cadRasterizationOptions.setPageHeight(zoom * width);
            cadRasterizationOptions.setPageWidth(zoom * height);
            cadRasterizationOptions.setDrawType(CadDrawTypeMode.UseObjectColor);
            cadRasterizationOptions.setUnitType(UnitType.Unitless);

            pngOptions.setVectorRasterizationOptions(cadRasterizationOptions);
            image.save(targetPath, pngOptions);
        }catch (Exception ex){

        }
    }
}
