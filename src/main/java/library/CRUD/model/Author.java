package library.CRUD.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import java.util.Date;

@Validated
@javax.annotation.Generated(value="io.swagger.codegen.languages.SpringCodegen", date ="2020-05-23T08:49:26.978Z")
@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class Author extends Person {

    @Schema(description = "Date of death")
    @JsonProperty("dateOfDeath")
    @EqualsAndHashCode.Include
    private Date dateOfDeath;

    @Schema(description = "Information if the author is nobelist or not")
    @JsonProperty("isNobelist")
    @EqualsAndHashCode.Include
    private Boolean isNobelist;
}
