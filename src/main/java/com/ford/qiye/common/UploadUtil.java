package com.ford.qiye.common;

import com.fute.common.util.ImgCompress;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by wanglijun on 16/8/14.
 */
public class UploadUtil {
    /**
     * 日志
     */
    private  static final Logger logger= LoggerFactory.getLogger (UploadUtil.class);

    public static String copyResolutionFile(InputStream stream, String ext, String webRootPath, String url, int width, int height) {
        InputStream in = null;
        OutputStream out = null;


        String temp = url + Math.abs(new Random ().nextLong()) + ext;
        try {
            temp = parsePNGImage(temp);
            //对图片进行压缩
            ImgCompress imgCom = new ImgCompress(stream);
            imgCom.resize(width, height, webRootPath + temp);
        } catch (Exception e) {
             logger.error (e.getMessage (),e);
        } finally {
            IOUtils.closeQuietly (in);
            IOUtils.closeQuietly (out);
        }
        return temp;
    }

    private static String parsePNGImage(String sourcePath) {
        return sourcePath.substring(0, sourcePath.lastIndexOf(".")) + ".png";
    }

}
