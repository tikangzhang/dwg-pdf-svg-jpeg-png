package com.laozhang.cad.app;

import java.io.*;

public class BatchDwgDxf2Pdf {
    public static void main(String[] args) throws Exception {
        String sourcePath = "C:\\Users\\admin\\Desktop\\now\\drawing\\total_dwg_dxf_pdf";
        String targetPath = "C:\\Users\\admin\\Desktop\\now\\drawing\\total_donet_convert_pdf";

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
        if(files == null){
            System.out.println("No Dwg or Dxf.");
            return;
        }
        String fileNamePost;
        String cmdTemplate = "D:\\SoftwareInstall\\Acme CAD Converter\\AcmeCADConverter /r /e /ls /f 105 /a -2 /bookmark /hide 1 " + ///w 1920 /h 1080
                "/d \"%s\" \"%s\"";
        for (File file : files) {
            fileNamePost = file.getName();
            Runtime run = Runtime.getRuntime();
            try {
                String cmd = String.format(cmdTemplate,targetPath + "\\" + fileNamePost + ".pdf",sourcePath + "\\" + fileNamePost);
                Process process = run.exec(cmd);
                while(process.isAlive()){

                }
                System.out.println(fileNamePost + " -> Done!");
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
