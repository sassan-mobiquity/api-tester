package com.mobiquity.assignment;


import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;


public class RestClientBase {
    public static final String usersEndpoint = "/users/";
    public static final String postsEndpoint = "/posts/";
    public static final String commentsEndpoint = "/comments/";


    public static void init(Integer httpTimeout, String typicodeURL) {
        RestAssured.baseURI = typicodeURL;
        RestAssured.config = RestAssuredConfig.config().
                sslConfig(new SSLConfig().relaxedHTTPSValidation()).
                httpClient(HttpClientConfig.httpClientConfig().
                        setParam("http.connection.timeout", httpTimeout).
                        setParam("http.socket.timeout", httpTimeout).
                        setParam("http.connection-manager.timeout", httpTimeout));
    }


}
