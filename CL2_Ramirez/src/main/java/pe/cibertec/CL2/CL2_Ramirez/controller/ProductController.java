package pe.cibertec.CL2.CL2_Ramirez.controller;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.CL2.CL2_Ramirez.exception.ResourceNotFoundException;
import pe.cibertec.CL2.CL2_Ramirez.service.ProductService;
import pe.cibertec.CL2.CL2_Ramirez.model.bd.Product;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/producto")
public class ProductController {

    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<Product>> listarProducto(){
        List<Product> productList = new ArrayList<>();
        productService.listarProductos()
                .forEach(productList::add);
        if(productList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> obtenerProducto(
            @PathVariable("id") Integer id){
        Product product = productService
                .obtenerProductoPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El producto con el Id Nro. "+
                        id + " no existe."));

        return new ResponseEntity<>(product, HttpStatus.OK);
    }



    @GetMapping("/productname/{filtro}")
    public ResponseEntity<List<Product>> filtrarProductoPorNombre(
            @PathVariable("filtro") String filtro
    ){
        List<Product> productList = new ArrayList<>();
        productService.obtenerProductoPorFiltro(filtro)
                .forEach(productList::add);
        if(productList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Product> registrarProducto(
            @RequestBody Product product
    ){
        return new ResponseEntity<>(
                productService.guardar(product), HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> actualizarProducto(
            @PathVariable("id") Integer id,
            @RequestBody Product product
    ){
        Product oldProduct = productService
                .obtenerProductoPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El producto con el Id Nro. "+
                        id + " no existe."));
        oldProduct.setNameproduct(product.getNameproduct());
        //oldProduct.setNameproduct(product.getNameproduct());
        oldProduct.setDescription(product.getDescription());

        return new ResponseEntity<>(
                productService.guardar(oldProduct), HttpStatus.OK
        );
    }



}
