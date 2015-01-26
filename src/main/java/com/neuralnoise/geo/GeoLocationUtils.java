package com.neuralnoise.geo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderGeometry;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.LatLng;
import com.google.common.collect.Lists;
import com.neuralnoise.map.model.geo.Location;

import fr.dudie.nominatim.client.JsonNominatimClient;
import fr.dudie.nominatim.model.Address;

public class GeoLocationUtils {

	private static final String PROPS_PATH = "/nominatim-client.properties";

	private GeoLocationUtils() {
	}

	public static List<Location> queryNominatim(String address, String language) throws IOException, InterruptedException {
		HttpClient httpClient = new DefaultHttpClient();

		Properties props = new Properties();
		final InputStream in = GeoLocationUtils.class.getResourceAsStream(PROPS_PATH);
		props.load(in);

		String baseUrl = props.getProperty("nominatim.server.url");
		String email = props.getProperty("nominatim.headerEmail");

		JsonNominatimClient nominatimClient = new JsonNominatimClient(httpClient, email);
		List<Address> addresses = null; //nominatimClient.search(address);

		int count = 0;
		
		while (count++ < 100 && addresses == null) {
			addresses = nominatimClient.search(address);
			if (addresses == null) {
				Thread.sleep(1000);
			}
		}
		
		List<Location> gcs = Lists.newLinkedList();

		for (Address addr : addresses) {
			String formatted = addr.getDisplayName();

			double dlat = addr.getLatitude();
			double dlong = addr.getLongitude();

			Location gc = new Location(dlat, dlong, formatted);

			gcs.add(gc);
		}

		return gcs;
	}

	public static List<Location> queryGoogle(String address, String language) throws InterruptedException {
		Geocoder geocoder = new Geocoder();
		GeocoderRequestBuilder builder = new GeocoderRequestBuilder().setAddress(address).setLanguage(language);

		GeocoderRequest request = builder.getGeocoderRequest();
		GeocodeResponse response = null; // geocoder.geocode(request);
				
		int count = 0;
		
		while (count++ < 100 && response == null) {
			response = geocoder.geocode(request);
			if (response == null) {
				Thread.sleep(1000);
			}
		}

		List<Location> gcs = Lists.newLinkedList();

		for (GeocoderResult result : response.getResults()) {
			GeocoderGeometry geometry = result.getGeometry();
			LatLng latlng = geometry.getLocation();
			String formatted = result.getFormattedAddress();

			Location gc = new Location(latlng.getLat().doubleValue(), latlng.getLng().doubleValue(), formatted);
			gcs.add(gc);
		}

		return gcs;
	}

}
