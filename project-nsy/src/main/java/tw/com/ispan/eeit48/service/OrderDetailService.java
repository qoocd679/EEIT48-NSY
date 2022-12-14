package tw.com.ispan.eeit48.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.apache.catalina.Contained;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aj.org.objectweb.asm.Type;
import tw.com.ispan.eeit48.domain.AccountsBean;
import tw.com.ispan.eeit48.domain.CompanyFollowingListBean;
import tw.com.ispan.eeit48.domain.OrderDetailsBean;
import tw.com.ispan.eeit48.domain.OrderStatusBean;
import tw.com.ispan.eeit48.domain.OrdersBean;
import tw.com.ispan.eeit48.domain.ProductBean;
import tw.com.ispan.eeit48.domain.SortComparator;
import tw.com.ispan.eeit48.domain.SupplierProductForOwnerProductBean;
import tw.com.ispan.eeit48.domain.View_product_order_orderdetailsBean;
import tw.com.ispan.eeit48.domain.Email;
import tw.com.ispan.eeit48.repository.AccountsRepository;
import tw.com.ispan.eeit48.repository.OrderDetailsRepositrory;
import tw.com.ispan.eeit48.repository.OrdersRepository;
import tw.com.ispan.eeit48.repository.ProductRepository;
import tw.com.ispan.eeit48.repository.SupplierProductForOwnerProductRepository;
import tw.com.ispan.eeit48.repository.View_product_order_orderdetailsRepository;

@Service
@Transactional
public class OrderDetailService {
	@Autowired
	private View_product_order_orderdetailsRepository view_product_order_orderdetailsRepository;
	@Autowired
	private AccountsRepository accountsRepository;
	@Autowired
	private OrdersRepository ordersReposirory;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductService productService;
	@Autowired
	private SupplierProductForOwnerProductRepository supplierProductForOwnerProductRepository;
	@Autowired
	private OrderDetailsRepositrory orderDetailsRepositrory;
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private  SystemNoticeMessageService messageService;
	
	
	
	JSONArray lista = new JSONArray();
	String[] productnamespec;
	int[] orderstatus;
	int[] productid;
	String[] orderid;
	int[] orderqty;
	int[] unitsellprice;
	int[] buyerid;
	int[] unitcost;
	int[] total;
	int[] profit;
	int[] stockqty;
	String[] ordertime;
	String[] acceptordertime;
	String[] exporttime;
	String[] arriveordertime;
	String[] completeordertime;
	String[] cancelordertime;
	String[] companyname;
	String[] paymentterms;
	String[] address;
	String[] mobile;
	String[] taxid;
	String[] contactperson;
	JSONArray listb = new JSONArray();
	JSONArray listc = new JSONArray();
	JSONObject[] obj;
	int[] cansell; // ????????????
	int aa = 0;
	int[] inttime;

