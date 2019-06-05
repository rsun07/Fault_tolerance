package pers.xiaoming.fault_tolerance.common.backends;

import org.apache.http.client.fluent.Request;

import java.io.IOException;

public class HttpClientImpl implements HttpClient {
    private final String uriPrefix;

    public HttpClientImpl(String endpoint, String path) {
        this.uriPrefix = endpoint + "/" + path + "?trip_id";
    }

    public String get(long id) throws IOException {
        return Request.Get(uriPrefix + id)
                .execute()
                .returnContent()
                .asString();
    }
}
