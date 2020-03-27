import com.aspose.cad.Image;
import com.aspose.cad.fileformats.cad.CadDrawTypeMode;
import com.aspose.cad.imageoptions.CadRasterizationOptions;
import com.aspose.cad.imageoptions.PngOptions;
import com.aspose.cad.imageoptions.SvgOptions;
import com.aspose.cad.imageoptions.UnitType;

public class TestDwg2Svg {
    public static void main(String[] args) throws Exception {
        //String sourcePath = "D:\\IDEA\\cad\\17.dwg";
        String targetPath = "D:\\IDEA\\cad\\17.svg";
        String sourcePath = "C:\\Users\\admin\\Desktop\\图纸文件\\KZ08TEE239-36 刷轮固定套.dwg";

        SvgOptions options = new SvgOptions();
        try {
            Image image = Image.load(sourcePath);
            System.out.println(image.getUnitType());
            System.out.println(image.getWidth() + " - " + image.getHeight());
            CadRasterizationOptions cadRasterizationOptions = new CadRasterizationOptions();
            cadRasterizationOptions.setPageWidth(1920);
            cadRasterizationOptions.setPageHeight(1080);
            cadRasterizationOptions.setDrawType(CadDrawTypeMode.UseObjectColor);

            options.setVectorRasterizationOptions(cadRasterizationOptions);
            //options.setColorType(SvgColorMode.Grayscale);
            //options.setTextAsShapes(true);
            //image.save(targetPath,options);
        }catch (Exception ex){

        }
    }
}