	public String ShowAll(Integer accountid) {
		listc.clear();
		lista.clear();

		List<View_product_order_orderdetailsBean> bean1 = view_product_order_orderdetailsRepository
				.findAllByOwneridAndOrderstatusBetween(accountid, 2, 7);
		// ???????????????????????????

		if (bean1 != null) {
			for (View_product_order_orderdetailsBean bean : bean1) {
				if (bean != null) {
					lista.put(bean.toJsonObject()); // ?????????????????????????????????
					
				}
			}
		}
			aa = lista.length();
			orderid = new String[aa];
			paymentterms = new String[aa];
			productnamespec = new String[aa];
			productid = new int[aa];
			orderstatus = new int[aa];
			orderqty = new int[aa];
			unitsellprice = new int[aa];
			unitcost = new int[aa];
			buyerid = new int[aa];
			companyname = new String[aa];
			acceptordertime = new String[aa];
			exporttime = new String[aa];
			arriveordertime = new String[aa];
			completeordertime = new String[aa];
			cancelordertime = new String[aa];
			ordertime = new String[aa];
			stockqty = new int[aa];
			cansell = new int[aa];
			companyname = new String[aa];
			paymentterms = new String[aa];
			address = new String[aa];
			mobile = new String[aa];
			taxid = new String[aa];
			contactperson = new String[aa];
			inttime = new int[aa];
			obj = new JSONObject[lista.length()]; // ??????obj??????

			for (int i = 0; i < lista.length(); i++) {
				obj[i] = new JSONObject();
			}
			System.out.println("orderid?????????" + orderid);
			for (int i = 0; i < lista.length(); i++) {
				// ??????????????????????????????????????????
				paymentterms[i] = (String) lista.getJSONObject(i).get("paymentterms");
				productnamespec[i] = (String) lista.getJSONObject(i).get("productnamespec");
				productid[i] = lista.getJSONObject(i).getInt("productid");
				orderstatus[i] = lista.getJSONObject(i).getInt("orderstatus");
				orderid[i] = (String) lista.getJSONObject(i).get("orderid");
				orderqty[i] = lista.getJSONObject(i).getInt("orderqty");
				unitsellprice[i] = lista.getJSONObject(i).getInt("unitsellprice");
				unitcost[i] = lista.getJSONObject(i).getInt("unitcost");
				buyerid[i] = lista.getJSONObject(i).getInt("buyerid");
				ordertime[i] = (String) lista.getJSONObject(i).get("ordertime");
				companyname[i] = (String) lista.getJSONObject(i).get("companyname");
				acceptordertime[i] = (String) lista.getJSONObject(i).get("acceptordertime");
				exporttime[i] = (String) lista.getJSONObject(i).getString("exporttime");
				arriveordertime[i] = (String) lista.getJSONObject(i).getString("arriveordertime");
				completeordertime[i] = (String) lista.getJSONObject(i).getString("completeordertime");
				cancelordertime[i] = (String) lista.getJSONObject(i).getString("cancelordertime");
				stockqty[i] = lista.getJSONObject(i).getInt("stockqty");
				cansell[i] = productService.findStockOwnByProductId(lista.getJSONObject(i).getInt("productid"));
			}

		
		aa = lista.length();
		total = new int[aa];
		profit = new int[aa];

		for (int i = 0; i < lista.length(); i++) {
			int a = orderqty[i] * unitsellprice[i];
			total[i] = a;
			// System.out.println(total[i]); // ??????????????? ????????????(orderqty* unitsellprice)
		}
		for (int i = 0; i < lista.length(); i++) {

			int a = orderqty[i] * (unitsellprice[i] - unitcost[i]);
			profit[i] = a;
			// System.out.println(profit[i]); // ??????????????? ????????????(orderqty* unitsellprice)
		}

		for (int f = 0; f < lista.length(); f++) {

			List<AccountsBean> beana = accountsRepository.findAllByAccountid(buyerid[f]);
			if (beana != null) {
				JSONArray list = new JSONArray();
				for (AccountsBean bean : beana) {
					list.put(bean.toJsonObject());
				}
				contactperson[f] = (String) list.getJSONObject(0).get("contactperson");
				address[f] = (String) list.getJSONObject(0).get("address");
				mobile[f] = (String) list.getJSONObject(0).get("mobile");
				taxid[f] = (String) list.getJSONObject(0).get("taxid");

			}
		}
		for (int i = 0; i < lista.length(); i++) {
			obj[i].put("productid", productid[i]);
			obj[i].put("orderid", orderid[i]);
			obj[i].put("productnamespec", productnamespec[i]);
			obj[i].put("orderqty", orderqty[i]);
			obj[i].put("unitsellprice", unitsellprice[i]);
			obj[i].put("total", total[i]);
			obj[i].put("orderstatus", orderstatus[i]);
			obj[i].put("ordertime", ordertime[i]);
			obj[i].put("acceptordertime", acceptordertime[i]);
			obj[i].put("exporttime", exporttime[i]);
			obj[i].put("arriveordertime", arriveordertime[i]);
			obj[i].put("completeordertime", completeordertime[i]);
			obj[i].put("contactperson", contactperson[i]);
			obj[i].put("address", address[i]);
			obj[i].put("companyname", companyname[i]);
			obj[i].put("profit", profit[i]);
			obj[i].put("mobile", mobile[i]);
			obj[i].put("paymentterms", paymentterms[i]);
			obj[i].put("taxid", taxid[i]);
			obj[i].put("cancelordertime", cancelordertime[i]);
			obj[i].put("buyerid", buyerid[i]);
			obj[i].put("cansell", cansell[i]);
			int orderDateSerial = (int) Long.parseLong(ordertime[i].replace(":", "").replace("-", "").replace(" ", ""));
			obj[i].put("orderDateSerial", orderDateSerial);
			listc.put(obj[i]);
		}
		List<JSONObject> sellingOrdersJsonList = new ArrayList();
		for (int number = 0; number < listc.length(); number++) {
			sellingOrdersJsonList.add((JSONObject) listc.get(number));
		}
		listc.clear();
		// ??????????????????????????????????????????

		Collections.sort(sellingOrdersJsonList, new SortComparator("orderDateSerial", "int", "desc"));
		for (int i = 0; i < sellingOrdersJsonList.size(); i++) {
			listc.put(sellingOrdersJsonList.get(i));
		}

		return listc.toString();
	
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	public String Update(Integer accountid, String orderid, int orderstatus, int buyerid, int sellerid,
			String paymentterms, Date acceptordertime, Date exporttime, Date arriveordertime, Date completeordertime,
			Date cancelordertime, Date ordertime) {
		String companyname;
		int[] product;
		int[] producthasauto;
		int[] stockqty;
		int[] warningqty;
		int[] safeqty;
		String newOrderId = null;
		JSONObject[] obj;
		JSONArray listc = new JSONArray();
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		int e = 0;
		int f = 0;
		int g = 0;
		int h = 0;
		int productid[];
		List<OrdersBean> beans = ordersReposirory.findAllByOrderid(orderid);

		List<View_product_order_orderdetailsBean> vb = view_product_order_orderdetailsRepository
				.findAllByOrderid(orderid);

		if (beans != null) {
			JSONArray list = new JSONArray();
			for (OrdersBean bean : beans) {
				list.put(bean.toJsonObject());
			}
			int a11 = (int) list.getJSONObject(0).get("buyerid");

			List<AccountsBean> beana = accountsRepository.findAllByAccountid(a11);
			if (beana != null) {
				JSONArray lista = new JSONArray();
				for (AccountsBean bean : beana) {
					lista.put(bean.toJsonObject());
				}

				int cc = list.length();

				productid = new int[cc];
//				for(int i=0; i< list.length();i++) {
//					
//					productid[i]=(int) list.getJSONObject(0).get("productid");
//					
//				}
				String email = (String) lista.getJSONObject(0).get("email");
				companyname = (String) lista.getJSONObject(0).get("companyname");

				Email e1 = new Email();
				Date a0 = ordertime;
				Date a1 = acceptordertime;
				Date a2 = exporttime;
				Date a3 = arriveordertime;
				Date a4 = completeordertime;
				Date a5 = cancelordertime;
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				OrdersBean up = new OrdersBean();

				if (orderstatus == 3) {
					try {
						String mailText = "??????" + companyname + "?????????????????????" + orderid + "???????????????";
						e1.sendMail(email, "????????????", mailText);
						System.out.println("mision complete");
						
						messageService.saveNewMessage("????????????"+mailText+new Date().toString(), a11);
					} catch (MessagingException e11) {
						// TODO Auto-generated catch block
						e11.printStackTrace();
					}
					up.setOrderid(orderid);
					up.setOrderstatus(orderstatus);
					up.setBuyerid(buyerid);
					up.setSellerid(sellerid);
					up.setPaymentterms(paymentterms);
					up.setOrdertime(a0);
					up.setAcceptordertime(new java.util.Date());
					ordersReposirory.save(up);

					List<ProductBean> Products = productRepository.findAllByOwnerid(accountid);
					if (Products != null) {
						JSONArray list1 = new JSONArray();
						for (ProductBean Product : Products) {
							list1.put(Product.toJsonObject());
						}
						int length = list1.length();
						product = new int[length];

//						for (int i = 0; i < length; i++) {
//							product[i] = (int) list1.getJSONObject(i).get("productid");
//						}
						System.out.println("product[0]" + product[0]);

						List<ProductBean> autoProducts = productRepository.findAllByAutoorderfunctionAndOwnerid("Y",
								accountid);

						if (autoProducts != null) {
							JSONArray lista1 = new JSONArray();
							for (ProductBean Product : autoProducts) {
								lista1.put(Product.toJsonObject());
							}
							obj = new JSONObject[lista1.length()]; // ??????obj??????
							for (int i = 0; i < lista1.length(); i++) {
								obj[i] = new JSONObject();
							}
							int lengtha = lista1.length();
							producthasauto = new int[lengtha];
							stockqty = new int[lengtha];
							warningqty = new int[lengtha];
							safeqty = new int[lengtha];
							for (int z = 0; z < lista1.length(); z++) {
								producthasauto[z] = lista1.getJSONObject(z).getInt("productid");
								stockqty[z] = lista1.getJSONObject(z).getInt("stockqty");
								warningqty[z] = lista1.getJSONObject(z).getInt("warningqty");
								safeqty[z] = lista1.getJSONObject(z).getInt("safeqty");

							}

							for (int i = 0; i < lista1.length(); i++) {
								obj[i].put("producthasauto", producthasauto[i]);
								obj[i].put("stockqty", stockqty[i]);
								obj[i].put("warningqty", warningqty[i]);
								obj[i].put("safeqty", safeqty[i]);
								listc.put(obj[i]);
							}
							String newOrderId1 = ordersService.createNewBookingOrderId(accountid);				
							for (int z = 0; z < listc.length(); z++) {
								a11 = (int) listc.getJSONObject(z).get("stockqty");
								b = (int) listc.getJSONObject(z).get("warningqty");
								c = (int) listc.getJSONObject(z).get("producthasauto");
								d = (int) listc.getJSONObject(z).get("safeqty");
								f = productService.findStockOwnByProductId(c);
								g = productService.findCallshippingByProductId(c);

								h = f + g;
								System.out.println("h" + h);
								System.out.println("b" + b);
								System.out.println("c" + c);
								System.out.println("f" + f);
								if (h < b) {
									List<SupplierProductForOwnerProductBean> order = supplierProductForOwnerProductRepository
											.findAllByProductid(c);
									ProductBean ppb = productRepository.findOneByProductid(c);
									System.out.println(order.get(0).getSupplierproductid());
									System.out.println("????????????");

									OrdersBean ob = new OrdersBean();
									ob.setOrderid(newOrderId1);
									ob.setOrderstatus(2);
									ob.setPaymentterms("??????");
									ob.setOrdertime(new java.util.Date());
									ob.setBuyerid(accountid);
									ob.setSellerid(2);
									ordersReposirory.save(ob);
									OrderDetailsBean odb = new OrderDetailsBean();
									List<AccountsBean> beanc = accountsRepository.findAllByAccountid(2);
									List<AccountsBean> beanv = accountsRepository.findAllByAccountid(1);
									JSONArray listxc = new JSONArray();
									odb.setOrderid(newOrderId1);
									System.out.println("???????????????");
									System.out.println("d - h" + (d - h));
									System.out.println("d " + (d));
									System.out.println(" h" + (h));
									odb.setOrderqty((d - h));
									odb.setSellerproductid(order.get(0).getSupplierproductid());
									odb.setUnitdealprice(ppb.getUnitsellprice());
									orderDetailsRepositrory.save(odb);
									if (beanv != null) {

										for (AccountsBean bean : beanv) {
											listxc.put(bean.toJsonObject());
										}
									}
									
									if (beanc != null) {
										JSONArray listxx = new JSONArray();
										for (AccountsBean bean : beanc) {
											listxx.put(bean.toJsonObject());
										}

										try {
											 //xx????????? xc?????????
											
											
											e1.sendMail(listxx.getJSONObject(0).getString("email"), "???????????????",
													"??????" + listxc.getJSONObject(0).getString("companyname") + "?????????????????????"
															+ orderid + "?????????????????????");
											e1.sendMail(listxc.getJSONObject(0).getString("email"), "???????????????",
													"???" + listxc.getJSONObject(0).getString("companyname") + "??????????????????????????????,??????:"
															+ orderid +"???????????????"+order.get(0).getSupplierproductid()+"????????????:"+(d - h));
											
										
											messageService.saveNewMessage("????????????"+listxc.getJSONObject(0).getString("companyname") + "?????????????????????"
													+ orderid + "?????????????????????"+new Date().toString(), 2);
											
											messageService.saveNewMessage("????????????"+ "????????????"+listxx.getJSONObject(0).getString("companyname")+"??????????????????,????????????:"
													+ orderid +"???????????????"+order.get(0).getSupplierproductid()+"????????????:"+(d - h)+new Date().toString(), 2);
											System.out.println("mision complete");
										} catch (MessagingException e11) {
											// TODO Auto-generated catch block
											e11.printStackTrace();
										}
									}

								}
							}
						}
					}
				} else if (orderstatus == 4) {

					up.setOrderid(orderid);
					up.setOrderstatus(orderstatus);
					up.setBuyerid(buyerid);
					up.setSellerid(sellerid);
					up.setPaymentterms(paymentterms);
					up.setOrdertime(a0);
					up.setAcceptordertime(a1);
					up.setExporttime(new java.util.Date());
					try {
						e1.sendMail(email, "?????????????????????", "??????" + companyname + "???" + sdFormat.format(new java.util.Date())
								+ "??????????????????:" + orderid + "??????");
						System.out.println("mision complete");
						
						messageService.saveNewMessage("????????????"+"??????" + companyname + "???" + sdFormat.format(new java.util.Date())
						+ "??????????????????:" + orderid + "??????"+new Date().toString(), a11);
					} catch (MessagingException e11) {
						// TODO Auto-generated catch block
						e11.printStackTrace();
					}
					ordersReposirory.save(up);
					return new java.util.Date().toString();
				} else if (orderstatus == 5) {

					up.setOrderid(orderid);
					up.setOrderstatus(orderstatus);
					up.setBuyerid(buyerid);
					up.setSellerid(sellerid);
					up.setPaymentterms(paymentterms);
					up.setOrdertime(a0);
					up.setAcceptordertime(a1);
					up.setExporttime(a2);
					up.setArriveordertime(new java.util.Date());
					messageService.saveNewMessage("????????????"+"??????" + companyname + "???" + sdFormat.format(new java.util.Date())
					+ "??????????????????:" + orderid + "?????????????????????"+new Date().toString(), a11);
					ordersReposirory.save(up);
				} else if (orderstatus == 6) {
					int id[] = null;

					if (beans != null) {
						JSONArray listb = new JSONArray();
						for (View_product_order_orderdetailsBean bean : vb) {
							listb.put(bean.toJsonObject());
						}

						int ccc = listb.length();
						id = new int[ccc];

						for (int x = 0; x < listb.length(); x++) {
							int aaa = (int) listb.getJSONObject(x).get("stockqty");
							int bbb = (int) listb.getJSONObject(x).get("orderqty");
							id[x] = listb.getJSONObject(x).getInt("productid");

							int ddd = id[x];
							System.out.println("ddd" + ddd);
							ProductBean pb = productRepository.findOneByProductid(ddd);

							System.out.println("????????????");

							pb.setStockqty((aaa - bbb));
							productRepository.save(pb);
						}
					}
					up.setOrderid(orderid);
					up.setOrderstatus(orderstatus);
					up.setBuyerid(buyerid);
					up.setSellerid(sellerid);
					up.setPaymentterms(paymentterms);
					up.setOrdertime(a0);
					up.setAcceptordertime(a1);
					up.setExporttime(a2);
					up.setArriveordertime(a3);
					up.setCompleteordertime(new java.util.Date());
					ordersReposirory.save(up);
				} else if (orderstatus == 7) {

					up.setOrderid(orderid);
					up.setOrderstatus(orderstatus);
					up.setBuyerid(buyerid);
					up.setSellerid(sellerid);
					up.setPaymentterms(paymentterms);
					up.setOrdertime(a0);
					up.setAcceptordertime(a1);
					up.setExporttime(a2);
					up.setArriveordertime(a3);
					up.setCancelordertime(new java.util.Date());
					try {
						e1.sendMail(email, "??????????????????", "??????" + companyname + "???" + sdFormat.format(new java.util.Date())
								+ "??????????????????:" + orderid + "??????");
						System.out.println("mision complete");
						messageService.saveNewMessage("????????????"+"??????" + companyname + "???" + sdFormat.format(new java.util.Date())
						+ "??????????????????:" + orderid + "????????????"+new Date().toString(), a11);
					} catch (MessagingException e11) {
						// TODO Auto-generated catch block
						e11.printStackTrace();
					}
					ordersReposirory.save(up);
				}

			}
		}
		return "OK";

	}
}
