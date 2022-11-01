package tw.com.ispan.eeit48.ericTests.controllerDemo;
//package tw.com.ispan.eeit48.controller.demo;
//
//import java.net.URI;
//import java.util.HashMap;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import tw.com.ispan.eeit48.domain.ProductBean;
//import tw.com.ispan.eeit48.service.ProductService;
//
//@RestController
//@RequestMapping(path = {"/api/products"})
//public class ProductApiController {
//	
//	@Autowired
//	private ProductService productService;
//	
//	@GetMapping("/")
//	public ResponseEntity<List<ProductBean>> findAll() {
//		List<ProductBean> result = productService.select(null);
//		return ResponseEntity.ok(result);
//	}
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<?> findByPrimaryKey(@PathVariable("id") Integer id) {
//		ProductBean bean = new ProductBean();
//		bean.setId(id);
//		List<ProductBean> result = productService.select(bean);
//		if(result!=null && !result.isEmpty()) {
//			return ResponseEntity.ok(result.get(0));
//		}else {
//			return ResponseEntity.notFound().build();
//		}	
//	}
//	
//	@PostMapping("/")
//	public ResponseEntity<?> create(@RequestBody ProductBean bean) {
//		ProductBean result = productService.insert(bean);
//		if(result!=null) {
//			URI uri = URI.create("/api/products"+result.getId());
//			return ResponseEntity.created(uri).body(result);
//		}else {
//			return ResponseEntity.noContent().build();
//		}
//	}
//	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> removeByPrimaryKey(@PathVariable("id") Integer id) {
//		ProductBean bean = new ProductBean();
//		bean.setId(id);
//		boolean result = productService.delete(bean);
//		if(result) {
//			return ResponseEntity.noContent().build();
//		}else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<?> updateByPrimaryKey(@PathVariable("id") Integer id, 
//			@RequestBody ProductBean bean) {
//		ProductBean result = productService.update(bean);
//		if(result!=null) {
//			return ResponseEntity.ok(result);
//		}else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//	
//	// 大專測試
//	@PostMapping("/nsy")
//	public void login(@RequestBody ProductBean bean) {
//		System.out.println(bean.getName());
//		System.out.println(bean.getPrice());
//	}
//	
//}
