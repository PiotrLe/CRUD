package library.CRUD.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import library.CRUD.model.enumeration.BookStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Date;

@Validated
@javax.annotation.Generated(value="io.swagger.codegen.languages.SpringCodegen", date ="2020-05-23T08:49:26.978Z")
@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class Person extends GenericItem{

    @Schema(description = "Unique identifier created on provider side (e.g. Order Capture system)", example = "ef799621-ecc7-4559-92f9-14a84d6546e1")
    @JsonProperty("id")
    @EqualsAndHashCode.Include
    @Id
    @Size(max = 150, message = "Person id exceeded the maximum number of 150 characters")
    private String id = null;

    @Schema(description = "First name of person")
    @JsonProperty("firstName")
    @EqualsAndHashCode.Include
    @Size(max = 255, message = "Person firstName exceeded the maximum number of 255 characters.")
    private String firstName;

    @Schema(description = "Surname of the exact person")
    @JsonProperty("surname")
    @EqualsAndHashCode.Include
    @Size(max = 255, message = "Person surname exceeded the maximum number of 255 characters.")
    private String surname;

    @Schema(description = "Date of birth")
    @JsonProperty("birthDate")
    @EqualsAndHashCode.Include
    private Date birthDate;
}
