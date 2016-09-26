package okhttpclientuse;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PutFile {
	/*
	 * public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType
	 * .parse("text/x-markdown; charset=utf-8");
	 */
	// multipart/form-data
	public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType
			.parse("multipart/form-data");

	private final OkHttpClient client = new OkHttpClient();

	public void run() throws Exception {
		File file = new File("E:/document/test.txt");

		Request request = new Request.Builder()
				.url("http://localhost:8080/upload0")
				.put(RequestBody.create(MEDIA_TYPE_MARKDOWN, file)).build();

		Response response = client.newCall(request).execute();

		if (!response.isSuccessful())
			throw new IOException("Unexpected code " + response);

		System.out.println(response.body().string());
	}

	public static void main(String... args) throws Exception {
		new PutFile().run();
	}
}
