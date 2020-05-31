package library.CRUD.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import library.CRUD.model.enumeration.BookStatus;
import library.CRUD.model.enumeration.Genre;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import static javax.persistence.CascadeType.ALL;

@Schema(description= "Book from book store" )

@Validated
@javax.annotation.Generated(value="io.swagger.codegen.languages.SpringCodegen", date ="2020-05-23T08:49:26.978Z")
@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class Book extends GenericItem{

    @Schema(description = "Unique identifier created on provider side (e.g. Order Capture system)", example = "ef799621-ecc7-4559-92f9-14a84d6546e1")
    @JsonProperty("id")
    @EqualsAndHashCode.Include
    @Id
    @Size(max = 150, message = "Book id exceeded the maximum number of 150 characters")
    private String id = null;

    @Schema(description = "Customer who has rented book")
    @JsonProperty("customer")
    @Valid
    @OneToOne(cascade = ALL, orphanRemoval = true)
    @EqualsAndHashCode.Include
    private Customer customer;

    @Schema(description = "Author of the book")
    @JsonProperty("author")
    @Valid
    @OneToOne(cascade = ALL, orphanRemoval = true)
    @EqualsAndHashCode.Include
    private Author author;

    @Schema(description = "Title for book")
    @JsonProperty("title")
    @EqualsAndHashCode.Include
    @Size(max = 255, message = "Book title exceeded the maximum number of 255 characters.")
    private String title;

    @Schema(description = "Status of the book.", allowableValues = BookStatus.ALLOWABLE_VALUES)
    @JsonProperty("status")
    @EqualsAndHashCode.Include
    private BookStatus status;

    @Schema(description = "Genre of the book.", allowableValues = BookStatus.ALLOWABLE_VALUES)
    @JsonProperty("genre")
    @EqualsAndHashCode.Include
    private Genre genre;

    @Schema(description = "Hyperlink to access the shopping cart. URL pattern required", example = "http://localhost:8080/shoppingCart/ef799621-ecc7-4559-92f9-14a84d6546e1")
    @Column(length = 512)
    @JsonProperty("href")
    @EqualsAndHashCode.Include
    @Size(max = 512, message = "ShoppingCart href exceeded the maximum number of 512 characters")
    @URL
    private String href = null;

}
