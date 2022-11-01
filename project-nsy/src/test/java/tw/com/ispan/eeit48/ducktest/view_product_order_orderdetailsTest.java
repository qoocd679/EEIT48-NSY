package tw.com.ispan.eeit48.ducktest;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.eeit48.domain.View_product_order_orderdetailsBean;
import tw.com.ispan.eeit48.repository.View_companyfollowinglist_accountsRepository;
import tw.com.ispan.eeit48.repository.View_product_order_orderdetailsRepository;

@SpringBootTest
public class view_product_order_orderdetailsTest {
	@Autowired
	View_companyfollowinglist_accountsRepository view_companyfollowinglist_accountsRepository;
	@Autowired
	View_product_order_orderdetailsRepository view_product_order_orderdetailsRepository;

//	@Test
//
//	public void findAll() throws Exception{
//		//以訂單編號的方法找到訂單(JSON)
////		List<Integer> idList = Arrays.asList(5);//(5)之後要能被選擇
//		
////		List<duckBean> beans = duckrepository.findAll();
//		Iterable<view_product_order_orderdetailsBean> beans = order_orderdetailsRepository.findAll();
//		if(beans!=null) {
//			
//			JSONArray list = new JSONArray();
//			for(view_product_order_orderdetailsBean bean:beans) {
//		    	list.put(bean.toJsonObject());
//		    }
//	//		JSONObject	 job01 = list.getJSONObject(0);
//			System.out.println(list);
//							
//	}
//}
	@Test
	public void findfindAllByProductid() throws Exception {
		// 以商品id的方法找到可出現貨
		Iterable<View_product_order_orderdetailsBean> beans = view_product_order_orderdetailsRepository
				.findAllByProductidAndOrderstatusBetween(100001, 2, 4);// (100001)之後要能被選擇
		int a = 0; // 裝所有被訂購的數量
		int origin = 0; // 一開始的庫存量
		int last = 0; // 最後的結果
		if (beans != null) {

			JSONArray lista = new JSONArray();
			for (View_product_order_orderdetailsBean bean : beans) {
				lista.put(bean.toJsonObject());
			}
			// System.out.println(lista);
			for (int i = 0; i < lista.length(); i++) {
				// System.out.println(lista.getJSONObject(i).getInt("orderqty"));
				int b = lista.getJSONObject(i).getInt("orderqty");
				a = a + b;
			}
			origin = lista.getJSONObject(0).getInt("stockqty");
			last = origin - a;
			System.out.println(last);
		}
	}

//	@Test
//public void findfindAllByProductid() throws Exception{
//	//大前提 預設立場商品id(100001)永遠捕獲的商品都是(200001)
//	//以商品id的方法找到已經訂購的量
//	Iterable<view_product_order_orderdetailsBean> beans = order_orderdetailsRepository.findAllByProductid(200002);//(200001)之後要能被選擇
//	int a=0;  //裝所有訂購的數量
//
//	if(beans!=null) {
//	
//		JSONArray lista = new JSONArray();
//		for(view_product_order_orderdetailsBean bean:beans) {
//	    	lista.put(bean.toJsonObject());
//	    }
//		//System.out.println(lista);
//		for(int i = 0;i<lista.length();i++) {				
//			//System.out.println(lista.getJSONObject(i).getInt("orderqty")); 	
//int b=lista.getJSONObject(i).getInt("orderqty");
//a=a+b;
//		}
//	
//		System.out.println(a);				
//}
//	
//	}
//	@Test
//public void findfindAllByProductid() throws Exception{
//	
//	//以商品id的方法找到安全庫存的量
//	Iterable<view_product_order_orderdetailsBean> beans = order_orderdetailsRepository.findAllByProductid(100001);//(100001)之後要能被選擇
//	int a=0;  //裝所有訂購的數量	
//	if(beans!=null) {		
//		JSONArray lista = new JSONArray();
//		for(view_product_order_orderdetailsBean bean:beans) {
//	    	lista.put(bean.toJsonObject());
//	    }
//		for(int i = 0;i<lista.length();i++) {									
// a=lista.getJSONObject(i).getInt("safeqty");
//		}
//		System.out.println(a);				
//}
//}
//	@Test
//public void findAllByProductidBetween2And4() throws Exception{
//	//以商品id的方法找到已定未出現貨
//	Iterable<view_product_order_orderdetailsBean> beans = order_orderdetailsRepository.findAllByProductidAndOrderstatusBetween(100001,2,4);//(100001)之後要能被選擇
//	int a=0;  //裝所有被訂購的數量
//	int origin=0;  //一開始的庫存量
//	int last=0; //最後的結果
//	if(beans!=null) {
//	
//		JSONArray lista = new JSONArray();
//		for(view_product_order_orderdetailsBean bean:beans) {
//	    	lista.put(bean.toJsonObject());
//	    }
//		//System.out.println(lista);
//		for(int i = 0;i<lista.length();i++) {				
//			//System.out.println(lista.getJSONObject(i).getInt("orderqty")); 	
//int b=lista.getJSONObject(i).getInt("orderqty");
//a=a+b;
//		}
//		origin=lista.getJSONObject(0).getInt("stockqty");
//		last=origin-a;
//		System.out.println(lista);				
//}
}
