package com.example.spring.contoller;

import com.example.spring.contoller.storage.StorageService;
import com.example.spring.dto.ProductDTO;
import com.example.spring.model.Product;
import com.example.spring.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private ProductServiceImpl productService;

    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestParam("title") String title, @RequestParam("description") String description,
        @RequestParam("price") Integer price, @RequestParam("image") MultipartFile image) {

        String fileName = storageService.store(image);
        productService.add(new Product(title, description, fileName, price));
        return "ok";
    }

    @RequestMapping(value = "/product/list", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductDTO> getLast() {
        return productService.getLatest();
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ProductDTO getById(@PathVariable("id") Long id) {
        return productService.getById(id);
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.GET)
    public String getAdd() {
        return "addProduct";
    }

    @RequestMapping(value = { "/product/", "/product", "/", "/home" }, method = RequestMethod.GET)
    public String getLastView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean seller = authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_SELLER"));
        model.addAttribute("isSeller", seller);
        return "productList";
    }

    @RequestMapping(value = { "/product/detail/", "/product/detail/{id}", "product/detail" },
        method = RequestMethod.GET)
    public String getProdDetail() {
        return "productList";
    }
}
