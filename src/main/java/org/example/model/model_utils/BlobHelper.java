package org.example.model.model_utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class BlobHelper {

    private final static Logger LOGGER = Logger.getLogger(BlobHelper.class.getName());

    public static byte[] byteThePic(String file) {
        ByteArrayOutputStream bos = null;
        try {
            File f = new File(file);
            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            for (int len; (len = fis.read(buffer)) != -1; ) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return bos != null ? bos.toByteArray() : null;
    }
}
