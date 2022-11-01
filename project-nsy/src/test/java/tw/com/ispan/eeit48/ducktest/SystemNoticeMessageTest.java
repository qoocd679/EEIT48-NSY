package tw.com.ispan.eeit48.ducktest;

import java.util.List;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.eeit48.domain.SystemNoticeMessageBean;
import tw.com.ispan.eeit48.repository.SystemNoticeMessageRepository;



@SpringBootTest
public class SystemNoticeMessageTest {
	@Autowired
	SystemNoticeMessageRepository  systemNoticeMessageRepository;
	
//	@Test
//	public void findAll() {
//		//找全部留言的方法(JSON)
//		List<SystemNoticeMessageBean> beans = systemNoticeMessageRepository.findAll();
//		if(beans!=null) {
//			
//			JSONArray list = new JSONArray();
//			for(SystemNoticeMessageBean bean:beans) {
//		    	list.put(bean.toJsonObject());
//		    }
//			 System.out.println(list.toString());				
//	}
//}
//	@Test
//	public void findAllByReceiverid() {
//		//以接收id的方法找到留言(JSON)
//		List<SystemNoticeMessageBean> beans = systemNoticeMessageRepository.findAllByReceiverid(2); //(2)之後要能被改
//		if(beans!=null) {
//			
//			JSONArray list = new JSONArray();
//			for(SystemNoticeMessageBean bean:beans) {
//		    	list.put(bean.toJsonObject());
//		    }
//			 System.out.println(list.toString());				
//	}
//}
	@Test
	public void findAllByReceiverid() {
		//先以接收id的方法找到全部留言再以是否被以讀為後續尋找條件(JSON)
		List<SystemNoticeMessageBean> beans = systemNoticeMessageRepository.findAllByReceiveridAndMessageread(1,1); //(1,1)之後要能被改
		if(beans!=null) {
			
			JSONArray list = new JSONArray();
			for(SystemNoticeMessageBean bean:beans) {
		    	list.put(bean.toJsonObject());
		    }
			 System.out.println(list.toString());				
	}
}
	
}
