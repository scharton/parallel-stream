package parallel.stream;

import okhttp3.OkHttpClient;

public class Request {
    public String getData() {
        OkHttpClient client = new OkHttpClient();
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
        okhttp3.Request request = builder.url("https://httpstat.us")
                .build();

        return "";
    }

}
