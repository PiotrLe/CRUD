package library.CRUD.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Validated
@javax.annotation.Generated(value="io.swagger.codegen.languages.SpringCodegen", date ="2020-05-23T08:49:26.978Z")
@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class Customer extends Person {

    @Schema(description = "Unique identifier for card of customer", example = "5467")
    @JsonProperty("CardId")
    @EqualsAndHashCode.Include
    @Size(max = 150, message = "Person id exceeded the maximum number of 150 characters")
    private String cardId;
}
