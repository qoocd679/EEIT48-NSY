package tw.com.ispan.eeit48.ducktest;

import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.eeit48.domain.OrderDetailsBean;
import tw.com.ispan.eeit48.domain.OrderStatusBean;
import tw.com.ispan.eeit48.repository.OrderDetailsRepositrory;
import tw.com.ispan.eeit48.repository.OrderStatusRepository;

@SpringBootTest
public class OrderDetailsTest {
	@Autowired
	OrderDetailsRepositrory  orderDetailsRepositrory;
	
	@Test
	public void findOneOrderByOrderId() {
		//以訂單編號的方法找到所有此筆訂單的商品(JSON)

		List<OrderDetailsBean> beans = orderDetailsRepositrory.findAllByOrderid ("2D202209090001");//("2D202209090001")之後要能被選擇
		if(beans!=null) {
			
			JSONArray list = new JSONArray();
			for(OrderDetailsBean bean:beans) {
		    	list.put(bean.toJsonObject());
		    }
			 System.out.println(list.toString());				
	}
}
}
