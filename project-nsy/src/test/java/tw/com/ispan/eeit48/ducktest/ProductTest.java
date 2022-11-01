package tw.com.ispan.eeit48.ducktest;

import static org.assertj.core.api.Assertions.atIndex;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.persistence.Index;
import org.hibernate.cfg.IndexColumn;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import tw.com.ispan.eeit48.domain.OrdersBean;
import tw.com.ispan.eeit48.domain.ProductBean;
import tw.com.ispan.eeit48.repository.OrdersRepository;
import tw.com.ispan.eeit48.repository.ProductRepository;

@SpringBootTest
public class ProductTest {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	OrdersRepository ordersRepository;
	@Test
	public void findAll() {
		//找全部商品的方法(JSON)
		List<ProductBean> beans = productRepository.findAll();
		if(beans!=null) {
			
			JSONArray list = new JSONArray();
			for(ProductBean bean:beans) {
		    	list.put(bean.toJsonObject());
		    }
			 System.out.println(list.toString());				
	}
}

//	@Test
//	public void findAllById() {
//		//以商品ID的方法找到那個商品(JSON)
//		List<Integer> idList = Arrays.asList(100002);//(100002)之後要能被選擇
//		List<ProductBean> beans = productRepository.findAllById(idList);
//		if(beans!=null) {
//			
//			JSONArray list = new JSONArray();
//			for(ProductBean bean:beans) {
//		    	list.put(bean.toJsonObject());
//		    }
//			 System.out.println(list.toString());				
//	}
//}  
	
//	@Test
//	public void findAllByOwnerid() throws Exception{
//		//以Ownerid方法找尋商品(JSON)
//		JSONObject job01 = null;
//		Optional<ProductBean> beanofproduct = productRepository.findAllByOwnerid(1); //(1)之後要能被選擇
//		System.out.println("duck is good");
//		if(beanofproduct!=null) {
//			
//			JSONArray list = new JSONArray();
////			for(ProductBean beanx1:beanofproduct) {
////		    	list.put(beanx1.toJsonObject());
////		    }
////			System.out.println("duck is good");
//			
//			 job01 = list.getJSONObject(0);
//			System.out.println("duck is good");
//			// System.out.println(list.toString());				
//	}
		//先找到Sellerid再以orderstatus方法找到所有Sellerid的訂單(JSON)
//		List<OrdersBean> beanoforder =ordersReposirory.findAllBySelleridAndOrderstatus(1,1); //(1,1)之後要能被選擇 第一個是sellerid 第2個是交易狀態
//		if(beanoforder!=null) {		
//			JSONArray list = new JSONArray();
//			for(OrdersBean beanx2:beanoforder) {
//		    	list.put(beanx2.toJsonObject());
//		    }
//			System.out.println("duck is wonderful");
//			 System.out.println(list.toString());				
//	}
		
			//以Sellerid方法找到所有Sellerid的訂單(JSON)
//			List<OrdersBean> beansoforder =ordersReposirory.findAllBySellerid(1); //(1)之後要能被選擇
//			if(beansoforder!=null) {
//				JSONArray list = new JSONArray();
//				for(OrdersBean beanx2:beansoforder) {
//			    	list.put(beanx2.toJsonObject());    	
//			    }			
//					 JSONObject job02 = list.getJSONObject(0);
//					 System.out.println(job02.get("sellerid"));
//					 System.out.println("duck is great"+"is"+job02.getString("sellerid"));
//					
//					 System.out.println("要有"+job01.get("ownerid"));
//					if( job01.get("ownerid")==(job02.get("sellerid"))) {
//						System.out.println("ok");
//					}
//						 
					 
				//	 Iterator iterator =job02.keys();
//					System.out.println("duck is awesome"+list.getJSONObject(0));								
//				System.out.println("duck is wonderful");
//				 System.out.println(list.toString());				
		}
	//}
		
		


