package co.com.gcode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;



@Configuration
public class RestClientConfig {

    @Value("${rest.endpoint}")
    private String url;

    @Value("${rest.uri.mdm.get}")
    private String uriMdm;

    @Value("${rest.uri.mdm.create}")
    private String uriMdmCreate;


    @Bean
    public String path(){
        return uriMdm;
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .defaultHeader(HttpHeaders.ACCEPT, "application/json")
                .build();
    }

}

