package okhttpclientuse;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


public class BuildOrder {

	public static void main(String[] args) throws Exception {
		OkHttpClient client = new OkHttpClient();
		String url = "http://192.168.1.36:8080/zy/api/v1/users/DFD1FB256B0FFEDCC2FD18CD78E74785/orders";
		MediaType contentType = MediaType.parse("");
		String content = "";
		RequestBody body = new FormBody.Builder()
				.addEncoded("consulationContents", "咨询内容")
				.addEncoded("consulationElaborate", "咨询详情")
				.addEncoded("customerAge", "30")
				.addEncoded("customerGender", "MALE")
				.addEncoded("customerId", "")
				.addEncoded("customerInfoType", "TEXT,")
				.addEncoded("customerMobile", "12345634569")
				.addEncoded("customerName", "客户姓名")
				.addEncoded("items", "").addEncoded("orderImageUrl", "").build();
		Request request = new Request.Builder().url(url).post(body).build();
		client.newCall(request);
	}
}
