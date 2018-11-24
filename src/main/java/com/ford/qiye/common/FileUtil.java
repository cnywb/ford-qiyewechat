package com.ford.qiye.common;

import java.io.File;

/**
 * Created by wanglijun on 16/8/21.
 */
public class FileUtil {
    public static final String EXCEL_EXTENSION = ".xls";

    public FileUtil() {
    }

    public static String mkDirs(String path) {
        File file = new File(path);
        if(!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }

        return file.getAbsolutePath();
    }

    public static File newFile(String path, String fileName) {
        StringBuilder sb = new StringBuilder();
        sb.append(mkDirs(path));
        sb.append(File.separator);
        sb.append(fileName);
        return new File(sb.toString());
    }
}
