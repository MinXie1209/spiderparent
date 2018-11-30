package top.myjnxj.model;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName
 * @Description TODO
 * @Author 江南小俊
 * @Date 2018/11/30 20:51
 * @Version 1.0.0
 **/
public class HttpRequestBody {
    public static abstract class ContentType {
        private static final String JSON = "application/json";
        private static final String XML = "text/xml";
        private static final String FORM = "application/x-www-form-urlencoded";
        private static final String MULTIPART = "multipart/form-data";
    }


    private byte[] body;
    private String contentType;
    private String encoding;

    public HttpRequestBody() {
    }

    public HttpRequestBody(byte[] body, String contentType, String encoding) {
        this.body = body;
        this.contentType = contentType;
        this.encoding = encoding;
    }

    /**
     * 获取以Json格式参数的HttpRequestBody
     *
     * @param json
     * @param encoding
     * @return
     */
    private HttpRequestBody json(String json, String encoding) {
        try {
            return new HttpRequestBody(json.getBytes(encoding), ContentType.JSON, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("illegal encoding " + encoding, e);
        }

    }

    /**
     * 获取以xml格式参数的HttpRequestBody
     *
     * @param xml
     * @param encoding
     * @return
     */
    private HttpRequestBody xml(String xml, String encoding) {
        try {
            return new HttpRequestBody(xml.getBytes(encoding), ContentType.XML, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("illegal encoding " + encoding, e);
        }

    }

    /**
     * 获取以表单参数的HttpRequestBody
     *
     * @param parameters
     * @param encoding
     * @return
     */
    private HttpRequestBody form(Map<String, Object> parameters, String encoding) {
        List<NameValuePair> nameValuePairs = new ArrayList<>(parameters.size());
        for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(parameter.getKey(), String.valueOf(parameter.getValue())));
        }
        try {
            return new HttpRequestBody(URLEncodedUtils.format(nameValuePairs,encoding).getBytes(encoding), ContentType.JSON, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("illegal encoding " + encoding, e);
        }

    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
