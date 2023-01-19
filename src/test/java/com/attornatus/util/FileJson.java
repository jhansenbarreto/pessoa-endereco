package com.attornatus.util;

import java.io.IOException;
import java.io.InputStream;

import java.nio.charset.Charset;

import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;

public class FileJson {

    public static String getContentFileJson(String path) {
        try {
            InputStream stream = ResourceUtils.class.getResourceAsStream(path);
            return StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
