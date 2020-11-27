package server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class InsertClient {

	public void insert(String name, String numb, String area) {
		try {

			URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080)
					.setPath("/ProjectServer/server/ParkingService/insertitems").build();
			System.out.println(uri.toString());
			HttpPost httpPost = new HttpPost(uri);
			httpPost.setHeader("Accept", "application/xml");
			CloseableHttpClient client = HttpClients.createDefault();

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("name", name));
			nameValuePairs.add(new BasicNameValuePair("numb", numb));
			nameValuePairs.add(new BasicNameValuePair("area", area));

			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			System.out.println("Inserted sucessfully............");
			CloseableHttpResponse response = client.execute(httpPost);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
