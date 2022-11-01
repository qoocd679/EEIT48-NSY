//package tw.com.ispan.eeit48.ProductServiceTests;
//
//import java.util.Date;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import tw.com.ispan.eeit48.domain.ProductBean;
//import tw.com.ispan.eeit48.service.ProductService;
//
//@SpringBootTest
//public class ProductServiceTests {
//	@Autowired private ProductService productService;
//	//@Test
//	public void testSelect() {
//		List<ProductBean> selects = productService.select(null);
//		System.out.println("selects"+selects);
//	}
//	
//	// @Test
//	public void testInsert() {
//		ProductBean insert = new ProductBean();
//		insert.setId(101);
//		insert.setName("Eric");
//		insert.setPrice(2.1);
//		insert.setMake(new Date());
//		insert.setExpire(3);
//		ProductBean result = productService.insert(insert);
//		System.out.println("insertResult="+result);
//	}
//	
//	// @Test
//	public void testUpdate() {
//		ProductBean update = new ProductBean();
//		update.setId(101);
//		update.setName("EricWang");
//		update.setPrice(2.1);
//		update.setMake(new Date());
//		update.setExpire(3);
//		ProductBean result = productService.update(update);
//		System.out.println("updateResult="+result);
//	}
//	
//	// @Test
//	public void testDelete() {
//		ProductBean delete = new ProductBean();
//		delete.setId(101);
//		boolean result = productService.delete(delete); 
//		System.out.println("deleteResult="+result);
//	}
//	
//}
