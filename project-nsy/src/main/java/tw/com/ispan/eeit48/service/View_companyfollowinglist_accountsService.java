package tw.com.ispan.eeit48.service;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.eeit48.domain.AccountsBean;
import tw.com.ispan.eeit48.domain.CompanyFollowingListBean;
import tw.com.ispan.eeit48.domain.View_companyfollowinglist_accountsBean;
import tw.com.ispan.eeit48.repository.AccountsRepository;
import tw.com.ispan.eeit48.repository.CompanyFollowingListRepository;
import tw.com.ispan.eeit48.repository.View_companyfollowinglist_accountsRepository;

@Service
@Transactional
public class View_companyfollowinglist_accountsService {
	@Autowired
	private View_companyfollowinglist_accountsRepository view_companyfollowinglist_accountRepository;
	@Autowired
	private AccountsRepository accountsRepository;
	@Autowired
	private CompanyFollowingListRepository companyFollowingListReposutory;

	public String SelectAll(Integer accontid) {
		JSONArray listc = new JSONArray();
		List<View_companyfollowinglist_accountsBean> beans = view_companyfollowinglist_accountRepository
				.findAllByBuyerid(accontid);

		if (beans != null) {
			JSONArray lista = new JSONArray();
			for (View_companyfollowinglist_accountsBean bean : beans) {
				if (bean != null) {
					lista.put(bean.toJsonObject()); // 把所有關聯場商找出來
				}
			}

			int[] sellerid = new int[10]; // 把各欄位的值單獨放在各自陣列
			String[] companyname = new String[10];
			String[] mobile = new String[10];
			String[] taxid = new String[10];
			String[] address = new String[10];
			String[] email = new String[10];
			String[] contactperson = new String[10];
			String[] contactpersonnum = new String[10];
			String[] fax = new String[10];
			String[] bankaccount = new String[10];
			String[] bankname = new String[10];
			String[] lineaccount = new String[10];
			int[] bankswiftcode = new int[10];

			for (int i = 0; i < lista.length(); i++) {
				sellerid[i] = (int) lista.getJSONObject(i).get("sellerid");
				companyname[i] = (String) lista.getJSONObject(i).get("companyname");
				mobile[i] = (String) lista.getJSONObject(i).get("mobile");
				taxid[i] = (String) lista.getJSONObject(i).get("taxid");
				address[i] = (String) lista.getJSONObject(i).get("address");
				email[i] = (String) lista.getJSONObject(i).get("email");
				contactperson[i] = (String) lista.getJSONObject(i).get("contactperson");
				contactpersonnum[i] = (String) lista.getJSONObject(i).get("contactpersonnum");
				fax[i] = (String) lista.getJSONObject(i).get("fax");
				bankaccount[i] = (String) lista.getJSONObject(i).get("bankaccount");
				bankname[i] = (String) lista.getJSONObject(i).get("bankname");
				lineaccount[i] = (String) lista.getJSONObject(i).get("lineaccount");
				bankswiftcode[i] = (int) lista.getJSONObject(i).get("bankswiftcode");
			}

			JSONObject[] obj;
			obj = new JSONObject[lista.length()]; // 新增obj陣列

			for (int i = 0; i < lista.length(); i++) {
				obj[i] = new JSONObject();
			}
			for (int i = 0; i < lista.length(); i++) { // 把各欄位的值組合成一個JSONArray
				obj[i].put("supplierid", sellerid[i]);
				obj[i].put("suppliercompanyname", companyname[i]);
				obj[i].put("mobile", mobile[i]);
				obj[i].put("taxid", taxid[i]);
				obj[i].put("address", address[i]);
				obj[i].put("email", email[i]);
				obj[i].put("contactperson", contactperson[i]);
				obj[i].put("contactpersonnum", contactpersonnum[i]);
				obj[i].put("fax", fax[i]);
				obj[i].put("bankaccount", bankaccount[i]);
				obj[i].put("bankname", bankname[i]);
				obj[i].put("lineaccount", lineaccount[i]);
				obj[i].put("bankswiftcode", bankswiftcode[i]);
				listc.put(obj[i]);
			}
			System.out.println("duck");
			System.out.println(listc);
		}
		return listc.toString();
	}

