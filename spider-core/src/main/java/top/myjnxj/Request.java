package top.myjnxj;


import top.myjnxj.model.HttpRequestBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName Request
 * @Description 包含要爬行的url和一些附加信息,是Page的一个成员变量
 * @Author 江南小俊
 * @Date 2018/11/30 20:50
 * @Version 1.0.0
 **/
public class Request {
    private String url;
    private String method;
    private HttpRequestBody requestBody;
    private Map<String,Object>extras;
    private Map<String,String> cookies=new HashMap<>();
    private Map<String,String> headers=new HashMap<>();

    public Request(String url) {
        this.url=url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof Request)){
            return false;
        }
        Request request = (Request) o;
        return Objects.equals(url, request.url) &&
                Objects.equals(method, request.method);
    }

    @Override
    public int hashCode() {

        return Objects.hash(url, method);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public HttpRequestBody getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(HttpRequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public Map<String, Object> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, Object> extras) {
        this.extras = extras;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
