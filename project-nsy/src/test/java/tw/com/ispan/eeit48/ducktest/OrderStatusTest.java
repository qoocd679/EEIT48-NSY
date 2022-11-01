package tw.com.ispan.eeit48.ducktest;

import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.eeit48.domain.OrderStatusBean;
import tw.com.ispan.eeit48.repository.OrderStatusRepository;

@SpringBootTest
public class OrderStatusTest {
	@Autowired
	OrderStatusRepository orderStatusRepository;
	
	@Test
	public void findOneOrderByOrderId() {
		//以訂單編號的方法找到訂單(JSON)
		List<Integer> idList = Arrays.asList(5);//(5)之後要能被選擇
		List<OrderStatusBean> beans = orderStatusRepository.findAllById(idList);
		if(beans!=null) {
			
			JSONArray list = new JSONArray();
			for(OrderStatusBean bean:beans) {
		    	list.put(bean.toJsonObject());
		    }
			 System.out.println(list.toString());				
	}
}
//	@Test
//	public void findAll() {
//		//以訂單編號的方法找到訂單(JSON)
//		
//		List<OrderStatusBean> beans = orderStatusRepository.findAll();
//		if(beans!=null) {
//			
//			JSONArray list = new JSONArray();
//			for(OrderStatusBean bean:beans) {
//		    	list.put(bean.toJsonObject());
//		    }
//			 System.out.println(list.toString());				
//	}
//}
}
