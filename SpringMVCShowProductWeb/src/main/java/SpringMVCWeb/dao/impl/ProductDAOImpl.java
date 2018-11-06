package SpringMVCWeb.dao.impl;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import SpringMVCWeb.dao.ProductDAO;
import SpringMVCWeb.entity.Product;
import SpringMVCWeb.model.PaginationResult;
import SpringMVCWeb.model.ProductInfo;

@SuppressWarnings("all")
@Transactional
@Repository
public class ProductDAOImpl implements ProductDAO  {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("deprecation")
	public Product findProduct(String code) {
		Session session=this.sessionFactory.getCurrentSession();
		Criteria crit=session.createCriteria(Product.class);
		crit.add(Restrictions.eq("code", code));
		return (Product)crit.uniqueResult();
	}
	@Override
	public ProductInfo findProductInfo(String code) {
		Product product=this.findProduct(code);
		if(product==null) {
			return null;
		}
		return new ProductInfo(product.getCode(), product.getName(), product.getPrice(),product.getDescription());
	}
	@Override
	public PaginationResult<ProductInfo> queryProducts(int page,int maxResult, int maxNavigationPage,String likeName){
		String sql="Select new "+ProductInfo.class.getName()+" (p.code,p.name,p.price,p.description) "+" from "+Product.class.getName()+" p ";
		if(likeName!=null&&likeName.length()>0) {
			sql+="Where lower(p.name) like :likeName";
		}
		sql+=" order by p.createDate desc";   // sort products via create date
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(sql);  
		if(likeName!=null&&likeName.length()>0) 
			query.setParameter("likeName", "%" + likeName.toLowerCase()+"%");
		return new PaginationResult<ProductInfo>(query, page, maxResult, maxNavigationPage);
	
	}
	@Override
	public PaginationResult<ProductInfo> queryProducts(int page,int maxResult,int maxNavigationPage){
		return queryProducts(page,maxResult,maxNavigationPage,null);
	}
	@Override
	public void save(ProductInfo productInfo) {
		String code=productInfo.getCode();
		Product product=null;
		boolean isNew=false;
		if(code!=null) {  //if code exists, edit
			product=this.findProduct(code);
		}
		if(product==null) {   //add new product
			isNew=true;
			product=new Product();
			product.setCreateDate(new Date());
		}
		product.setCode(code);
		product.setName(productInfo.getName());
		product.setPrice(productInfo.getPrice());
		product.setDescription(productInfo.getDescription());
		if(productInfo.getFileData()!=null) {    //if product image was uploaded
			byte[] image=productInfo.getFileData().getBytes(); //convert image format to byte[] to save in database
			if(image!=null&&image.length>0) { 
				product.setImage(image);  //set image byte[] for product
			}
		}
		if(isNew) this.sessionFactory.getCurrentSession().persist(product);
		this.sessionFactory.getCurrentSession().flush();//immediately throwing exception if having error in db 
	}
	@Override
	public void removeProduct(String code) {  //
		Session session=this.sessionFactory.getCurrentSession();
		Product product=this.findProduct(code);
		if(product!=null) session.remove(product);
		System.out.println("Product has been delected completely!");
	}
	
	
}
