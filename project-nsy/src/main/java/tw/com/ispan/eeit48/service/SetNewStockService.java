package tw.com.ispan.eeit48.service;

import java.util.List;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.ispan.eeit48.domain.ProductBean;
import tw.com.ispan.eeit48.domain.SupplierProductForOwnerProductBean;
import tw.com.ispan.eeit48.repository.ProductRepository;
import tw.com.ispan.eeit48.repository.SupplierProductForOwnerProductRepository;

@Service
@Transactional
public class SetNewStockService {
	@Autowired
	private ProductRepository productrepository;
	@Autowired
	private SupplierProductForOwnerProductRepository supplierProductForOwnerProductRepository;

	public String InsertNewstock(Integer accountid, Integer stockqty, Integer supplierid, String productdesc,
			Integer unitcost, Integer warningqty, Integer safeqty, Integer productclassification, String productpic,
			Integer onshelf, Integer minsellqty, Integer unitsellprice, String productnamespec,
			Integer autoorderconfirmfunctionstatus, String autoorderfunction, Integer reservedqty,
			Integer supplierproductid) {
		System.out.println(supplierid);
		System.out.println(stockqty);
		ProductBean result = null;
		List<ProductBean> beanofproduct = productrepository.findAllByOwnerid(accountid);
		int c = 0; // 拿來接productid
		if (beanofproduct != null) {
			JSONArray list = new JSONArray();
			for (ProductBean bean : beanofproduct) {
				list.put(bean.toJsonObject());
			}
			for (int i = 0; i < list.length(); i++) {

				int b = list.getJSONObject(i).getInt("productid");
				c = b + 1; // 自動把productid+1號
			}
			System.out.println(c);
		}
		ProductBean in = new ProductBean(); // 設定庫存資訊
		in.setProductid(c);
		in.setOwnerid(1);
		in.setStockqty(stockqty);
		in.setProductdesc(productdesc);
		in.setUnitcost(unitcost);
		in.setWarningqty(warningqty);
		in.setSafeqty(safeqty);
		in.setProductclassification(productclassification);
		// ../img/05.jpg
		in.setProductpic(productpic);
		in.setOnshelf(onshelf);
		in.setMinsellqty(minsellqty);
		in.setUnitsellprice(unitsellprice);
		in.setProductnamespec(productnamespec);
		in.setAutoorderconfirmfunctionstatus(autoorderconfirmfunctionstatus);
		in.setAutoorderfunction(autoorderfunction);
		in.setReservedqty(reservedqty);
		System.out.println(in);
		productrepository.save(in);
		
//		SupplierProductForOwnerProductBean bb = new SupplierProductForOwnerProductBean(); // 設定補貨對象
//		bb.setProductid(c);
//		bb.setSupplierid(supplierid);
//		bb.setSupplierproductid(supplierproductid);
//		supplierProductForOwnerProductRepository.save(bb);

		return "OK";
	}

	public String Updatestock(Integer accountid, Integer stockqty, Integer supplierid, String productdesc,
			Integer unitcost, Integer warningqty, Integer safeqty, Integer productclassification, String productpic,
			Integer onshelf, Integer minsellqty, Integer unitsellprice, String productnamespec,
			Integer autoorderconfirmfunctionstatus, String autoorderfunction, Integer reservedqty,
			Integer supplierproductid, Integer productid) {

		List<ProductBean> beanofproduct = productrepository.findAllByProductid(productid);
		System.out.println(beanofproduct);

		ProductBean in = new ProductBean();
		in.setProductid(productid);
		in.setOwnerid(accountid);
		in.setStockqty(stockqty);
		in.setProductdesc(productdesc);
		in.setUnitcost(unitcost);
		in.setWarningqty(warningqty);
		in.setSafeqty(safeqty);
		in.setProductclassification(productclassification);
		// ../img/05.jpg
		in.setProductpic(productpic);
		in.setOnshelf(onshelf);
		in.setMinsellqty(minsellqty);
		in.setUnitsellprice(unitsellprice);
		in.setProductnamespec(productnamespec);
		in.setAutoorderconfirmfunctionstatus(autoorderconfirmfunctionstatus);
		in.setAutoorderfunction(autoorderfunction);
		in.setReservedqty(reservedqty);
		productrepository.save(in);

		SupplierProductForOwnerProductBean bb = new SupplierProductForOwnerProductBean(); // 設定補貨對象
		bb.setProductid(productid);
		bb.setSupplierid(supplierid);
		bb.setSupplierproductid(supplierproductid);
		supplierProductForOwnerProductRepository.save(bb);

		return "OK";
	}

	public String Deletestock(Integer productid, Integer accountid) {
		List<ProductBean> beanofproduct = productrepository.findAllByProductid(productid);

		System.out.println("duckkkkkk");
		System.out.println(beanofproduct);
		SupplierProductForOwnerProductBean bb = new SupplierProductForOwnerProductBean(); // 設定補貨對象
		supplierProductForOwnerProductRepository.deleteAllByProductid(productid);
		productrepository.deleteByProductid(productid);
		return "OK";

	}

	public List<ProductBean> SelectProduct(Integer productid, Integer accountid) {

		List<ProductBean> beanofproduct = productrepository.findAllByProductid(productid);
		System.out.println(beanofproduct);
		return beanofproduct;
	}

}
