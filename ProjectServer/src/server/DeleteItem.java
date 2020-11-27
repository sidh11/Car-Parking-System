package server;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class DeleteItem {

	public int deleteCarinfo(String id) throws IOException {
		final HttpDelete httpdelete = new HttpDelete(
				"http://localhost:8080//ProjectServer/server/ParkingService/" + id);
		HttpResponse response = null;
		try {
		httpdelete.setHeader("Accept","application/xml");
			CloseableHttpClient HttpClient = HttpClients.createDefault();
			response = HttpClient.execute(httpdelete);
			System.out.println(response.toString());
			System.out.println("Deleted sucessfully");
		} finally {
			if (response != null) {
			}
		}
		return 0;

	}

}
