package tw.com.ispan.eeit48.ducktest;

import java.util.List;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.eeit48.domain.ProductBean;
import tw.com.ispan.eeit48.domain.SupplierProductForOwnerProductBean;
import tw.com.ispan.eeit48.repository.SupplierProductForOwnerProductRepository;

@SpringBootTest
public class SupplierProductForOwnerProductTest {
	@Autowired
	SupplierProductForOwnerProductRepository supplierProductForOwnerProductRepository;
	@Test
	public void findAll() {
		List<SupplierProductForOwnerProductBean> beans = supplierProductForOwnerProductRepository.findAll();
		if(beans!=null) {
			
			JSONArray list = new JSONArray();
			for(SupplierProductForOwnerProductBean bean:beans) {
		    	list.put(bean.toJsonObject());
		    }
			 System.out.println(list.toString());				
	}
}
		
		
		
	}
