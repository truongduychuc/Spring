package SpringMVCWeb.dao;

import SpringMVCWeb.entity.Account;

public interface AccountDAO {
	public Account findAccount(String userName);
}