	public JSONArray InsertNew(Integer accontid,String companyname) {

		JSONArray listc = new JSONArray();
		boolean ab = false;
		int a = 0;
		int c=0;
		List<AccountsBean> beana = accountsRepository.findAllByCompanyname(companyname);
		
		if (beana != null) {
			JSONArray listb = new JSONArray();
			for (AccountsBean bean : beana) {
				if (bean != null) {
					listb.put(bean.toJsonObject()); // 把所有關聯場商找出來
				}
			}
		System.out.println("listb"+listb);
		System.out.println();
		
		c=(int) listb.getJSONObject(0).get("accountid");
		
		
		if (a == 0) {
			CompanyFollowingListBean in = new CompanyFollowingListBean();
			in.setBuyerId(accontid);
			in.setSellerId(c);
			companyFollowingListReposutory.save(in);
			ab = true;
		}

		System.out.println("duck");
		if (ab == true) {
//			List<View_companyfollowinglist_accountsBean> beans = view_companyfollowinglist_accountRepository
//					.findAllByBuyerid(accontid);
			List<View_companyfollowinglist_accountsBean> beans = view_companyfollowinglist_accountRepository
					.findAllByCompanyname(companyname);
			
			if (beans != null) {
				JSONArray lista = new JSONArray();
				for (View_companyfollowinglist_accountsBean bean : beans) {
					if (bean != null) {
						lista.put(bean.toJsonObject()); // 把所有關聯場商找出來
					}
				}
				int[] sellerida = new int[10];
				String[] companynamelist = new String[10];
				String[] mobile = new String[10];
				String[] taxid = new String[10];
				String[] address = new String[10];
				String[] email = new String[10];
				String[] contactperson = new String[10];
				String[] contactpersonnum = new String[10];
				String[] fax = new String[10];
				String[] bankaccount = new String[10];
				String[] bankname = new String[10];
				String[] lineaccount = new String[10];
				int[] bankswiftcode = new int[10];

				for (int i = 0; i < lista.length(); i++) {
					sellerida[i] = (int) lista.getJSONObject(i).get("sellerid");
					companynamelist[i] = (String) lista.getJSONObject(i).get("companyname");
					mobile[i] = (String) lista.getJSONObject(i).get("mobile");
					taxid[i] = (String) lista.getJSONObject(i).get("taxid");
					address[i] = (String) lista.getJSONObject(i).get("address");
					email[i] = (String) lista.getJSONObject(i).get("email");
					contactperson[i] = (String) lista.getJSONObject(i).get("contactperson");
					contactpersonnum[i] = (String) lista.getJSONObject(i).get("contactpersonnum");
					fax[i] = (String) lista.getJSONObject(i).get("fax");
					bankaccount[i] = (String) lista.getJSONObject(i).get("bankaccount");
					bankname[i] = (String) lista.getJSONObject(i).get("bankname");
					lineaccount[i] = (String) lista.getJSONObject(i).get("lineaccount");
					bankswiftcode[i] = (int) lista.getJSONObject(i).get("bankswiftcode");
				}
				JSONObject[] obj;
				obj = new JSONObject[lista.length()];

				for (int i = 0; i < lista.length(); i++) {
					obj[i] = new JSONObject();
				}
				for (int i = 0; i < lista.length(); i++) {
					obj[i].put("sellerid", sellerida[i]);
					obj[i].put("companyname", companynamelist[i]);
					obj[i].put("mobile", mobile[i]);
					obj[i].put("taxid", taxid[i]);
					obj[i].put("address", address[i]);
					obj[i].put("email", email[i]);
					obj[i].put("contactperson", contactperson[i]);
					obj[i].put("contactpersonnum", contactpersonnum[i]);
					obj[i].put("fax", fax[i]);
					obj[i].put("bankaccount", bankaccount[i]);
					obj[i].put("bankname", bankname[i]);
					obj[i].put("lineaccount", lineaccount[i]);
					obj[i].put("bankswiftcode", bankswiftcode[i]);
					listc.put(obj[i]);
				}
				System.out.println(listc);
			}
		}
		}
		return null;
	}
}
