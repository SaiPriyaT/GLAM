package com.glam.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.glam.beans.Branch;
import com.glam.beans.CustomerAppointment;
import com.glam.beans.CustomerVisit;
import com.glam.beans.OurCare;
import com.glam.beans.Product;
import com.glam.beans.ProductCategory;
import com.glam.beans.ProductHistory;
import com.glam.beans.Store;
import com.glam.beans.Vendors;
import com.glam.repository.ProductHistoryRepository;
import com.glam.repository.VendorRepository;
import com.glam.services.BranchService;
import com.glam.services.ProductCategoryService;
import com.glam.services.ProductHistoryService;
import com.glam.services.ProductService;
import com.glam.services.StoreService;
import com.glam.services.VendorService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCategoryService productCategoryService;

	@Autowired
	private ProductHistoryService productHistoryService;
	@Autowired
	private ProductHistoryRepository productHistoryRepository;
	@Autowired
	private StoreService storeService;
	@Autowired
	private BranchService branchService;

	@Autowired
	private VendorRepository vendorRepository;

	private static final Logger log = Logger.getLogger(BranchController.class);
	
	
	@GetMapping("/multipleProductSaleForm")
	public String VendorPage(Model model,Product product) {
//		ProductHistory productHistory = new ProductHistory();
//		model.addAttribute("objVendors", productHistory);
		
		List<Store> storeList = storeService.getAllStore();
		System.out.println(storeList);
        
		model.addAttribute("storeList", storeList);
		return "sellingDropdown";
	}
	
	
	
	
	
	@PostMapping("/productList")
	public String bookAppointment(
			Model model, final HttpServletRequest request, HttpSession session) {

		List<Store> storeList = storeService.getAllStore();
		System.out.println("::::inside bookAppointment method calling::::");
		model.addAttribute("storeList", storeList);
		String getQry = request.getParameter("getQry");
		String storeID = request.getParameter("storeID");
		
		
		System.out.println(storeID + " ------pavan-------" + getQry);
		if (getQry != null && getQry.equals("sellingDropdownForm") && storeID != null && storeID != "") {
			List<Branch> branchList = branchService.getAllBranchesByStoreID(Integer.valueOf(storeID));
			System.out.println("branchList:::" + branchList.size());
			model.addAttribute("branchList", branchList);
			System.out.println(storeID + " " + getQry + " hi");
	
			return "sellingDropdown";

			
		}
		List<Branch> branchList = branchService
				.getAllBranchesByStoreID(Integer.valueOf(storeID));
		model.addAttribute("branchList", branchList);

		
		List<Product> productList = productService.getAllProducts();
		
		
	
		model.addAttribute("product", productList);
		
	
		return "findProducts";
	}
	
	
	
	@PostMapping("/selectList")
	public String savecustomerAppointment(Model model,String[] s1,	Product product,@ModelAttribute("obj")ProductHistory productHistory,@RequestParam(value="productId") String productId) {
		
	
		Branch egbn=branchService.getBranchById(productHistory.getBranchID());
//		customerAppointment1.setBranchName(egbn.getBranchName());
		
		Store egsn=storeService.getStoreById(productHistory.getStoreID());
		
		
		;
	
		s1=productId.split(",");
	    

		List<Product> productList = new ArrayList<Product>();

		
		  for(String billingList : s1) {
		  
		  Product oneProduct=productService.getProductById(Integer.parseInt(billingList));
		  productList.add(oneProduct);
		  ProductHistory p=new ProductHistory();
		  p.setProductId(Integer.parseInt(billingList));;
		  System.err.println(p.getTrackId());
		  //productHistoryService.saveProductHistory(p); 
		  }
		  model.addAttribute("productList",productList);		  

		
		return "multipleProductsBill";
	}
	@PostMapping("/save")
	public String saveProducts(Model model,String[] s1,	Product product,@ModelAttribute("obj")ProductHistory productHistory,@RequestParam(value="units",required = false) String units) {
	
System.err.println("productHistory.getProductId()="+productHistory.getProductId());
System.err.println("units"+units);
		
		return "multiroductsBill";	
	}
		
	
		
		
		
		
		
		
		
		
		
		
		
		
	
	
