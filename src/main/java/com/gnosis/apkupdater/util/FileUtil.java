package com.gnosis.apkupdater.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by pcc on 12.05.2015.
 */
public class FileUtil {
    public static boolean deleteFile(String url) {
        return new File(url).delete();
    }

    public static boolean saveMultipart(MultipartFile file, String name) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = System.getProperty("user.home");
                File dir = new File(rootPath + File.separator + "projectFiles");
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();


                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }
}
