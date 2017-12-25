package com.example.spring.service;

/**
 * Created by elh on 06.09.17.
 */

import com.example.spring.dao.ProductDaoImpl;
import com.example.spring.dto.ProductDTO;
import com.example.spring.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl {

    @Autowired
    ProductDaoImpl productDao;

    private String URL_PREFIX = "/image/";

    @Transactional
    public void add(Product product) {
        productDao.add(product);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getLatest() {
        return productDao.getLast().stream().map(this::mapProductToProductDTO)
            .collect(Collectors.toList());
    }

    @Transactional
    public ProductDTO getById(Long id) {
        return mapProductToProductDTO(productDao.getById(id));
    }

    private ProductDTO mapProductToProductDTO(Product product) {
        ProductDTO.ProductDTOBuilder productDTOBuilder = ProductDTO.builder();
        productDTOBuilder.id(product.getId());
        productDTOBuilder.description(product.getDescription());
        productDTOBuilder.title(product.getTitle());
        productDTOBuilder.img(URL_PREFIX + product.getImageName());
        productDTOBuilder.price(product.getPrice());
        return productDTOBuilder.build();
    }


}
