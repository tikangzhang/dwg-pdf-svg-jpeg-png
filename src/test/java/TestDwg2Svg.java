import com.aspose.cad.Image;
import com.aspose.cad.fileformats.cad.CadDrawTypeMode;
import com.aspose.cad.imageoptions.CadRasterizationOptions;
import com.aspose.cad.imageoptions.PngOptions;
import com.aspose.cad.imageoptions.SvgOptions;
import com.aspose.cad.imageoptions.UnitType;

public class TestDwg2Svg {
    public static void main(String[] args) throws Exception {
        String sourcePath = "D:\\IDEA\\cad\\17.dwg";
        String targetPath = "D:\\IDEA\\cad\\17.svg";

        SvgOptions options = new SvgOptions();
        try {
            Image image = Image.load(sourcePath);
            int width = image.getWidth();
            int height = image.getHeight();
            int zoom = 1008000 / width / height;//100万像素等比倍数

            CadRasterizationOptions cadRasterizationOptions = new CadRasterizationOptions();
            cadRasterizationOptions.setPageHeight(height * zoom);
            cadRasterizationOptions.setPageWidth(width * zoom);
            cadRasterizationOptions.setDrawType(CadDrawTypeMode.UseObjectColor);

            options.setVectorRasterizationOptions(cadRasterizationOptions);
            //options.setColorType(SvgColorMode.Grayscale);
            //options.setTextAsShapes(true);
            image.save(targetPath,options);
        }catch (Exception ex){

        }
    }
}