//	@PostMapping("/review/billing")
//	public String previewBill(@ModelAttribute("obj") CustomerAppointment customerAppointment1,
//			Model model, final HttpServletRequest request, HttpSession session) {
//		customerAppointmentService.addNewCustomerAppointment(customerAppointment1);
//		return "redirect:/review/bookAppointment/"+customerAppointment1.getAppointmentID();
//	}
	
	
	
	
	

	@GetMapping("/showNewProductForm/{id}")
	public String showNewProductForm(Model model, @PathVariable("id") int id) {
		ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
		Product product = new Product();
		List<Vendors> vendors=vendorRepository.findAll();
		
		model.addAttribute("vendors", vendors);
		model.addAttribute("categoryid", productCategory.getCategoryId());
		model.addAttribute("productCategory", productCategory);

		model.addAttribute("product", product);
		return "newProductForm";
	}

	@GetMapping("/showFileReadForm/{id}")
	public String showFileReadForm(Model model, @PathVariable("id") int id) {
		ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
		Product product = new Product();

		model.addAttribute("categoryid", productCategory.getCategoryId());
		model.addAttribute("productCategory", productCategory);

		model.addAttribute("product", product);
		return "newProductForm";
	}

	@PostMapping("/saveProduct")
    public String saveProduct(Model model,@ModelAttribute("product") Product product,ProductHistory productHistory,
    		@ModelAttribute("productCategory")ProductCategory productCategory,@RequestParam(value="Q",required=false,defaultValue = "0") Integer q,
    		@RequestParam(value="action") String action ,
    		@RequestParam(value="Cost",required=false,defaultValue = "0") Double cost,
    		@RequestParam(value="Name",required=false,defaultValue = "0") String name,
    		@RequestParam(value="Number",required=false,defaultValue = "0") Long number) {
       
    	product.setCategoryId(productCategory.getCategoryId());

        product.setCategoryName(productCategory.getCategoryName());
        product.setIsActive('Y');
       product.setCreatedDate(LocalDateTime.now());
      
       product.setVendorID(product.getVendorID());
       productHistory.setVendorId(product.getVendorID());
       productHistory.setModifiedUnits(q);
       productHistory.setCreatedDate(product.getCreatedDate());
       Vendors vendors=vendorRepository.getById(product.getVendorID());
       product.setStoreID(vendors.getStoreID());
       product.setBranchID(vendors.getBranchID());
 
    if(action.equals("add")) {
    	
    	 int m=q+product.getUnits();
         product.setPreviousUnits(m-q);
        
         
         product.setUnits(m);
         if(m<0) {
             product.setUnits(0);
         }
         System.out.println("=====================praveen==============="+product);

         productService.saveProduct(product);
        productHistory.setPreviousUnits(product.getPreviousUnits());
         productHistory.setProductId(product.getProductId());
         productHistory.setProductName(product.getProductName());
         productHistory.setProductBrand(product.getProductBrand());
         productHistory.setProductPrice(product.getProductPrice());
         productHistory.setUnits(product.getUnits());
         
         productHistory.setAction(product.getAction());
         productHistoryService.saveProductHistory(productHistory);
    }	
	  else {
		  if (q<=product.getUnits()) {
			
		  if(action.equals("sold")) {			  
			  productHistory.setAction(action);
			  productHistory.setCustomerNum(number);
			  productHistory.setCustomerName(name);
			  productHistory.setSoldPrice(cost);
			  productHistory.setActualPrice(product.getProductPrice());
			  Double noGst=q*product.getProductPrice();
			  productHistory.setBill(noGst+(18.0/100.0)* noGst);
			 
		  }	  
	  int m=product.getUnits()-q; 
	  
	  product.setPreviousUnits(m+q);	
	  product.setUnits(m);
	  
	  productService.saveProduct(product);
	  productHistory.setPreviousUnits(product.getPreviousUnits());
	  productHistory.setProductId(product.getProductId());
	  productHistory.setProductName(product.getProductName());
	  productHistory.setProductBrand(product.getProductBrand());
	  productHistory.setProductPrice(product.getProductPrice());
	  productHistory.setUnits(product.getUnits());
	  
	  productHistory.setAction(product.getAction());	 
	  productHistoryService.saveProductHistory(productHistory);
	  }
	}
    System.out.println("----------save----"+product);
	  return "redirect:/productlist/"+productCategory.getCategoryId();	    
	}
	@GetMapping("/productlist/{id}")
	public String productListBycategoryId(Model model, @PathVariable("id") int categoryid) {

		List<Product> productList = productService.getAllProductsBycategoryId(categoryid);
		System.out.println("==========="+productList);
		
		
		log.debug("This is list of products in categoryid " + categoryid);
		int count = productList.size();
		List<Product> productObj = new ArrayList<>();
		for (int i = 0; i < count; i++) {

			int productid = productList.get(i).getProductId();
			Product product = productService.getProductById(productid);
			
//			 Vendors vendor=vendorRepository.getById(product.getVendorID()); 
//			 model.addAttribute("vendorName",vendor.getVendorName()); 
			ProductCategory productCategory = productCategoryService.getProductCategoryById(product.getCategoryId());
			product.setCategoryName(productCategory.getCategoryName());
			productObj.add(product);
			

			model.addAttribute("category", productCategory);
			
			Vendors vendor=vendorRepository.getById(product.getVendorID());
			model.addAttribute("vendorName", vendor.getVendorName());

		}
	
		
		model.addAttribute("categoryId", categoryid);
		System.out.println("..............................................."+productObj);

		model.addAttribute("productList", productObj);
		return "productList";
	}
	
	@PostMapping("/saleProduct")
    public String Sale(Model model,@ModelAttribute("product") Product product,ProductHistory productHistory,
    		@ModelAttribute("productCategory")ProductCategory productCategory,@RequestParam(value="Q",required=false,defaultValue = "0") Integer q,
    		@RequestParam(value="action") String action ,
    		@RequestParam(value="Cost",required=false,defaultValue = "0") Double cost,
    		@RequestParam(value="Name",required=false,defaultValue = "0") String name,
    		@RequestParam(value="Number",required=false,defaultValue = "0") Long number) {
       
    	product.setCategoryId(productCategory.getCategoryId());

        product.setCategoryName(productCategory.getCategoryName());
        product.setIsActive('Y');
       product.setCreatedDate(LocalDateTime.now());
       System.out.println(product.getVendorID()+"===========================================");
       
       productHistory.setModifiedUnits(q);
       productHistory.setCreatedDate(product.getCreatedDate());
		
		   Vendors vendor=vendorRepository.getById(product.getVendorID()); 
		   Store store=storeService.getStoreById(vendor.getStoreID()); 
		   Branch branch=branchService.getBranchById(vendor.getBranchID());
    
		  if (q<=product.getUnits()) {			
		 			  
			  productHistory.setAction(action);
			  productHistory.setCustomerNum(number);
			  productHistory.setCustomerName(name);
			  productHistory.setSoldPrice(cost);
			  productHistory.setActualPrice(product.getProductPrice());
			  Double noGst=q*product.getProductPrice();
			  productHistory.setBill(noGst+(18.0/100.0)* noGst);
			 
		  
	  int m=product.getUnits()-q; 
	  
	  product.setPreviousUnits(m+q);	
	  product.setUnits(m);
	  product.setVendorID(product.getVendorID());
	  productService.saveProduct(product);
	  productHistory.setPreviousUnits(product.getPreviousUnits());
	  productHistory.setProductId(product.getProductId());
	  productHistory.setProductName(product.getProductName());
	  productHistory.setProductBrand(product.getProductBrand());
	  productHistory.setProductPrice(product.getProductPrice());
	  productHistory.setUnits(product.getUnits());
	  
	  productHistory.setAction(product.getAction());	
	  productHistory.setVendorId(product.getVendorID());
	  productHistoryService.saveProductHistory(productHistory);
	  //int productid = product.getProductId();
	  model.addAttribute("gst", (18.0/100.0)* noGst);
	  }
		  
		 model.addAttribute("productHistory", productHistory);
		 model.addAttribute("store", store);
		 model.addAttribute("branch", branch);
		 System.out.println("--------productHistory-------"+productHistory);
		 

	  return "redirect:/productBillForm/"+productHistory.getTrackId();	    
	}
	@GetMapping("/productBillForm/{traceid}")
	
	public String productBillForm(Model model,Product product,@PathVariable("traceid") int traceid) {
		System.out.println(":::::::::abhi:::::::::::::::"+product);
		ProductHistory productHistory=productHistoryRepository.getById(traceid);
		
		Vendors vendor=vendorRepository.getById(productHistory.getVendorId()); 
		   Store store=storeService.getStoreById(vendor.getStoreID()); 
		   Branch branch=branchService.getBranchById(vendor.getBranchID());
		   model.addAttribute("gst", (18.0/100.0)* productHistory.getBill());
		   model.addAttribute("productHistory", productHistory);
			 model.addAttribute("store", store);
			 model.addAttribute("branch", branch);
		
		return"productBillingForm";
	}
	
	
	
	
	@GetMapping("/productUpdateForm/{id}")
	public String showUpdateForm(@PathVariable(value = "id") int id, Model model) {
		Product product = productService.getProductById(id);

		if (product.getUnits() < 10) {
			model.addAttribute("productFillAlert", "Only few units are lest please re-fill the products");
		}
		model.addAttribute("product", product);
		System.out.println("================jhgfhjifh==="+product);
		return "addUnitsForm";
	}
	@GetMapping("/internallyUsedProductUpdateForm/{id}")
	public String showInternallyUsedProductForm(@PathVariable(value = "id") int id, Model model) {
		Product product = productService.getProductById(id);


		if (product.getUnits() < 10) {
			model.addAttribute("productFillAlert", "Only few units are lest please re-fill the products");
		}
		model.addAttribute("maxUnits", product.getUnits());
		model.addAttribute("product", product);
		return "internallyUsedProductForm";
	}

    @GetMapping("/saleProductForm/{id}")
    public String saleProductForm(@PathVariable(value = "id") int id, Model model) {
        Product product = productService.getProductById(id);
   
        if(product.getUnits()<10) {
        	model.addAttribute("productFillAlert", "Only few units are lest please re-fill the products" );
        } 
        System.out.println(product.getVendorID());
        model.addAttribute("product", product);
        return "saleProductForm";
    }

	@GetMapping("/deleteProduct/{cid}/{id}")
	public String deleteProduct(@PathVariable(value = "id") int id, @PathVariable(value = "cid") int cid,
			@ModelAttribute("category") ProductCategory productCategory) {
		ProductCategory category = productCategoryService.getProductCategoryById(cid);
		System.out.println(cid + "::::::::::::::");
		this.productService.deleteProductById(id);

		return "redirect:/productlist/" + category.getCategoryId();
	}
	
	
	@GetMapping("/soldHistory")
	public String history(Model model) {
		
	List<ProductHistory> soldHistory = productHistoryService.getAllProductHistory();
	
	
	List<ProductHistory> list=soldHistory.stream().filter(n->n.getAction().equals("sold")).collect(Collectors.toList());
	Double a=0.0; 
	int b=0;
	Double c=0.0;
	
	for(ProductHistory p:list) {
		a=a+p.getSoldPrice();
		b=b+p.getModifiedUnits();
		c=c+p.getActualPrice();
		
	}
	model.addAttribute("soldPrice", a );
	model.addAttribute("soldUnits", b );
	model.addAttribute("ActualPrice", c );
	model.addAttribute("Profits", a-c );
	
	
	
	
 		model.addAttribute("soldHistory", list );
 		
		return "soldHistory";
	}
	
	@GetMapping("/totalHistory")
	public String totalHistory(Model model) {
		
	List<ProductHistory> totalHistory = productHistoryService.getAllProductHistory();
	
	
 		model.addAttribute("totalHistory", totalHistory );
		return "totalHistory";
	}
	
	

	



	}
	
