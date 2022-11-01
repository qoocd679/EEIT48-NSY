package tw.com.ispan.eeit48.ducktest.ProductServiceTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tw.com.ispan.eeit48.domain.AccountsBean;
import tw.com.ispan.eeit48.service.AccountsService;

@SpringBootTest
public class AccountsServiceTests {
	@Autowired
	private AccountsService accountsService;

	@Test
	public void testLogin() {
		AccountsBean account = accountsService.login("ericWang", "eric1234");
		System.out.println("selects" + account);
	}

}
