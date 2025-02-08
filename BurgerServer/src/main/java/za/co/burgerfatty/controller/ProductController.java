package za.co.burgerfatty.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.burgerfatty.dto.ProductDto;
import za.co.burgerfatty.service.ProductService;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/products/")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("all")
    public ResponseEntity<ArrayList<ProductDto>> getAllProducts() {
        return ResponseEntity.ok().body(new ArrayList<ProductDto>());
    }

    @GetMapping("{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") String productId) {
        return ResponseEntity.ok().body(new ProductDto());
    }

    @PostMapping("post")
    public ResponseEntity<ProductDto> postProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productDto);
    }

    @PutMapping("update")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productDto);
    }

    @PatchMapping("patch")
    public ResponseEntity<ProductDto> patchProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("productId") String productId) {
        return ResponseEntity.ok().body(new ProductDto());
    }
}
