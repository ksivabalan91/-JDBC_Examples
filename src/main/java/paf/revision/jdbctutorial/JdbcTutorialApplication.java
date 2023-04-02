package paf.revision.jdbctutorial;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import paf.revision.jdbctutorial.models.Orders;
import paf.revision.jdbctutorial.models.Products;
import paf.revision.jdbctutorial.repositories.NorthwindRepo;

@SpringBootApplication
public class JdbcTutorialApplication implements CommandLineRunner {

	@Autowired
	private NorthwindRepo nwRepo;
	public static void main(String[] args) {
		SpringApplication.run(JdbcTutorialApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// queryStatements();	
		// insertStatements();
		System.out.println(deleteStatements());
		System.out.println("done");

	}

	public void queryStatements(){
		//! finding onefield
		List<String> names = nwRepo.findOneField("last_name", "customers", String.class);
		System.out.println("------ Finding one field ------\n"+names.toString()+"\n");

		//! finding items with order based parameters
		List<Orders> order = nwRepo.findItemBy("ship_name,employee_id", "orders", "employee_id", "3", Orders.class);
		System.out.println("------ Partial map ------\n"+order.toString()+"\n");

		//! finding items with more conditions
		List<Products> products = nwRepo.findItems("*", "products", "category in ('Condiments','Beverages') and minimum_reorder_quantity > 0", Products.class);
		System.out.println("------ All products mapped ------");
		for(Products p: products){
			System.out.println(p.toString());
		}

		//! finding one item
		List<Products> product =  nwRepo.findItemBy("*", "products", "id", "5", Products.class);
		System.out.println("\n------ Singleitem -----\n"+product.toString());

		//! generic map
		String SQL = "select customer_id from orders";
		List<Map<String, Object>> map  =nwRepo.genericMap(SQL);

	}

	public void insertStatements(){
		//! insert new entry with partial information
		String fieldsList = "product_code,product_name,standard_cost,list_price,reorder_level,target_level ,quantity_per_unit,minimum_reorder_quantity,category";
		String valuesList = "'GEFORCE','Nvidia RTX4090',1199.99,2999.99,10,50,'1 card',1,'Electronics'";
		
		int rowsinserted = nwRepo.insert("products", fieldsList, valuesList);
		
		//! insert new entry with all information, id can be null for auto increment
		String valuesList2 = "'9',null,'GEFORCE','Nvidia RTX4090','expensive af',1199.99,2999.99,10,50,'1 card',0,1,'Electronics',null";
		rowsinserted = nwRepo.insert("products", valuesList2);
	}

	public int deleteStatements(){
		return nwRepo.deleteById("products", "category", "Electronics");
	}

}
