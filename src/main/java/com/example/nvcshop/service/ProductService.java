package com.example.nvcshop.service;

import com.example.nvcshop.dto.request.ProductRequest;
import com.example.nvcshop.dto.response.ProductResponse;
import com.example.nvcshop.entity.Product;
import com.example.nvcshop.entity.Type;
import com.example.nvcshop.mapper.TypeMapper;
import com.example.nvcshop.repository.ProductRepository;
import com.example.nvcshop.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TypeRepository typeRepository;

    public List<ProductResponse> findAllProduct(){
        List<Product> list = productRepository.findAll();
        List<ProductResponse> productResponseList = new ArrayList<>();
        for(Product product : list){
            ProductResponse productResponse = ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .slug(product.getSlug())
                    .sizes(product.getSizes())
                    .colors(product.getColor())
                    .image1(product.getImage1())
                    .image2(product.getImage2())
                    .image3(product.getImage3())
                    .typeResponses(TypeMapper.toListResponse((List<Type>) product.getTypes()))
                    .build();
            productResponseList.add(productResponse);
        }
        return productResponseList;
    }
    public ProductResponse getProduct(String productId){
        Product product = productRepository.findById(productId).orElse(null);
        if(product != null){
            ProductResponse productResponse = ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .slug(product.getSlug())
                    .sizes(product.getSizes())
                    .colors(product.getColor())
                    .image1(product.getImage1())
                    .image2(product.getImage2())
                    .image3(product.getImage3())
                    .typeResponses(TypeMapper.toListResponse((List<Type>) product.getTypes()))
                    .build();
            return productResponse;
        }
        return null;
    }
    @Transactional
    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setColor(productRequest.getColors());
        product.setSizes(productRequest.getSizes());
        product.setImage1(productRequest.getImage1());
        product.setImage2(productRequest.getImage2());
        product.setImage3(productRequest.getImage3());

        Set<String> typeId = productRequest.getType();
        List<Type> listType = new ArrayList<>();
        for(String s : typeId){
            Type type = typeRepository.findById(s).orElse(null);
            listType.add(type);
        }
        product.setTypes(listType);

        productRepository.save(product);

        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .slug(product.getSlug())
                .sizes(product.getSizes())
                .colors(product.getColor())
                .image1(product.getImage1())
                .image2(product.getImage2())
                .image3(product.getImage3())
                .typeResponses(TypeMapper.toListResponse((List<Type>) product.getTypes()))
                .build();
        return productResponse;
    }

    @Transactional
    public ProductResponse updateProduct(ProductRequest productRequest, String productId){
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            product.setName(productRequest.getName());
            product.setSizes(productRequest.getSizes());
            product.setColor(productRequest.getColors());
            product.setImage1(productRequest.getImage1());
            product.setImage2(productRequest.getImage2());
            product.setImage3(productRequest.getImage3());
            List<Type> list = typeRepository.findAllById(productRequest.getType());
            product.setTypes(list);

            productRepository.save(product);
            ProductResponse productResponse = ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .slug(product.getSlug())
                    .sizes(product.getSizes())
                    .colors(product.getColor())
                    .image1(product.getImage1())
                    .image2(product.getImage2())
                    .image3(product.getImage3())
                    .typeResponses(TypeMapper.toListResponse((List<Type>) product.getTypes()))
                    .build();
            return productResponse;
        }
        return  null;
    }

    @Transactional
    public Boolean deleteProduct(String productId){
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isPresent()){
            productRepository.deleteById(productId);
            return true;
        }
        else{
            return false;
        }
    }
}
