package za.co.burgerfatty.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Table(name = "product")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, insertable=false, updatable=false)
    private ProductCategory category;

    @Column(name = "sku")
    private String sku;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "active")
    private String active;

    @Column(name = "units_in_stock")
    private int unitsInStock;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "last_updated")
    private Date lastUpdated;

    @Column(name = "category_id")
    private int categoryId;
}
