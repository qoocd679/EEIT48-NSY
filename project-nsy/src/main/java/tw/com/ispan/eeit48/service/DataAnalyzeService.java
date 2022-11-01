package tw.com.ispan.eeit48.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

import tw.com.ispan.eeit48.domain.AccountsBean;
import tw.com.ispan.eeit48.domain.OrderDetailsBean;
import tw.com.ispan.eeit48.domain.OrdersBean;
import tw.com.ispan.eeit48.domain.ProductBean;
import tw.com.ispan.eeit48.domain.ProductClassIficationBean;
import tw.com.ispan.eeit48.domain.SortComparator;
import tw.com.ispan.eeit48.domain.View_product_order_orderdetailsBean;
import tw.com.ispan.eeit48.repository.AccountsRepository;
import tw.com.ispan.eeit48.repository.OrderDetailsRepositrory;
import tw.com.ispan.eeit48.repository.OrdersRepository;
import tw.com.ispan.eeit48.repository.ProductClassIficationRepository;
import tw.com.ispan.eeit48.repository.ProductRepository;
import tw.com.ispan.eeit48.repository.View_product_order_orderdetailsRepository;

@Service
@Transactional

public class DataAnalyzeService {
	@Autowired
	private View_product_order_orderdetailsRepository view_product_order_orderdetailsRepository;
	@Autowired
	private OrdersRepository ordersRepository;
	@Autowired
	private OrderDetailsRepositrory orderDetailsRepositrory;
	@Autowired
	private AccountsRepository accountsRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductClassIficationRepository classIficationRepository;

	JSONObject[] obj;
	JSONArray listc = new JSONArray();

