import com.aspose.cad.CodePages;
import com.aspose.cad.Image;
import com.aspose.cad.LoadOptions;
import com.aspose.cad.fileformats.cad.CadDrawTypeMode;
import com.aspose.cad.imageoptions.CadRasterizationOptions;
import com.aspose.cad.imageoptions.PngOptions;
import com.aspose.cad.imageoptions.UnitType;

public class TestDwg2Png {
    public static void main(String[] args) throws Exception {
//        String sourcePath = "D:\\IDEA\\cad\\17.dwg";
//        String targetPath = "D:\\IDEA\\cad\\17.png";

        String sourcePath = "C:\\Users\\admin\\Desktop\\图纸文件\\WM1001-b01-027触摸屏挂件2.dwg";
        String targetPath = "C:\\Users\\admin\\Desktop\\图纸文件\\WM1001-b01-027触摸屏挂件2.png";
        PngOptions pngOptions = new PngOptions();
        pngOptions.setCompressionLevel(0);
        try {
            LoadOptions loadOptions = new LoadOptions();
            loadOptions.setSpecifiedEncoding(CodePages.SimpChineseOtherGb2312);
            Image image = Image.load(sourcePath,loadOptions);
            int unitType = image.getUnitType();
            int width = image.getWidth();
            int height = image.getHeight();
            int zoom = 1008000 / width / height;//100万像素等比倍数
            CadRasterizationOptions cadRasterizationOptions = new CadRasterizationOptions();
            cadRasterizationOptions.setPageHeight(height * zoom);
            cadRasterizationOptions.setPageWidth(width * zoom);
            cadRasterizationOptions.setDrawType(CadDrawTypeMode.UseObjectColor);
            cadRasterizationOptions.setUnitType(UnitType.Unitless);

            pngOptions.setVectorRasterizationOptions(cadRasterizationOptions);
            image.save(targetPath, pngOptions);
        }catch (Exception ex){

        }
    }
}
