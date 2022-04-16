package Model;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import jakarta.json.JsonObject;

public class JsonClient {
	private HttpClient client;
	
	public JsonClient() {
		this.client = HttpClient.newHttpClient();
	}
	
	public String get(String url) throws IOException, InterruptedException {
		HttpRequest hr = HttpRequest.newBuilder()
									.uri(URI.create(url))
									.header("content-type", "application/json")
									.version(HttpClient.Version.HTTP_1_1)
									.build();
		HttpResponse<String> hresp = this.client.send(hr, BodyHandlers.ofString());
		return hresp.body();
	}
	
	public String post(String url, String json) throws IOException, InterruptedException {
		HttpRequest hr = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.POST(BodyPublishers.ofString(json))
				.header("content-type", "application/json; charset=utf-8")
				.build();
		HttpResponse<String> hresp = this.client.send(hr, BodyHandlers.ofString());
		return hresp.body();
	}
	
	public String put(String url, String json) throws IOException, InterruptedException {
		HttpRequest hr = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.PUT(BodyPublishers.ofString(json))
				.header("content-type", "application/json")
				.build();
		HttpResponse<String> hresp = this.client.send(hr, BodyHandlers.ofString());
		return hresp.body();
	}

	/*public String delete(String url) throws IOException, InterruptedException {
		HttpRequest hr = HttpRequest.newBuilder()
									.uri(URI.create(url))
									.DELETE()
									.build();
		HttpResponse<String> hresp = this.client.send(hr, BodyHandlers.ofString());
		return hresp.body();
	}*/
}
