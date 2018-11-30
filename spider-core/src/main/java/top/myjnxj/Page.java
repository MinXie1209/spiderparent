package top.myjnxj;

import org.apache.commons.lang3.StringUtils;
import top.myjnxj.selector.Html;
import top.myjnxj.selector.Json;
import top.myjnxj.selector.Selectable;
import top.myjnxj.utils.HttpConstant;
import top.myjnxj.utils.UrlUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Page
 * @Description 包含页面抽取的结果和要去取的URL
 * @Author 江南小俊
 * @Date 2018/11/30 21:25
 * @Version 1.0.0
 **/
public class Page {
    //请求
    private Request request;
    //抓取的结果 用于持久化
    private ResultItems resultItems = new ResultItems();
    //当前页内容
    private Html html;
    private Json json;
    private String rawText;
    //选择器
    private Selectable url;
    //是否下载成功
    private boolean downloadSuccess = true;
    private byte[] bytes;
    private List<Request> targetRequest = new ArrayList<Request>();
    private String charset;
    private Map<String,List<String>>headers;
    //状态码
    private int statusCode=HttpConstant.StatusCode.CODE_200;

    public static Page fail() {
        Page page = new Page();
        page.setDownloadSuccess(false);
        return page;
    }

    public Page setSkip(boolean skip) {
        resultItems.setSkip(skip);
        return this;
    }


    public void addTargetRequest(Request request) {
        targetRequest.add(request);
    }

    public void addTargetRequest(String requestString) {
        if (StringUtils.isBlank(requestString) || "#".equals(requestString)) {
            return;
        }
        requestString = UrlUtils.canonicalizeUrl(requestString, url.toString());
        targetRequest.add(new Request(requestString));
    }

    public void addTargetRequest(List<String> requestStrings) {
        for (String requestString : requestStrings) {
            addTargetRequest(requestString);
        }
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
        //TODO
        this.resultItems.setRequest(request);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ResultItems getResultItems() {
        return resultItems;
    }

    public void setResultItems(ResultItems resultItems) {
        this.resultItems = resultItems;
    }

    public Html getHtml() {
        if (html == null) {
            html = new Html(rawText, request.getUrl());
        }
        return html;
    }

    public void setHtml(Html html) {
        this.html = html;
    }

    public Json getJson() {
        if (json == null) {
            json = new Json(rawText);
        }
        return json;
    }

    public void setJson(Json json) {
        this.json = json;
    }

    public String getRawText() {
        return rawText;
    }
    public Page setRawText(String rawText) {
        this.rawText=rawText;
        return this;
    }

    public Selectable getUrl() {
        return url;
    }

    public void setUrl(Selectable url) {
        this.url = url;
    }

    public boolean isDownloadSuccess() {
        return downloadSuccess;
    }

    public void setDownloadSuccess(boolean downloadSuccess) {
        this.downloadSuccess = downloadSuccess;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }


    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    @Override
    public String toString() {
        return "Page{" +
                "request=" + request +
                ", resultItems=" + resultItems +
                ", html=" + html +
                ", json=" + json +
                ", rawText='" + rawText + '\'' +
                ", url=" + url +
                ", downloadSuccess=" + downloadSuccess +
                ", bytes=" + Arrays.toString(bytes) +
                ", targetRequest=" + targetRequest +
                ", charset='" + charset + '\'' +
                ", headers=" + headers +
                ", statusCode=" + statusCode +
                '}';
    }
}
