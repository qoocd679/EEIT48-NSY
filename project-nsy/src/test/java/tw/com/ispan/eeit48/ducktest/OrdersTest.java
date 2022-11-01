package tw.com.ispan.eeit48.ducktest;

import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tw.com.ispan.eeit48.repository.OrdersRepository;
import tw.com.ispan.eeit48.domain.OrdersBean;

@SpringBootTest
public class OrdersTest {
	@Autowired
	OrdersRepository ordersRepository;

//	@Test
//	public void findOneOrderByOrderId() {
//		//以訂單編號的方法找到訂單(JSON)
//		List<String> idList = Arrays.asList("2D202209090001");//(2D202209090001)之後要能被選擇
//		List<OrdersBean> beans = ordersReposirory.findAllById(idList);
//		if(beans!=null) {
//			
//			JSONArray list = new JSONArray();
//			for(OrdersBean bean:beans) {
//		    	list.put(bean.toJsonObject());
//		    }
//			 System.out.println(list.toString());				
//	}
//}
//	@Test
//	public void findAllBySellerid() {
//		//以Sellerid方法找到所有Sellerid的訂單(JSON)
//		List<OrdersBean> beans =ordersReposirory.findAllBySellerid(1); //(1)之後要能被選擇
//		if(beans!=null) {
//			
//			JSONArray list = new JSONArray();
//			for(OrdersBean bean:beans) {
//		    	list.put(bean.toJsonObject());
//		    }
//			System.out.println("duck is good");
//			 System.out.println(list.toString());				
//	}
//}
//	@Test
//	public void findAllByBuyerid() {
//		//以Sellerid方法找到所有Sellerid的訂單(JSON)
//		List<OrdersBean> beans =ordersReposirory.findAllByBuyerid(2); //(2)之後要能被選擇
//		if(beans!=null) {
//		
//			JSONArray list = new JSONArray();
//			for(OrdersBean bean:beans) {
//		    	list.put(bean.toJsonObject());
//		    }
//			System.out.println("duck is great");
//			 System.out.println(list.toString());				
//	}
//}
	@Test
	public void findAllBySelleridAndOrderstatus() {
		// 先找到Sellerid再以orderstatus方法找到所有Sellerid的訂單(JSON)
		List<OrdersBean> beans = ordersRepository.findAllBySelleridAndOrderstatus(1, 1); // (1,1)之後要能被選擇 第一個是sellerid
																							// 第2個是交易狀態
		if (beans != null) {

			JSONArray list = new JSONArray();
			for (OrdersBean bean : beans) {
				list.put(bean.toJsonObject());
			}
			System.out.println("duck is wonderful");
			System.out.println(list.toString());
		}

	}
}