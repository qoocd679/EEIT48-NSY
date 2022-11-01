package tw.com.ispan.eeit48.ducktest;

import static org.mockito.ArgumentMatchers.anyList;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tw.com.ispan.eeit48.domain.AutoOrderConfirmFunctionStatusBean;
import tw.com.ispan.eeit48.repository.AutoOrderConfirmFunctionStatusRepository;



@SpringBootTest
public class AutoOrderConfirmFunctionStatusTest {

	@Autowired
	AutoOrderConfirmFunctionStatusRepository autoOrderConfirmFunctionStatusRepository;
	
//	@Test
//	public void ffindAll() {
//		//找全部自動接單的方法(JSON)
//		List<AutoOrderConfirmFunctionStatusBean> beans = autoOrderConfirmFunctionStatusRepository.findAll();
//		if(beans!=null) {
//			
//			JSONArray list = new JSONArray();
//			for(AutoOrderConfirmFunctionStatusBean bean:beans) {
//		    	list.put(bean.toJsonObject());
//		    }
//			 System.out.println(list.toString());				
//	}
//	}
//}
	@Test
	public void findAllById() {
		//找到單1個自動接單的方法(JSON)
		
		List<Integer> idList = Arrays.asList(3);//(3)之後要能被選擇
		List<AutoOrderConfirmFunctionStatusBean> beans = autoOrderConfirmFunctionStatusRepository.findAllById(idList);
		if(beans!=null) {
			
			JSONArray list = new JSONArray();
			for(AutoOrderConfirmFunctionStatusBean bean:beans) {
		    	list.put(bean.toJsonObject());
		    }		    
		   			 System.out.println(list);				
	}
}
	
	
	
	}
