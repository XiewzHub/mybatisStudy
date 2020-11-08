package com.xiewz.io;

import java.io.InputStream;

public class Resources {
    public static InputStream getResourceAsSteam(String mapperPath) {
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream(mapperPath);
        return  resourceAsStream;
    }
}
