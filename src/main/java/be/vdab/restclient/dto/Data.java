package be.vdab.restclient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
//Record stelt het binnenste object voor in de JSON van https://reqres.in/api/users/1
public record Data(
        long id,
        @JsonProperty("first_name") String firstName,
        //De naamgeving conventie van Java verbiedt _ tekens in de naam van de variabele. @JsonProperty("first_name")
        //koppelt de variabele aan het attribuut first_name.
        @JsonProperty("last_name") String lastName) {
}
