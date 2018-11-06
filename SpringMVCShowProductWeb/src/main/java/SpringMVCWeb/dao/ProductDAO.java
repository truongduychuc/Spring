package SpringMVCWeb.dao;

import SpringMVCWeb.entity.Product;
import SpringMVCWeb.model.PaginationResult;
import SpringMVCWeb.model.ProductInfo;

public interface ProductDAO {
	public Product findProduct(String code);
	public ProductInfo findProductInfo(String code);
	public void save (ProductInfo productInfo);
	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage);
	public PaginationResult<ProductInfo> queryProducts(int page,int maxResult,int maxNavigationPage,String likeName);
	public void removeProduct(String code);
}
