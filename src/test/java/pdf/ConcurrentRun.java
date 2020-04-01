package pdf;

import com.laozhang.cad.pdf.WaterMark;
import com.laozhang.cad.pdf.util.PdfUtil;
import com.laozhang.cad.pdf.wm.PDFShortSidePictWaterMark;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConcurrentRun {
    public static void main(String[] args) {
        String sourcePath01 = "C:\\Users\\admin\\Desktop\\now\\drawing\\total_donet_convert_pdf";
        String sourcePath02 = "C:\\Users\\admin\\Desktop\\now\\drawing\\total_dwg_dxf_pdf";
        String targetPath = "C:\\Users\\admin\\Desktop\\now\\drawing\\total_pdf_result";
        int threadNum = 8;
        FileFilter fileFilter =  new FileFilter() {
            String fileName;
            @Override
            public boolean accept(File pathname) {
                fileName = pathname.getName();
                if(fileName.endsWith("pdf") || fileName.endsWith("PDF")){
                    return true;
                }
                return false;
            }
        };
        File[] files01 = new File(sourcePath01).listFiles(fileFilter);
        File[] files02 = new File(sourcePath02).listFiles(fileFilter);
        File[] files = new File[files01.length + files02.length];
        System.arraycopy(files01,0,files,0,files01.length);
        System.arraycopy(files02,0,files,files01.length,files02.length);

        List<List<File>> list = getParallel(files,threadNum);

        WaterMark wm = new PDFShortSidePictWaterMark("D:\\IDEA\\cad\\202003301330.png");
        for (int i = 0; i < threadNum; i++) {
            final int a = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<File> files01 = list.get(a);
                    String fileName,sourceFileName,targetFileName;
                    for(File file : files01){
                        fileName = file.getName();
                        sourceFileName = file.getAbsolutePath();
                        targetFileName = targetPath + "\\" + fileName;
                        new PdfUtil(sourceFileName,targetFileName).waterMark(wm).action();
                    }
                    System.out.println(Thread.currentThread().getName() + " " + files01.size() + " -> Done!");
                }
            }).start();
        }
    }

    private static List<List<File>> getParallel(File[] files,int parallel){
        int len = files.length;
        List<List<File>> list = new ArrayList<>(parallel);
        for (int i = 0; i < parallel; i++) {
            list.add(new LinkedList<>());
        }
        for (int i = 0; i < len; i++) {
            list.get(i % parallel).add(files[i]);
        }
        System.out.println("总共：" + len);
        return list;
    }
}
