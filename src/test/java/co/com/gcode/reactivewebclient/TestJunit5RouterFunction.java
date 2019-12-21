package co.com.gcode.reactivewebclient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //Springboot sube un servidor en un puerto aleatorio
public class TestJunit5RouterFunction {


    @Value("${path.get.client}")
    private String getClient;

    @Value("${path.save.client}")
    private String saveClient;

    @Value("${basePath}")
    private String basePath;

    private WebTestClient webTestClient;

    @Autowired
    private RouterFunction routes;

    @LocalServerPort
    private int port; // El puerto asignado lo lleva a la variable port para ser usado en baseUrl

    @BeforeEach
    private void beforeEach(){
        System.out.println(basePath + ":" + port);
        this.webTestClient = WebTestClient
                .bindToRouterFunction(routes)
                .configureClient()
                .baseUrl("https://dry-butterfly-4551.getsandbox.com:443/")
                //.baseUrl(basePath + ":" + port)
                .build();
    }

    /**
     * Prueba que la respuesta entregada sea un http 200 y que el cuerpo tenga una entidad cliente
     */
    @Test
    void testGetClient(){
        webTestClient.get()
                .uri(getClient)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Client.class);
    }

    /**
     * Prueba que la respuesta entregada sea un http 200
     */
    @Test
    void testPostClient(){
        webTestClient.post()
                .uri(saveClient)
                .exchange()
                .expectStatus()
                .isOk();
    }


}
