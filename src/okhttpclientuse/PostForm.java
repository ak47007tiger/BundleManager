package okhttpclientuse;
import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public final class PostForm {
  private final OkHttpClient client = new OkHttpClient();

  public void run() throws Exception {
    RequestBody formBody = new FormBody.Builder()
        .add("grantType", "PASSWORD")
        .add("deviceId", "1234567890")
        .add("username", "seven")
        .add("password", "e10adc3949ba59abbe56e057f20f883e")
        .add("scope", "")
        .build();
    Request request = new Request.Builder()
        .url("http://192.168.1.126:8080/test")
        .post(formBody)
        .build();

    Response response = client.newCall(request).execute();
    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

    System.out.println(response.body().string());
  }

  public static void main(String... args) throws Exception {
    new PostForm().run();
  }
}