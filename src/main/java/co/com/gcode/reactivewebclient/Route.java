package co.com.gcode.reactivewebclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Clase encargada de recibir las peticiones rest y redireccionarlas al correspondiente handler
 * que resolverá la peticion. Equivale al controlador en el modelo reactivo orientado a anotaciones
 * @author Giovany Villegas
 */

@Configuration
public class Route {

    private static final String ROUTE_CLIENT_CREATE = "/client";

    private static final String ROUTE_CLIENT_GET= "/client/{id}";

    @Bean
    RouterFunction<ServerResponse> routes(Handler handler){
        return route(GET(ROUTE_CLIENT_GET).and(accept(MediaType.APPLICATION_JSON)), handler::getClient)
                .andRoute(POST(ROUTE_CLIENT_CREATE).and(accept(MediaType.APPLICATION_JSON)), handler::setClient);
    }
}
