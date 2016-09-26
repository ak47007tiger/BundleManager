package okhttpclientuse;

import java.io.File;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public final class PostMultipart {
	/**
	 * The imgur client ID for OkHttp recipes. If you're using imgur for
	 * anything other than running these examples, please request your own
	 * client ID! https://api.imgur.com/oauth2
	 */
	private static final String IMGUR_CLIENT_ID = "9199fdef135c122";
	private static final MediaType MEDIA_TYPE_PNG = MediaType
			.parse("image/jpg");

	private final OkHttpClient client = new OkHttpClient();

	public void run() throws Exception {
		// Use the imgur image upload API as documented at
		// https://api.imgur.com/endpoints/image
		RequestBody requestBody = new MultipartBody.Builder()
				.setType(MultipartBody.FORM)
				/*
				 * .addFormDataPart("accessToken",
				 * "9D0834913500BAF783F6BE8412F2CA33")
				 */
				/*.addFormDataPart("imageFile","f.jpg",
						RequestBody.create(MEDIA_TYPE_PNG, new File("E:/document/f.jpg")))*/
				.addPart(RequestBody.create(MEDIA_TYPE_PNG, new File(
								"E:/document/f.jpg")))
				.build();

		Request request = new Request.Builder()
				.header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
				.url("http://zy.doocom.cn:9080/api/v7/users/9D0834913500BAF783F6BE8412F2CA33/orders/uploadImage")
				.post(requestBody).build();

		Response response = client.newCall(request).execute();
		if (!response.isSuccessful())
			throw new IOException("Unexpected code " + response);

		System.out.println(response.body().string());
	}

	public static void main(String... args) throws Exception {
		new PostMultipart().run();
	}
}