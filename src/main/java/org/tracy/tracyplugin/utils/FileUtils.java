package org.tracy.tracyplugin.utils;

import com.intellij.notification.Notification;
import com.intellij.openapi.util.ClassLoaderUtil;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tracy.tracyplugin.notification.TracyNotification;
import org.tracy.tracyplugin.providers.StructureFileProvider;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class FileUtils {
    private static final Logger LOG = LoggerFactory.getLogger(StructureFileProvider.class);

    public String getBaseUrlFromFile(String fileName) {
        String data = "";
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        try {
            assert inputStream != null;
            try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8); BufferedReader reader = new BufferedReader(streamReader)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    data = line;
                }
            }
            inputStream.close();
        } catch (IOException e) {
            LOG.warn("Cannot read a file base url: " + e.getMessage());
        }
        return data;
    }

    public void updateBaseUrlInTheFile(String filename, String newBaseUrl) {
        try {
            ClassLoader classLoader = FileUtils.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(filename);

            Scanner input = new Scanner(new File(filename));
            System.out.println(input.nextLine());
            LOG.warn("Success write a new base in file");
        } catch (Exception e) {
            LOG.warn("Cannot write a file base url: " + e.getMessage());
        }
    }
}
