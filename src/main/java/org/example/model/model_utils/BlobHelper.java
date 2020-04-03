package org.example.model.model_utils;

import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import org.example.App;
import org.example.utils.InMemory;
import org.example.utils.MyStrings;
import org.example.utils.MyStyles;

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

    public void showAvatar(Circle circle, InMemory memory, double radius) {
        MyStyles styles = new MyStyles();
        if (memory.getUser().getPicture() != null) {
            Image img = BlobHelper.bytesToImage(memory.getUser().getPicture());
            styles.transformToCircle(circle, img, radius);
        } else {
            Image img = new Image(App.class.getResourceAsStream(MyStrings.DEFAULT_PROFILE));
            styles.transformToCircle(circle, img, radius);
        }
    }
}