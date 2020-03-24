package com.laozhang.cad.svg.content;

public abstract class AbstractSVGMaker implements IContentMaker {
    public static final String NODE_X = "x";
    public static final String NODE_Y = "y";

    protected String inferType(String path){
        String fileType = path.substring(path.lastIndexOf("."));
        switch (fileType){
            case "jpg":
                return "jpeg";
            case "png":
                return "png";
            default:
                return "jpeg";
        }
    }
}
