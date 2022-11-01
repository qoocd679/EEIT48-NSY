package tw.com.ispan.eeit48.ericTests;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tw.com.ispan.eeit48.domain.OrdersBean;
import tw.com.ispan.eeit48.domain.View_companyfollowinglist_accountsBean;
import tw.com.ispan.eeit48.repository.AccountsRepository;
import tw.com.ispan.eeit48.repository.OrdersRepository;
import tw.com.ispan.eeit48.repository.View_companyfollowinglist_accountsRepository;

@SpringBootTest
public class Tests {
	@Autowired
	private View_companyfollowinglist_accountsRepository view_companyfollowinglist_accountsrepository;

	@Autowired
	private OrdersRepository ordersReposirory;

	@Autowired
	private AccountsRepository accountsRepository;

	// @Test
	public void view_companyfollowinglist_accountsRepositoryTests() {
		List<View_companyfollowinglist_accountsBean> companyfollowinglist_accountsBean = view_companyfollowinglist_accountsrepository
				.findAllByBuyerid(1);
		System.out.println(companyfollowinglist_accountsBean);
	}

	// @Test
	public void createNewBookingOrderId() {
		int userId = 2;
		int serialNumber = 1;

		// 今天日期
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
		String simpleToday = sdFormat.format(new Date());
		System.out.println("Today is= " + simpleToday);

		// 找出使用者所有訂單, 查看是否有今天日期的訂單(只能找出來比對, 因為DB裡是dateTime, 無法單純使用date做搜尋)
		List<OrdersBean> ordersBeans = ordersReposirory.findAllByBuyerid(userId);
		if (ordersBeans != null) {
			for (OrdersBean ordersBean : ordersBeans) {
				String simpleOrderDate = sdFormat.format(ordersBean.getOrdertime());
				// 如果有今天日期的訂單, 就把最新訂單的末四碼流水號叫出來+1成為最新訂單流水號
				if (simpleToday.equals(simpleOrderDate)) {
					// 訂單號碼
					System.out.println(ordersBean.getOrderid());
					// 訂單末四碼流水號
					System.out.println(ordersBean.getOrderid().substring(10, 14));
					serialNumber = Integer.valueOf(ordersBean.getOrderid().substring(10, 14)) + 1;
				} else {
					System.out.println("This order is not for today: " + ordersBean.getOrderid());
				}
			}
		} else {
			System.out.println("No order for this user");
		}
		// 建立新訂單編號
		String newOrderId = String.format("%dD%s%04d", userId, simpleToday, serialNumber);
	}

	// @Test
	public void findOrderDataByBuyerId() {
		int buyerId = 2;
		List<View_companyfollowinglist_accountsBean> orderData = view_companyfollowinglist_accountsrepository
				.findAllByBuyerid(buyerId);
		System.out.println(orderData);
	}

	// @Test
	public void findOrdersByUserId() {
		int userId = 1;
		List<OrdersBean> bookingOrders = ordersReposirory.findAllByBuyerid(userId);
		System.out.println(bookingOrders);
	}

	// @Test
	public void findCompanyNameByaccountid() {
		System.out.println(accountsRepository.findCompanynameByAccountid(1));
	}

	// @Test
	public void test() {
		System.out.println((Integer) null);
	}

}
