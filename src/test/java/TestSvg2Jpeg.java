import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class TestSvg2Jpeg {

    JPEGTranscoder trans = new JPEGTranscoder();

    public TestSvg2Jpeg() {}

    public void tile(String inputFilename,
                     String outputFilename,
                     Rectangle aoi) throws Exception {

        trans.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(1.0));
        // Set hints to indicate the dimensions of the output image
        // and the input area of interest.
        trans.addTranscodingHint(JPEGTranscoder.KEY_WIDTH, new Float(aoi.width));
        trans.addTranscodingHint(JPEGTranscoder.KEY_HEIGHT, new Float(aoi.height));
        trans.addTranscodingHint(JPEGTranscoder.KEY_AOI, aoi);

        // Transcode the file.
        String svgURI = new File(inputFilename).toURL().toString();
        TranscoderInput input = new TranscoderInput(svgURI);
        OutputStream ostream = new FileOutputStream(outputFilename);
        TranscoderOutput output = new TranscoderOutput(ostream);
        trans.transcode(input, output);

        // Flush and close the output.
        ostream.flush();
        ostream.close();
    }

    public static void main(String[] args) throws Exception {
        // Rasterize the samples/anne.svg document and save it
        // as four tiles.
        TestSvg2Jpeg p = new TestSvg2Jpeg();
        String in = "D:\\大数据\\bigdata_code\\cad\\17.svg";
        //String in = "samples/anne.svg";
        int documentWidth = 421;
        int documentHeight = 300;
        int dw2 = documentWidth;
        int dh2 = documentHeight;
        p.tile(in, "D:\\大数据\\bigdata_code\\cad\\tileTopLeft.jpg", new Rectangle(0, 0, dw2, dh2));
        p.tile(in, "D:\\大数据\\bigdata_code\\cad\\tileTopRight.jpg", new Rectangle(dw2, 0, dw2, dh2));
        p.tile(in, "D:\\大数据\\bigdata_code\\cad\\tileBottomLeft.jpg", new Rectangle(0, dh2, dw2, dh2));
        p.tile(in, "D:\\大数据\\bigdata_code\\cad\\tileBottomRight.jpg", new Rectangle(dw2, dh2, dw2, dh2));
        System.exit(0);
    }
}
