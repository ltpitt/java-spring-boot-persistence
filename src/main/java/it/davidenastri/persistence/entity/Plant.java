package it.davidenastri.persistence.entity;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Plant {

    @Id
    @GeneratedValue
    private Long id;

    @JsonView(Views.Public.class)
    @Nationalized // Should use @Nationalized instead of @Type=nstring
    private String name;

    @JsonView(Views.Public.class)
    @Column(precision=12, scale=4)
    private BigDecimal price; // BigDecimal is the standard Java class for currency math

    // Many plants can belong to one delivery
    @ManyToOne(fetch = FetchType.LAZY) // Don't retrieve delivery if we don't need it
    @JoinColumn(name = "delivery_id")  // Map the join column in the plant table
    private Delivery delivery;

    public Plant() {
    }

    public Plant(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

}
