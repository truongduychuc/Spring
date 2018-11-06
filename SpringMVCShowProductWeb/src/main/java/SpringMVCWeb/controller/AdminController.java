package SpringMVCWeb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import SpringMVCWeb.dao.ProductDAO;
import SpringMVCWeb.model.ProductInfo;
import SpringMVCWeb.validator.ProductInfoValidator;


@Controller
@Transactional
@EnableWebMvc //enable spring mvc module,equivalent to <mvc:annotation-driven/> ,enable supports @Controller
public class AdminController {
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ProductInfoValidator productInfoValidator; //authenticate product information inputed in product form by admin or manager
	@SuppressWarnings("all")
	@Autowired 
	private ResourceBundleMessageSource messageSource; //configurated in appcontext,using for getting message text from message/validator
	
	@InitBinder //support for mvc validator, support for working with complex data when spring just automatically bind with simple data
	public void myInitBinder(WebDataBinder dataBinder) { 
		Object target=dataBinder.getTarget();
		if(target==null) return;
		System.out.println("Target= "+target);
		if(target.getClass()==ProductInfo.class) {
			dataBinder.setValidator(productInfoValidator);
			dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor()); //for convert to byte[]
		}
	}
	
	//login page
	@RequestMapping(value="/login",method=RequestMethod.GET,produces="application/x-www-form-urlencoded;charset=UTF-8")
	public String login() {
		return "login";
	}
	//show account info
	@RequestMapping(value="/accountInfo",method=RequestMethod.GET,produces="application/x-www-form-urlencoded;charset=UTF-8")
	public String getAccountInfo(Model model) {
		UserDetails userDetails=(UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //get the user who is logging in systems
		System.out.println(userDetails.getUsername());
		System.out.println(userDetails.getPassword());
		System.out.println(userDetails.getAuthorities());
		model.addAttribute("userDetails",userDetails);  //help to transmit the userdetail properties to account info page
		return "accountInfo";
	}
	@RequestMapping(value="/product",method=RequestMethod.POST,produces="application/x-www-form-urlencoded;charset=UTF-8") //save product after validating productForm information inputed
	@Transactional(propagation=Propagation.NEVER) //propagation: transaction context options for method
	public String saveProduct(Model model,@ModelAttribute("productForm")@Validated ProductInfo productInfo,BindingResult bindingResult,final RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {  //if any error exists, back to add_edit_product page to retype
			return "add_edit_product";
		}
		try {
			productDAO.save(productInfo);  //save new product with the productInfo is the temp model class
		}catch(Exception e) {
			String message=e.getMessage(); 
			model.addAttribute("message",message);
			return "add_edit_product";  //if exception is thrown, come back to add_edit_product
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/product",method=RequestMethod.GET,produces="application/x-www-form-urlencoded;charset=UTF-8")//show product information of product existed or show product form to add new product
	public String editProduct(Model model,@RequestParam(value="code",defaultValue="")String code) {
		ProductInfo productInfo=null;
		if(code!=null&&code.length()>0) {
			productInfo=productDAO.findProductInfo(code);
		}
		if(productInfo==null) {
			productInfo=new ProductInfo();
			productInfo.setNewProduct(true);
		}
		model.addAttribute("productForm",productInfo);
		return "add_edit_product";
	}
	@RequestMapping(value="/removeProduct/{code}",produces="application/x-www-form-urlencoded;charset=UTF-8")
	public String removeProduct(@PathVariable("code")String code,Model model) {
		if (code!=null) {
		productDAO.removeProduct(code);
		boolean hasbeenDelected=true;
		model.addAttribute("delectedSuccessfully", hasbeenDelected);
		}
		return "redirect:/";
	}

}
