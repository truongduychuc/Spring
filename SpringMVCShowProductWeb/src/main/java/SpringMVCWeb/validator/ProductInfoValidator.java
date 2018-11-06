package SpringMVCWeb.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import SpringMVCWeb.dao.ProductDAO;
import SpringMVCWeb.entity.Product;
import SpringMVCWeb.model.ProductInfo;

@Component
public class ProductInfoValidator implements Validator {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Override
	public boolean supports(Class<?> class1) {  //support authentication for class ProductInfo
		return class1==ProductInfo.class;
	}
	@Override
	public void validate(Object object, Errors errors) {
		ProductInfo productInfo=(ProductInfo)object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "NotEmpty.productForm.code"); //for productFom in add_edit_product.jsp
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.productForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.productForm.price");
		String code=productInfo.getCode();
		if(code!=null&&code.length()>0) {
			if(code.matches("\\s+")) {
				errors.rejectValue("code", "Pattern.productForm.code");
			}else if(productInfo.isNewProduct()) {
				Product product=productDAO.findProduct(code);
				if(product!=null) {
					errors.rejectValue("code", "Duplicate.productForm.code");
				}
			}
		}
	}

}
