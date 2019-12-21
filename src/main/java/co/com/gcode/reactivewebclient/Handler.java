package co.com.gcode.reactivewebclient;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 *  Clase que contiene la logica para consultar y crear la entidad cliente consumiento un servicio rest
 *  @author Giovany Villegas
 */

@AllArgsConstructor
@Component
public class Handler {



    private final WebClient webclient;


    /**
     * Handler que consulta la entidad cliente del servicio rest pasando el parametro en el metodo GET
     * @param 'ServerRequest que contiene la peticion http'
     * @return 'Mono<ServerResponse>' devuelve la entidad dentro del body de un server response
     */
    public Mono<ServerResponse> getClient(ServerRequest request) {
        String documentId = request.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getClientFromRest(documentId), Client.class);
    }

    /**
     * Metodo que obtiene la entidad cliente a través de un servicio rest usando WebClient
     * @param documentId: documento de identidad del cliente
     * @return Mono<Client>
     */
    private Mono<Client> getClientFromRest(String documentId){
        return webclient.get()
                .uri(Constants.ROUTE_CLIENT_GET, documentId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Client.class);
    }

    /**
     * Hanlder que consume el servicio rest de almacenamiento mediante un body en eviado por POST
     * @param 'serverRequest que contiene la peticion http'
     * @return 'Mono<ServerResponse>' devuelve un valor booleano indicado si el guardado fue exitoso o fallido
     */
    public Mono<ServerResponse> setClient(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(saveClient(Mono.just(Client.builder()
                        .documentId("8281377")
                        .name("Jaime Villegas")
                        .mdmKey("0987654321")
                        .documentType("CC")
                        .build())), Client.class);

    }


    /**
     * Metodo que obtiene la entidad y la envia al servicio rest usando web client para que esta sea almacenada
     * @param client Entidad cliente
     * @return client Entidad cliente almacenada
     */
    private Mono<Client> saveClient(Mono<Client> client){
        return webclient.post()
                .uri(Constants.ROUTE_CLIENT_CREATE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(client, Client.class)
                .retrieve()
                .bodyToMono(Client.class);
    }
}
