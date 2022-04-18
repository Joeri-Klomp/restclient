package be.vdab.restclient.restclients;

import be.vdab.restclient.dto.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Optional;

@Component
//maakt van class een bean
public class ReqRestClient {
    private final WebClient client;
    private final String userURI;

    //Je injecteert een WebClient.Builder. Spring maakt altijd een WebClient.Builder bean:
    public ReqRestClient(WebClient.Builder builder, @Value("${reqres.user}") String userURI) {
        //Je injecteert de URI template (reqres.user) uit application.properties met @Value
        client = builder.build();
        this.userURI = userURI;
    }

    public Optional<User> findById(long id) {
        try {
            //Deze method leest via een GET request een user op van de website Reqres. De parameter is de id van de op te
            //halen user. Je krijgt een Optional terug. Die is leeg als de user niet gevonden werd, of bevat de user
            //als die gevonden werd.
            return Optional.of(client.get()
                    .uri(userURI, id)
                    //De eerste parameter is de URI template uit application.properties. De tweede parameter is een
                    //waarde voor de parameter id in de URI template.
                    .retrieve()
                    .bodyToMono(User.class)
                    .block());
        } catch (WebClientResponseException.NotFound e) {
            //Als de response een status code 404 (Not Found) heeft, geef je een lege Optional terug.
            return Optional.empty();
        }
    }
}
