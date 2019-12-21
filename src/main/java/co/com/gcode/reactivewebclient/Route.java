package co.com.gcode.reactivewebclient;


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


    @Bean
    RouterFunction<ServerResponse> routes(Handler handler){
        return route(GET(Constants.ROUTE_CLIENT_GET).and(accept(MediaType.APPLICATION_JSON)), handler::getClient)
                .andRoute(POST(Constants.ROUTE_CLIENT_CREATE).and(accept(MediaType.APPLICATION_JSON)), handler::setClient);
    }
}
