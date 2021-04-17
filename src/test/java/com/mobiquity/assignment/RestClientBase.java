package com.mobiquity.assignment;


import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;


public class RestClientBase {

    public static RestAssuredConfig requestConfig;
    public static String typicodeURL;
    public static final String usersEndpoint = "/users/";
    public static final String postsEndpoint = "/posts/";
    public static final String commentsEndpoint = "/comments/";


    public static void init(TestConfig props) {
        Integer httpTimeout = props.getDefaultTimeoutMilSec();
        typicodeURL = props.getTypicodeURL();
        requestConfig = RestAssuredConfig.config().
                sslConfig(new SSLConfig().relaxedHTTPSValidation()).
                httpClient(HttpClientConfig.httpClientConfig().
                        setParam("http.connection.timeout", httpTimeout).
                        setParam("http.socket.timeout", httpTimeout).
                        setParam("http.connection-manager.timeout", httpTimeout));
    }


}
