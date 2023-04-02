package paf.revision.jdbctutorial.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {
    private String supplierIds;
    private int id;
    private String productCode;
    private String productName;
    private String description;
    private float standardCost;
    private float listPrice;
    private int reorderLevel;
    private int targetLevel;
    private String quantityPerUnit;
    private int discontinued;
    private int minimumReorderQuantity;
    private String category;
    
}
