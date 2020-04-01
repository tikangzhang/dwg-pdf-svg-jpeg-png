package cad;

import com.laozhang.cad.pdf.util.CadUtil;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConcurrentRun {
    public static void main(String[] args) {
        String sourcePath = "C:\\Users\\admin\\Desktop\\now\\drawing\\total_dwg_dxf_pdf";
        String targetPath = "C:\\Users\\admin\\Desktop\\now\\drawing\\total_donet_convert_pdf";
        int threadNum = 8;

        File sourceDir = new File(sourcePath);
        File[] files = sourceDir.listFiles(new FileFilter() {
            String fileName;
            @Override
            public boolean accept(File pathname) {
                fileName = pathname.getName();
                if(fileName.endsWith("dwg") || fileName.endsWith("DWG")|| fileName.endsWith("dxf")|| fileName.endsWith("DXF")){
                    return true;
                }
                return false;
            }
        });

        List<List<File>> list = getParallel(files,threadNum);
        for (int i = 0; i < threadNum; i++) {
            final int a = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<File> files01 = list.get(a);
                    String fileName,sourceFileName,targetFileName;
                    for(File file : files01){
                        fileName = file.getName();
                        targetFileName = targetPath + "\\" + fileName + ".pdf";
                        sourceFileName = sourcePath + "\\" + fileName;
                        new CadUtil(sourceFileName,targetFileName).toPDF();
                    }
                    System.out.println(Thread.currentThread().getName() + " " + files01.size() + " -> Done!");
                }
            }).start();
        }
        new CadUtil(sourcePath,targetPath).toPDF();
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