	public String ShowAll(Integer accountid, String ordertime, String completeordertime) throws ParseException {
		listc.clear();
		String[] orderid;
		int[] buyerid;
		int[] productid;
		int[] productclassification;
		String[] companyname;
		String[] classname;
		int[] orderqty;
		int[] unitsellprice;
		int[] cost;
		int[] base;
		int trya = 0;
		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(ordertime);
		Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(completeordertime);
		List<OrdersBean> ob = ordersRepository
				.findAllBySelleridAndOrdertimeBetweenAndCompleteordertimeBetween(accountid, date1, date2, date1, date2); // 把所有完成的訂單放進去
		JSONArray lista = new JSONArray();
		if (ob != null) {

			for (OrdersBean bean : ob) {
				if (bean != null) {
					lista.put(bean.toJsonObject());
				}
			}

		}
		int length = lista.length();
		orderid = new String[length];
		for (int i = 0; i < lista.length(); i++) {
			orderid[i] = (String) lista.getJSONObject(i).get("orderid");
			// buyerid[i] = (int) lista.getJSONObject(i).get("buyerid");
		}
		JSONArray listb = new JSONArray();
		listb.clear();
		for (int b = 0; b < lista.length(); b++) {
			List<OrderDetailsBean> od = orderDetailsRepositrory.findAllByOrderid(orderid[b]); // 找到所有訂單的細項
			if (ob != null) {
				for (OrderDetailsBean bean : od) {
					if (bean != null) {
						listb.put(bean.toJsonObject());
					}
				}

			}
		}
		int b = listb.length();
		productid = new int[b];
		productclassification = new int[b];
		buyerid = new int[b];
		companyname = new String[b];
		classname = new String[b];
		obj = new JSONObject[b];
		orderqty = new int[b];
		unitsellprice = new int[b];
		cost = new int[b];
		base = new int[b];
		for (int s = 0; s < listb.length(); s++) {
			productid[s] = (int) listb.getJSONObject(s).get("sellerproductid");
		}

		JSONArray listd = new JSONArray();
		listd.clear();
		for (int i = 0; i < listb.length(); i++) {

			List<View_product_order_orderdetailsBean> vb = view_product_order_orderdetailsRepository
					.findAllByProductid(productid[i]); // 找到商品的資料
			if (vb != null) {
				for (View_product_order_orderdetailsBean bean : vb) {
					if (bean != null) {
						listd.put(bean.toJsonObject());
					}
				}

			}

		}

		for (int i = 0; i < listb.length(); i++) {
			buyerid[i] = (int) listd.getJSONObject(i).get("buyerid");
			orderqty[i] = (int) listd.getJSONObject(i).get("orderqty");
		}

		JSONArray liste = new JSONArray();
		liste.clear();

		for (int i = 0; i < listb.length(); i++) {
			List<AccountsBean> oa = accountsRepository.findAllByAccountid(buyerid[i]); // 找到買家的資料
			if (ob != null) {
				for (AccountsBean bean : oa) {
					if (bean != null) {
						liste.put(bean.toJsonObject());
					}
				}
			}
		}

		for (int i = 0; i < listb.length(); i++) {
			companyname[i] = (String) liste.getJSONObject(i).get("companyname");
		}

		JSONArray listf = new JSONArray();
		listf.clear();
		for (int v = 0; v < listb.length(); v++) {
			List<ProductBean> op = productRepository.findAllByProductid(productid[v]); // 找到商品的資料
			if (ob != null) {
				for (ProductBean bean : op) {
					if (bean != null) {
						listf.put(bean.toJsonObject());
					}
				}

			}

		}
	
		for (int v = 0; v < listb.length(); v++) {
			productclassification[v] = (int) listf.getJSONObject(v).get("productclassification");
			unitsellprice[v] = (int) listf.getJSONObject(v).get("unitsellprice");
			cost[v] = (int) listf.getJSONObject(v).get("unitcost");
		}

for (int v = 0; v < listb.length(); v++) {
	System.out.println("productid[v]"+productid[v]);
	System.out.println("unitsellprice[v]"+unitsellprice[v]);
	System.out.println("cost[v]"+cost[v]);
	
	
		}
		
		
		
		
		JSONArray listg = new JSONArray();
		listg.clear();
		for (int z = 0; z < listb.length(); z++) {
			List<ProductClassIficationBean> oc = classIficationRepository.findAllByClassid(productclassification[z]); // 找到所有訂單的細項
			if (oc != null) {
				for (ProductClassIficationBean bean : oc) {
					if (bean != null) {
						listg.put(bean.toJsonObject());
					}
				}

			}
		}
		for (int v = 0; v < listb.length(); v++) {
			classname[v] = (String) listg.getJSONObject(v).get("classdesc");
		}
		for (int i = 0; i < listb.length(); i++) {
			obj[i] = new JSONObject();
		}

		
		for (int i = 0; i < listb.length(); i++) {
			int a=unitsellprice[i];
			int b1=cost[i];
			unitsellprice[i]=a-b1;
		}	
		
		for (int i = 0; i < listb.length(); i++) {
			obj[i].put("buyerid", buyerid[i]);
			obj[i].put("classname", classname[i]);
			obj[i].put("orderqty", orderqty[i]);
			obj[i].put("unitsellprice", unitsellprice[i]);
			obj[i].put("productclassification", productclassification[i]);
			obj[i].put("companyname", companyname[i]);
			listc.put(obj[i]);
		}
//		List<JSONObject> sellingOrdersJsonList = new ArrayList();
//		for (int number = 0; number < listb.length(); number++) {
//			sellingOrdersJsonList.add((JSONObject) listc.get(number));
//		}
//		listc.clear();
//		// 叫貨單按訂購時間由新至舊排列
//
//		Collections.sort(sellingOrdersJsonList, new SortComparator("buyerid", "int", "asc"));
//		for (int i = 0; i < sellingOrdersJsonList.size(); i++) {
//			listc.put(sellingOrdersJsonList.get(i));
//		}

		// trya=(int) listc.getJSONObject(0).get("orderqty");
		// System.out.println(trya);

		// int a=(int)listc.getJSONObject(0).get("orderqty");
//		for(int i=1;i<(listc.length()-1);i++) {
//		if(	listc.getJSONObject(0).get("buyerid")==listc.getJSONObject(i).get("buyerid")) {
//			
//			if(	listc.getJSONObject(0).get("productclassification")==listc.getJSONObject(i).get("productclassification")) {
//				 a=a+(int)listc.getJSONObject(1).get("orderqty");		
//				listc.remove(i);
//				obj[0].put("orderqty", a);
//			listc.put(0, obj[0]);
//			}
//			
//		}
//		}	

		System.out.println(trya);
		System.out.println("listc" + listc);
		return listc.toString();

	}

}
