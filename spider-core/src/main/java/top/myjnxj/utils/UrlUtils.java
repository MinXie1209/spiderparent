package top.myjnxj.utils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @ClassName UrlUtils
 * @Description TODO
 * @Author 江南小俊
 * @Date 2018/11/30 23:47
 * @Version 1.0.0
 **/
public class UrlUtils {
    /**
     * 规范化url
     *
     * @param url
     * @param refer
     * @return
     */
    public static String canonicalizeUrl(String url, String refer) {
        URL base;
        try {
            try {
                base = new URL(refer);
            } catch (MalformedURLException e) {
                URL abs = new URL(refer);
                return abs.toExternalForm();
            }
            if(url.startsWith("?")){
                    url=base.getPath()+url;
            }
            URL abs = new URL(base,url);
            return abs.toExternalForm();
        } catch (MalformedURLException e) {
          return "";
        }

    }
}
