package tw.com.ispan.eeit48.ericTests.JDBCtests;



import java.util.List;


import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.eeit48.domain.OrderDetailsBean;
import tw.com.ispan.eeit48.repository.OrderDetailsRepositrory;

@SpringBootTest
public class OrderDetailsTests {
	@Autowired
	private OrderDetailsRepositrory orderDetailsRepository;
	

	// 自動接單- 賣家收到一筆新訂單時, 搜尋該訂單所有商品是否開啟自動接單功能, 如果有一個商品沒開自動接單功能, 該單就不能自動接單, 反之所有商品都開啟自動接單, 就開始使用每個商品的自動接單功能邏輯, 檢測是否可以自動接單
	@Test
	public void orderDetailRepositoryTests() {
		List<OrderDetailsBean> beans = orderDetailsRepository.findAll();
		if(beans!=null) {
			JSONArray list = new JSONArray();
		    for(OrderDetailsBean bean:beans) {
		    	list.put(bean.toJsonObject());
		    }
		    System.out.println(list.toString());
		} else {
			System.out.println("select beans not exist");
		}	
	}
	
	
	
	//@Test
//	void autoCorfirmFunction() throws Exception {
//		String newOrder = "2D202209090001";
//		int orderQty = 0, stockQty = 0;
//		boolean autoOrder = true;
//		
//		
//		
//		try {
//			// 抓單筆訂單裡的所有產品的productId和orderQty
//			String sql = String.format("SELECT * FROM orderdetails WHERE OrderId = '%s'", newOrder); 
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
//			while (rs.next()) {
//				System.out.println(
//						"訂單" + newOrder + " 商品" + rs.getString("SellerProductId") + " 訂單數量=" + rs.getInt("OrderQty"));
//				// 搜尋所有商品的自動接單功能狀態
//				String sql2 = String.format("SELECT * FROM product WHERE ProductId = '%s'",
//						rs.getString("SellerProductId"));
//				Statement stmt2 = conn.createStatement();
//				ResultSet rs2 = stmt2.executeQuery(sql2);
//				while (rs2.next()) {
//					System.out.println(rs.getString("SellerProductId") + "自動接單功能狀態= "
//							+ rs2.getInt("AutoOrderConfirmFunctionStatus"));
//					// 檢查所有商品的自動接單功能狀態
//					if (autoOrder) {
//						// 該訂單如果所有商品都沒開啟自動叫貨, 整張訂單就不能自動叫貨
//						if (rs2.getInt("AutoOrderConfirmFunctionStatus") == 1) {
//							autoOrder = false;
//						// 依照該商品的自動交易功能狀態, 檢查是否能接單
//						// 狀態2的判斷邏輯為整筆訂單所有商品的訂單數量 > 庫存數量時, 整張訂單不能自動接單
//						} else if (rs2.getInt("AutoOrderConfirmFunctionStatus") == 2) {
//							// 搜尋庫存
//							String sql3 = String.format("SELECT * FROM product WHERE ProductId = '%s'",
//									rs.getString("SellerProductId"));
//							Statement stmt3 = conn.createStatement();
//							ResultSet rs3 = stmt3.executeQuery(sql3);
//							while (rs3.next()) {
//								System.out.println("庫存數量= " + rs3.getInt("StockQty"));
//								// 自動交易判斷
//								if (rs.getInt("OrderQty") == 0) {
//									autoOrder = false;
//									System.out.println("有商品的訂購數量為0");
//								}  else if (rs.getInt("OrderQty") > rs3.getInt("StockQty")) {
//									autoOrder = false;
//									System.out.println("超庫存訂單");
//								}
//							}
//						}
//					}
//				}
//			}
//			System.out.println(autoOrder ? "系統自動接單" : "系統人工接單");
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}
//		conn.close();
//	}

	// 廠商關注功能
	//@Test
//	void addCompanyAccount() throws Exception {
//		String userId = "1";
//		String enterAccount = "doudou";
//		ResultSet rs, rs2;
//		int rs3;
//		String newCompanyId = "";
//		// 先查這個account是否存在
//		try {
//			Connection conn = dataSource.getConnection();
//			String sql = "SELECT accountId FROM `accounts` WHERE account = ?";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, enterAccount);
//			rs = ps.executeQuery();
//			// 如果存在就檢查新廠商accountId是否存在於自己的companyFollowingList中
//			if (rs.next()) {
//				newCompanyId = rs.getString("accountid");
//				String sql2 = "SELECT * FROM `companyfollowinglist` WHERE buyerId = ? AND sellerId = ?";
//				PreparedStatement ps2 = conn.prepareStatement(sql2);
//				ps2.setString(1, userId);
//				ps2.setString(2, newCompanyId);
//				rs2 = ps2.executeQuery();
//				// 如果user已經加過這個account, 那就不能再加他, 以防別人封鎖他後還繼續騷擾
//				if (rs2.next()) {
//					System.out.println("You already added this account");
//					// 如果沒有, 就讓彼此互為買賣家
//				} else {
//					String sql3 = "INSERT INTO `companyfollowinglist` (`BuyerId`, `SellerId`) VALUES (?, ?), (?, ?)";
//					PreparedStatement ps3 = conn.prepareStatement(sql3);
//					ps3.setString(1, userId);
//					ps3.setString(2, newCompanyId);
//					ps3.setString(3, newCompanyId);
//					ps3.setString(4, userId);
//					rs3 = ps3.executeUpdate();
//					if (rs3 > 0) {
//						System.out.println("Company added successfully!");
//					} else {
//						System.out.println("There is bug inside adding company");
//					}
//				}
//			} else {
//				System.out.println("This account doesn't exist");
//			}
//		} catch (SQLException e) {
//			System.out.println(e.toString());
//		}	
//	}
//	// 依照廠商ID叫出所有產品
//	@Test
//	void selectCompanyProduct() throws Exception {
//		int choosenId = 1;
//		try {
//			Connection conn = dataSource.getConnection();
//			String sql = String.format("SELECT * FROM orderdetails WHERE OrderId = '%s'", choosenId); 
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
//		}catch (Exception e) {
//			System.out.println(e.toString());
//		}	
//		
//		
//	}
	
	// 歷史交易紀錄、收貨和取消訂單功能

}
