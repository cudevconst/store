package com.example.nvcshop.service;

import com.example.nvcshop.dto.request.ProductDetailRequest;
import com.example.nvcshop.dto.request.ProductRequest;
import com.example.nvcshop.dto.request.TypeRequest;
import com.example.nvcshop.dto.response.ProductDetailResponse;
import com.example.nvcshop.dto.response.ProductResponse;
import com.example.nvcshop.dto.response.TypeResponse;
import com.example.nvcshop.entity.Product;
import com.example.nvcshop.entity.ProductDetails;
import com.example.nvcshop.entity.Type;
import com.example.nvcshop.entity.TypeProduct;
import com.example.nvcshop.mapper.ProductDetailMapper;
import com.example.nvcshop.mapper.ProductMapper;
import com.example.nvcshop.mapper.TypeMapper;
import com.example.nvcshop.repository.ProductDetailRepository;
import com.example.nvcshop.repository.ProductRepository;
import com.example.nvcshop.repository.TypeProductRepository;
import com.example.nvcshop.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private TypeProductRepository typeProductRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;


    @Autowired
    private ProductDetailService productDetailService;
    public List<ProductResponse> findAll(){
        List<Product> list = productRepository.findAll();
        List<ProductResponse> productResponseList = new ArrayList<>();
        for(Product p : list){
            Collection<Type> types = typeRepository.findAllByProductId(p.getId());
            Collection<TypeResponse> typeResponses = TypeMapper.toListResponse((List<Type>) types);

            List<ProductDetails> productDetails = productDetailRepository.findAllByProductId(p.getId());
            Collection<ProductDetailResponse> productDetailResponses = ProductDetailMapper.toListResponse(productDetails);
            ProductResponse productResponse = ProductMapper.toResponse(p);
            productResponse.setType(typeResponses);
            productResponse.setProductDetail(productDetailResponses);
            productResponseList.add(productResponse);
        }
        return productResponseList;
    }

    public ProductResponse findById(String productId){
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            Collection<Type> types = typeRepository.findAllByProductId(product.getId());
            Collection<TypeResponse> typeResponses = TypeMapper.toListResponse((List<Type>) types);

            List<ProductDetails> productDetails = productDetailRepository.findAllByProductId(product.getId());
            Collection<ProductDetailResponse> productDetailResponses = ProductDetailMapper.toListResponse(productDetails);
            ProductResponse productResponse = ProductMapper.toResponse(product);
            productResponse.setType(typeResponses);
            productResponse.setProductDetail(productDetailResponses);
            return productResponse;
        }
        return null;
    }
    @Transactional
    public ProductResponse createNewProduct(ProductRequest productRequest, List<String> typeList, ProductDetailRequest productDetailRequest) throws IOException {
        Product product = ProductMapper.toEntity(productRequest);
        productRepository.save(product);
        List<TypeProduct> types = new ArrayList<>();
        List<TypeResponse> typeResponseList = new ArrayList<>();
        for(String s : typeList){
            Type type = typeRepository.getById(s);
            TypeResponse typeResponse = TypeMapper.toResponse(type);
            typeResponseList.add(typeResponse);
            TypeProduct typeProduct = TypeProduct.builder()
                    .type(type)
                    .product(product)
                    .build();
            typeProductRepository.save(typeProduct);
        }

        product.setTypeProducts(types);
        ProductDetails productDetails = productDetailService.createProductDetail(productDetailRequest);
        List<ProductDetails> productDetailsList = new ArrayList<>();
        productDetailsList.add(productDetails);
        product.setProductDetails(productDetailsList);
        productRepository.save(product);
        productDetails.setProduct(product);
        productDetailRepository.save(productDetails);
        ProductResponse productResponse = ProductMapper.toResponse(product);

        productResponse.setType(typeResponseList);

        productResponse.setProductDetail(ProductDetailMapper.toListResponse(productDetailsList));
        return productResponse;

    }

    @Transactional
    public ProductResponse addProductDetail(String productId, ProductDetailRequest productDetailRequest) throws IOException {
        Product product = productRepository.getById(productId);
        ProductDetails productDetails = productDetailService.createProductDetail(productDetailRequest);
        Collection<ProductDetails> productDetailsList = product.getProductDetails();
        productDetailsList.add(productDetails);
        product.setProductDetails(productDetailsList);
        productRepository.save(product);
        productDetails.setProduct(product);
        productDetailRepository.save(productDetails);
        ProductResponse productResponse = ProductMapper.toResponse(product);

        List<Type> list = typeRepository.findAllByProductId(productId);
        List<TypeResponse> typeResponseList = new ArrayList<>();
        for(Type t : list){
            TypeResponse typeResponse = TypeMapper.toResponse(t);
            typeResponseList.add(typeResponse);
        }
        productResponse.setType(typeResponseList);
        productResponse.setProductDetail(ProductDetailMapper.toListResponse((List<ProductDetails>) productDetails));

        return productResponse;

    }

    @Transactional
    public ProductResponse updateProduct(String productId, ProductRequest productRequest, List<String> typeIdList){
        Product product = productRepository.getById(productId);
        product.setName(productRequest.getName());
        List<Type> typeList = typeRepository.findAllByProductId(productId);
        List<String> typeStringList = new ArrayList<>();
        Collection<TypeProduct> typeProducts = product.getTypeProducts();
        for(Type t : typeList){
            typeStringList.add(t.getId());
        }

        for(String s1 : typeStringList){
            for(String s2 : typeIdList){
                if(!s2.equals(s1)){
                    typeProducts.add(TypeProduct.builder()
                                    .product(product)
                                    .type(typeRepository.getById(s2))
                            .build());
                }
            }
        }
        for(String s2 : typeIdList){
            for(int i = 0; i < typeStringList.size(); i++){
                if(!typeStringList.get(i).equals(s2)){
                    typeStringList.remove(i);
                    typeProducts.remove(i);
                }
            }
        }
//        product.setTypeProducts(typeProducts);

        return ProductMapper.toResponse(product);


    }

}
