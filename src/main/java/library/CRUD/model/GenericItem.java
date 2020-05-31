package library.CRUD.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXISTING_PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

@Getter
@Setter
@JsonTypeInfo(
        use = NAME,
        include = EXISTING_PROPERTY,
        property = "@type")
@JsonSubTypes({
  //      @JsonSubTypes.Type(value = CartItem.class, name = "CartItem"),
//        @JsonSubTypes.Type(value = CartPrice.class, name = "CartPrice"),
//        @JsonSubTypes.Type(value = Characteristic.class, name = "Characteristic"),
//        @JsonSubTypes.Type(value = ContactMedium.class, name = "ContactMedium"),
//        @JsonSubTypes.Type(value = Place.class, name = "Place"),
//        @JsonSubTypes.Type(value = Price.class, name = "Price"),
//        @JsonSubTypes.Type(value = PriceAlteration.class, name = "PriceAlteration"),
//        @JsonSubTypes.Type(value = ShoppingCart.class, name = "ShoppingCart"),
//        @JsonSubTypes.Type(value = Channel.class, name = "Channel"),
//        @JsonSubTypes.Type(value = GeoPlace.class, name = "GeoPlace"),
        @JsonSubTypes.Type(value = Book.class, name = "Book"),
//        @JsonSubTypes.Type(value = AgreementSpecificationRef.class, name = "AgreementSpecificationRef"),
//        @JsonSubTypes.Type(value = RoleSpecification.class, name = "RoleSpecification"),
//        @JsonSubTypes.Type(value = PartyPrivacyRoleSpecification.class, name = "PartyPrivacyRoleSpecification"),
//        @JsonSubTypes.Type(value = PartyPrivacyProfileSpecification.class, name = "PartyPrivacyProfileSpecification"),
//        @JsonSubTypes.Type(value = PartyPrivacyProfileSpecificationCharacteristic.class, name = "PartyPrivacyProfileSpecificationCharacteristic"),
//        @JsonSubTypes.Type(value = SpecificationCharacteristicValue.class, name = "SpecificationCharacteristicValue"),
})
public abstract class GenericItem {

    private static final String SCHEMA_LOCATION = "http://geschaeftskunden.telekom.de/schema/shoppingCart#";
    private static final String TO_STRING_FORMAT = "%s[%s]:\n%s";

    public GenericItem() {
        this.setType(this.getClass().getSimpleName());
        this.setBaseType(this.getClass().getSimpleName());
        this.setSchemaLocation(GenericItem.SCHEMA_LOCATION);
    }

    @Schema(description = "Property is responsible for correct type inheritance mapping.")
    @JsonProperty("@type")
    protected String type;

    @Schema(description = "Generic attribute indicating the base class type of the extension" +
            " class of the current object. Useful only when the class type of the current  " +
            "object is unknown to the implementation.", example = "CartItem")
    @JsonProperty("@baseType")
    @EqualsAndHashCode.Include
    @Size(max = 150, message = "BaseType exceeded the maximum number of 150 characters")
    @Pattern(regexp = "[a-zA-Z]{0,150}$", message = "Incorrect pattern for baseType")
    protected String baseType;

    @Schema(description = "Generic attribute containing the link to the schema that defines the structure of the" +
            " class type of the current object. URL pattern required.", example = "http://geschaeftskunden.telekom.de/schema/shoppingCart#")
    @JsonProperty("@schemaLocation")
    @EqualsAndHashCode.Include
    @Size(max = 150, message = "SchemaLocation exceeded the maximum number of 150 characters")
    @URL
    protected String schemaLocation;

    @Override
    public String toString() {
        return reflectionToString(this, JSON_STYLE);
    }

}