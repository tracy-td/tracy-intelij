package br.ufpb.dcx.tdm.utils;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.PropertiesUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertiesUtil {
    private static Properties props = null;

    private static final Logger LOG = Logger.getInstance(EDTInvoker.class);

    private static Properties getProperties() throws IOException {
        if (props == null) {
            LOG.warn("READ PROPERTIES");
            String path = " ${idea.home.path}/idea.properties";
            InputStream i = PropertiesUtil.class.getResourceAsStream("${idea.home.path}/idea.properties");
            LOG.warn("READ PROPERTIES" + path);
            props = new Properties();
            props.load(i);
            assert i != null;
            i.close();
        }
        return props;
    }

    public static String getProperty(String key) {
        LOG.warn("Call Get Property");
        try {
            LOG.warn("Try get the Property" + key);
            return getProperties().getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
