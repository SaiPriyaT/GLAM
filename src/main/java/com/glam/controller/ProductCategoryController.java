package com.glam.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.glam.beans.ProductCategory;
import com.glam.services.ProductCategoryService;


@Controller
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;
    private static final Logger log = Logger.getLogger(StoreController.class);

    @GetMapping("/showNewProductCategoryForm")
    public String showNewProductCategoryForm(Model model) {
        ProductCategory productCategory = new ProductCategory();
        log.debug("This is a form for adding new category.");
        model.addAttribute("objProductCategory", productCategory);
        model.addAttribute(  "productobj", productCategory.getCategoryId());
        return "newProductCategoryForm";
    }

    @PostMapping("/saveProductCategory")
    public String saveProductCategory(@ModelAttribute("objProductCategory") ProductCategory productCategory) {
        productCategoryService.saveProductCategory(productCategory);
        return "redirect:/productCategoryList";
    }
    @GetMapping("/productCategoryList")
    public String productCategoryList(Model model) {
        log.debug("This is ProductCategory list");
    List<ProductCategory> productCategory = productCategoryService.getAllProductCategories();
//    model.addAttribute("categoryID", productCategory );
         model.addAttribute("objCategory", productCategory );
        return "productCategoryList";
    }
    @GetMapping("/productCategoryUpdateForm/{id}")
    public String productCategoryUpdateForm(@PathVariable(value = "id") int categoryId, Model model) {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(categoryId);
        log.debug("This is update form for ProductCategory.");

        System.out.println( productCategory.getCategoryId()+"productCategory.........;.;.;.;.;.;.;.;.;.");

        model.addAttribute("objProductCategory", productCategory);
        model.addAttribute("categoryID", productCategory.getCategoryId());

        return "newProductCategoryForm";
    }

}