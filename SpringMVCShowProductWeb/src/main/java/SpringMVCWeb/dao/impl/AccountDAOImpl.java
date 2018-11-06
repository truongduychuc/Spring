package SpringMVCWeb.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import SpringMVCWeb.dao.AccountDAO;
import SpringMVCWeb.entity.Account;

@Transactional//transaction for hibernate
@Repository //use for dao classes
public class AccountDAOImpl implements AccountDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("deprecation")
	public Account findAccount(String userName) {
		Session session=this.sessionFactory.getCurrentSession();
		Criteria crit=session.createCriteria(Account.class);
		crit.add(Restrictions.eq("userName", userName));
		return (Account)crit.uniqueResult();
	}
}
