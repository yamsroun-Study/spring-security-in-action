package yamsroun.ssiach9.web.data;

public enum HttpHeader {
    REQUEST_ID("Request-Id");

    private final String code;

    HttpHeader(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
