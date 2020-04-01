package com.laozhang.cad.pdf.converter;

import com.laozhang.cad.pdf.Converter;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.Properties;

public class Cad2PdfConverter implements Converter {
    private final static String CONVERTER_CMD_TEMPLATE = "%s/AcmeCADConverter /r /e /ls /f 105 /a -2 /bookmark /hide 1 /d \"%s\" \"%s\"";
    private final static String CONVERTER_HOME_KEY = "cad.converter.home";
    private static String converterHome;

    static{
        try {
            InputStream in = Cad2PdfConverter.class.getClassLoader().getResourceAsStream("cad.properties");
            Properties props = new Properties();
            props.load(in);
            converterHome = props.getProperty(CONVERTER_HOME_KEY);
            if(StringUtils.isBlank(converterHome)){
                throw new RuntimeException(CONVERTER_HOME_KEY + " 配置为空！");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void setConverterHome(String converterHome){
        Cad2PdfConverter.converterHome = converterHome;
    }

    public Cad2PdfConverter(){
    }

    @Override
    public void save(String source,String target) {
        Runtime run = Runtime.getRuntime();
        try {
            String cmd = String.format(CONVERTER_CMD_TEMPLATE,this.converterHome,target,source);
            Process process = run.exec(cmd);
            while(process.isAlive()){

            }
            //System.out.println(source + " -> Done!");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
