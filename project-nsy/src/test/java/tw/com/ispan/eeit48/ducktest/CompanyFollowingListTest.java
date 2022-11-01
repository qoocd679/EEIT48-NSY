package tw.com.ispan.eeit48.ducktest;

import java.util.List;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tw.com.ispan.eeit48.domain.CompanyFollowingListBean;
import tw.com.ispan.eeit48.domain.SystemNoticeMessageBean;
import tw.com.ispan.eeit48.repository.CompanyFollowingListRepository;

@SpringBootTest
public class CompanyFollowingListTest {

	@Autowired
	CompanyFollowingListRepository companyFollowingListRepository;

//	@Test
//	public void findAll() {
//		//找全部留言的方法(JSON)
//		List<CompanyFollowingListBean> beans = companyFollowingListReposutory.findAll();
//		if(beans!=null) {
//			
//			JSONArray list = new JSONArray();
//			for(CompanyFollowingListBean bean:beans) {
//		    	list.put(bean.toJsonObject());
//		    }
//			 System.out.println(list.toString());				
//	}
//}
	@Test
	public void findAllByAccountId() {
		// 找全部留言的方法(JSON)
		List<CompanyFollowingListBean> beans = companyFollowingListRepository.findAllByBuyerid(1);
		if (beans != null) {

			JSONArray list = new JSONArray();
			for (CompanyFollowingListBean bean : beans) {
				list.put(bean.toJsonObject());
			}
			System.out.println(list.toString());
		}
	}

}
