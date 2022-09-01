package parallel.stream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Optional;

public class RequestUtil {
    public String getData(Optional<Integer> delaySeconds, Optional<Integer> statusCode) {
        Integer status = statusCode.orElse(200);
        Integer delay = delaySeconds.orElse(0) ;
        String url = String.format("https://httpstat.us/%s?sleep=%s", status, delay * 1000);
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url)
                .build();
        try(Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
