package SpringMVCWeb.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import SpringMVCWeb.dao.ProductDAO;
import SpringMVCWeb.entity.Product;
import SpringMVCWeb.model.PaginationResult;
import SpringMVCWeb.model.ProductInfo;

@Controller
@Transactional //required for hibernate transaction
@EnableWebMvc //essential for using "redirect:"
public class MainController {
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value="/403",produces="application/x-www-form-urlencoded;charset=UTF-8")
	public String accessDenied() {
		return "403";
	}
	@RequestMapping(value="/",produces="application/x-www-form-urlencoded;charset=UTF-8")
	public String home(Model model,@RequestParam(value="name",defaultValue="")String likeName,@RequestParam(value="page",defaultValue="1")int page) {
		final int maxResult=5;
		final int maxNavigationPage=10;
		PaginationResult<ProductInfo> result=productDAO.queryProducts(page, maxResult, maxNavigationPage, likeName);//invoke constru
		model.addAttribute("paginationProducts",result); //show products to index page
		return "index";
	}
	@RequestMapping(value="/productImage",method=RequestMethod.GET,produces="application/x-www-form-urlencoded;charset=UTF-8") 
	public void productImage(HttpServletRequest request,HttpServletResponse response,Model model,@RequestParam("code")String code) throws IOException{
		Product product=null;
		if(code!=null) { //if the href link include 'code' value
			product=this.productDAO.findProduct(code);
		}
		if(product!=null&&product.getImage()!=null) {  //if the product image exists 
			response.setContentType("image/jpeg,image/jpg,image/png,image/gif"); //supports for converting byte[] to image
			response.getOutputStream().write(product.getImage());
		}
		response.getOutputStream().close();
		
	}
	@RequestMapping(value="/allProducts",method=RequestMethod.GET,produces="application/x-www-form-urlencoded;charset=UTF-8")//same as above home method but help us view all product in another page
	public String viewAllProducts(Model model,@RequestParam(value="name",defaultValue="")String likeName,@RequestParam(value="page",defaultValue="1")int page) {
		final int maxResult=5;
		final int maxNavigationPage=10;
		PaginationResult<ProductInfo> result=productDAO.queryProducts(page, maxResult, maxNavigationPage, likeName);
		model.addAttribute("paginationProducts",result);
		return "allProducts";
	}
	@RequestMapping(value="/detail",method=RequestMethod.GET,produces="application/x-www-form-urlencoded;charset=UTF-8") //detailed product's page
	public String viewdetail(Model model,@RequestParam(value="code")String code) {
		ProductInfo productInfo=this.productDAO.findProductInfo(code);
		model.addAttribute("productForm",productInfo);
		return "detailedProduct";
	}
}
