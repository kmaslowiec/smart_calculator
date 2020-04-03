package org.example.model.model_utils;

import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class BlobHelper {

    private final static Logger LOGGER = Logger.getLogger(BlobHelper.class.getName());

    public static byte[] fileToBytes(File file) {
        ByteArrayOutputStream bos = null;
        try {
            FileInputStream fis = new FileInputStream(file);
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

    public static Image bytesToImage(byte[] picFromDb) {
        InputStream is = new ByteArrayInputStream(picFromDb);
        return new Image(is);
    }
}