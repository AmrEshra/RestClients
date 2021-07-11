package com.RestClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class MoviesAPI {

	@XmlRootElement(name = "Result")
	class Result {
		private int page;
		private int per_page;
		private int total;
		private int total_pages;
		private List<Movie> data;

		public Result(int page, int per_page, int total, int total_pages, List<Movie> data) {
			this.page = page;
			this.per_page = per_page;
			this.total = total;
			this.total_pages = total_pages;
			this.data = data;
		}

		public Result() {
		}

		public int getPage() {
			return page;
		}

		public int getPer_page() {
			return per_page;
		}

		public int getTotal() {
			return total;
		}

		public int getTotal_pages() {
			return total_pages;
		}

		public List<Movie> getData() {
			return data;
		}
	}

	@XmlRootElement
	public class Movie {
		private String Title;
		private int Year;
		private String imdbID;

		public String getTitle() {
			return Title;
		}

		public int getYear() {
			return Year;
		}

		public String getImdbID() {
			return imdbID;
		}

		@Override
		public String toString() {
			return "Movie [Title=" + Title + ", Year=" + Year + ", imdbID=" + imdbID + "]";
		}
	}

	public static void main(String[] args) {

//		getMovies("a");
		getMovies2("a");
	}

	public static void getMovies(String substr) {

		String response;
		int startPage = 1;
		int totalPages = Integer.MAX_VALUE;
		while (startPage <= totalPages) {
			try {
				URL obj = new URL(
						"https://jsonmock.hackerrank.com/api/movies/search/?Title=" + substr + "&page=" + startPage);
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				con.setRequestMethod("GET");
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				while ((response = in.readLine()) != null) {
					Result p = new Gson().fromJson(response, Result.class);
					totalPages = p.getTotal_pages();
					for(Movie m : p.getData())
						System.out.println(m.toString());
				}
				in.close();
				startPage++;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void getMovies2(String substr) {
		Client client = Client.create();
		Gson g = new Gson();
		
		int startPage = 1;
		int totalPages = Integer.MAX_VALUE;
		while (startPage <= totalPages) {

			String URL = "https://jsonmock.hackerrank.com/api/movies/search/?Title=" + substr + "&page=" + startPage;
			WebResource webResource = client.resource(URL);
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			Result p = g.fromJson(response.getEntity(String.class), Result.class);
			totalPages = p.getTotal_pages();
			for(Movie m : p.getData())
				System.out.println(m.toString());
			
			startPage++;
		}

	}

}
