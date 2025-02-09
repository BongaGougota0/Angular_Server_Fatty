package za.co.burgerfatty.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.burgerfatty.dto.ProductDto;
import za.co.burgerfatty.service.ProductService;
import java.util.List;

@RestController
@RequestMapping(path = "api/products/")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("all")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") String productId) {
        return ResponseEntity.ok().body(productService.getProductById(Integer.valueOf(productId)));
    }

    @PostMapping("post")
    public ResponseEntity<ProductDto> postProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.newProduct(productDto));
    }

    @PutMapping("update")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.updateProduct(productDto));
    }

    @PatchMapping("patch")
    public ResponseEntity<ProductDto> patchProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("productId") String productId) {
        return ResponseEntity.ok().body(productService.deleteProductById(Integer.valueOf(productId)));
    }
}
