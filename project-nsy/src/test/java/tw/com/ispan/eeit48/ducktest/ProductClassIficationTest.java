package tw.com.ispan.eeit48.ducktest;

import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.eeit48.domain.ProductBean;
import tw.com.ispan.eeit48.domain.ProductClassIficationBean;
import tw.com.ispan.eeit48.repository.ProductClassIficationRepository;


@SpringBootTest
public class ProductClassIficationTest {
	@Autowired
	ProductClassIficationRepository productClassIficationRepository;
	
//	@Test
//	public void findAll() {
//		//找全部商品類別方法(JSON)
//		List<ProductClassIficationBean> beans = productClassIficationRepository.findAll();
//		if(beans!=null) {
//			
//			JSONArray list = new JSONArray();
//			for(ProductClassIficationBean bean:beans) {
//		    	list.put(bean.toJsonObject());
//		    }
//			 System.out.println(list.toString());				
//	}
//}
	@Test
	public void findAllById() {
		//找單一商品類別的方法(JSON)
		List<Integer> idList = Arrays.asList(3);//(3)之後要能被選擇
		List<ProductClassIficationBean> beans = productClassIficationRepository.findAllById(idList);
		if(beans!=null) {		
			JSONArray list = new JSONArray();
			for(ProductClassIficationBean bean:beans) {
		    	list.put(bean.toJsonObject());
		    }
			 System.out.println(list.toString());				
	}
}
	
}
