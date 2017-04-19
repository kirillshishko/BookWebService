package com.epam.methods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static com.epam.CommonConstatns.*;

public class Request {
    private String method;
    private String path;
    private String version;
    private String host;
    private String connection;
    private String csp;
    private String cacheControl;
    private String userAgent;
    private String accept;
    private String acceptEncoding;
    private String acceptLanguage;
    private int contentLenght = 0;
    private String origin;
    private String contentType;
    private String body;

    public Request(BufferedReader bfr) {
        parseRequest(bfr);
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getCsp() {
        return csp;
    }

    public void setCsp(String csp) {
        this.csp = csp;
    }

    public String getCacheControl() {
        return cacheControl;
    }

    public void setCacheControl(String cacheControl) {
        this.cacheControl = cacheControl;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getAcceptEncoding() {
        return acceptEncoding;
    }

    public void setAcceptEncoding(String acceptEncoding) {
        this.acceptEncoding = acceptEncoding;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public void setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
    }

    public int getContentLenght() {
        return contentLenght;
    }

    public void setContentLenght(int contentLenght) {
        this.contentLenght = contentLenght;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    private void parseRequest(BufferedReader bfr) {
        List<String> headerValue = null;
        try {
            headerValue = getHeaderValue(bfr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String value : headerValue) {
            if (value.startsWith(GET)) {

                method = GET;
                path = getCertainSplitValueBy(value, PATH, SPACE);
                version = getCertainSplitValueBy(value, VERSION, SPACE);

            } else if (value.startsWith(POST)) {

                method = POST;
                path = getCertainSplitValueBy(value, PATH, SPACE);
                version = getCertainSplitValueBy(value, VERSION, SPACE);

            } else if (value.startsWith(HOST)) {

                host = getCertainSplitValueBy(value, VALUE, COLON_SPLITTER);

            } else if (value.startsWith(CONNECTION)) {

                connection = getCertainSplitValueBy(value, VALUE, COLON_SPLITTER);

            } else if (value.startsWith(CSP)) {

                csp = getCertainSplitValueBy(value, VALUE, COLON_SPLITTER);

            } else if (value.startsWith(CACHE_CONTROL)) {

                cacheControl = getCertainSplitValueBy(value, VALUE, COLON_SPLITTER);

            } else if (value.startsWith(USER_AGENT)) {

                userAgent = getCertainSplitValueBy(value, VALUE, COLON_SPLITTER);

            } else if (value.startsWith(ACCEPT)) {

                accept = getCertainSplitValueBy(value, VALUE, COLON_SPLITTER);

            } else if (value.startsWith(ACCEPT_ENCODING)) {

                acceptEncoding = getCertainSplitValueBy(value, VALUE, COLON_SPLITTER);

            } else if (value.startsWith(ACCEPT_LANGUAGE)) {

                acceptLanguage = getCertainSplitValueBy(value, VALUE, COLON_SPLITTER);

            } else if (value.startsWith(CONTENT_LENGTH)) {

                contentLenght = Integer.parseInt(getCertainSplitValueBy(value, VALUE,
                        COLON_SPLITTER));

            } else if (value.startsWith(ORIGIN)) {

                origin = getCertainSplitValueBy(value, VALUE, COLON_SPLITTER);

            } else if (value.startsWith(CONTENT_TYPE)) {

                contentType = getCertainSplitValueBy(value, VALUE, COLON_SPLITTER);
            }
        }

        if (contentLenght > 0) {
            body = headerValue.get(headerValue.size() - 1);
        }
    }

    private static String getCertainSplitValueBy(String sourceString, int splitIndex, String patternForSplit) {
        String lastValue = "";
        String[] content = sourceString.split(patternForSplit);

        if (content.length > 1) {
            lastValue = content[splitIndex].trim();
        }
        return lastValue;
    }
    private static String getLastSplitValueBy(String sourceString, String patternForSplit) {
        String lastValue = "";
        String[] content = sourceString.split(patternForSplit);

        if (content.length > 1) {
            lastValue = content[content.length - 1].trim();
        }
        return lastValue;
    }

    private static List<String> getHeaderValue(BufferedReader rq) throws IOException {

        List<String> headerValue = new ArrayList<String>();
        String strForReadHeader = "";

        while ((strForReadHeader = rq.readLine()) != null) {
            headerValue.add(strForReadHeader);
            if (strForReadHeader.isEmpty()) {
                break;
            }
        }
        int contentLeng = getContentLength(headerValue);
        if (contentLeng > 0) {
            headerValue.add(getPostBody(rq, contentLeng));
        }
        return headerValue;
    }
    private static int getContentLength(List<String> headerValue) {
        int contentLength = 0;
        for (String header : headerValue) {
            if (header.contains(CONTENT_LENGTH)) {
                contentLength = Integer.parseInt(getLastSplitValueBy(header, COLON_SPLITTER));
            }
        }
        return contentLength;
    }
    private static String getPostBody(BufferedReader rq, int contentLeng) throws IOException {

        StringWriter postRequest = new StringWriter();
        char[] buffer = new char[1024];
        int charToWrite = 0;
        while ((charToWrite = rq.read(buffer)) != -1) {
            postRequest.write(buffer, 0, charToWrite);
            if (charToWrite == contentLeng) {
                break;
            }
        }
        return postRequest.toString();
    }
}

