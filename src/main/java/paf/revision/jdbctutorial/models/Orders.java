package paf.revision.jdbctutorial.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
private int id;
private int employeeId;
private int customerId;
private Date orderDate; 
private Date shippedDate; 
// private int shipperId;
private String shipName; 
private String shipAddress;
private String shipCity; 
private String shipStateProvince; 
private String shipZipPostalCode; 
private String shipCountryRegion; 
private float shippingFee;
private float taxes;
private String paymentType; 
private Date paidDate; 
private String notes;
private float taxRate;
// private int taxStatusId;
private int statusOd;
}
