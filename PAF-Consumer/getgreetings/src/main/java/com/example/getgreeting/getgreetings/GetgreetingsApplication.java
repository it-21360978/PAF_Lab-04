package com.example.getgreeting.getgreetings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GetgreetingsApplication {

	//client for performing HTTP request 
	private static RestTemplate httpClient = null;

	//base url for remote calls
	private static String BaseURL = "http://localhost:8083/";


	//endpoint for remote calls
	private static String defaultGreetingURL = "greeting";
	private static String namedGreetingURL = "greeting/name?name=gihan";

	//main method
	public static void main(String[] args) {
		SpringApplication.run(GetgreetingsApplication.class, args);
		makeCalls();
	}

	// singleton pattern implemented to get a single instance of the HTTP client
	private static RestTemplate getHttpClient(){
		
		if(httpClient == null){
			httpClient = new RestTemplate();
		}
		return httpClient;
	}



	//call the default endpoint and get the response
	private static Greeting getGreeting(String url){

		RestTemplate restmp = getHttpClient();
		Greeting response = restmp.getForObject(BaseURL + "/" + url, Greeting.class);

		return response;
	}




	//call the named endpoint and get the response
	private static Greeting getGreetingByName(String url){
		
		RestTemplate restmp = getHttpClient();
		Greeting response = restmp.getForObject(BaseURL + "/" + url, Greeting.class);

		return response;
	}


	//call the endpoints,receive the responses and print them on the console
	private static void makeCalls(){

		Greeting recievedGreeting1 = GetgreetingsApplication.getGreeting(defaultGreetingURL);
		Greeting recievedGreeting2 = GetgreetingsApplication.getGreetingByName(namedGreetingURL);

		String content1 = recievedGreeting1.content();
		System.out.println(content1);

		String content2 = recievedGreeting2.content();
		System.out.println(content2);

	}
}
