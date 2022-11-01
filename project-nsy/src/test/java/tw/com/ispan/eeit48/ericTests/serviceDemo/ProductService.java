package tw.com.ispan.eeit48.ericTests.serviceDemo;
//package tw.com.ispan.eeit48.service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import tw.com.ispan.eeit48.domain.ProductBean;
//import tw.com.ispan.eeit48.repository.ProductRepository;
//
//@Service
//@Transactional
//public class ProductService {
//	@Autowired
//	private ProductRepository productRespository;
//
//	public List<ProductBean> select(ProductBean bean) {
//		List<ProductBean> result = null;
//		if(bean!=null && bean.getId()!=null && !bean.getId().equals(0)) {
//			Optional<ProductBean> data = productRespository.findById(bean.getId()); 
//			if(data.isPresent()) {
//				result = new ArrayList<ProductBean>();
//				result.add(data.get());
//			}else {}
//		} else {
//			result = productRespository.findAll();
//		}
//		return result;
//	}
//	
//	public ProductBean insert(ProductBean bean) {
//		ProductBean result = null;
//		if(bean!=null && bean.getId()!=null) {
//			if(!productRespository.existsById(bean.getId())) {
//				result = productRespository.save(bean);
//			}
//		}
//		return result;
//	}
//	
//	public ProductBean update(ProductBean bean) {
//		ProductBean result = null;
//		if(bean!=null && bean.getId()!=null) {
//			if(productRespository.existsById(bean.getId())) {
//				result = productRespository.save(bean);
//			}
//		}
//		return result;
//	}
//	
//	public boolean delete(ProductBean bean) {
//		boolean result = false;
//		if(bean!=null && bean.getId()!=null) {
//			if(productRespository.existsById(bean.getId())) {
//				productRespository.deleteById(bean.getId());
//				return true;
//			}
//		}
//		return result;
//	}
//}
