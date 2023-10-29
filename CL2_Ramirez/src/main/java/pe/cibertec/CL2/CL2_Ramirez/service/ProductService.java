package pe.cibertec.CL2.CL2_Ramirez.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.cibertec.CL2.CL2_Ramirez.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import pe.cibertec.CL2.CL2_Ramirez.model.bd.Product;
@Service
@AllArgsConstructor

public class ProductService {
    private ProductRepository productRepository;

    public List<Product> listarProductos(){
        return productRepository.findAll();
    }
    public Product guardar(Product product){
        return productRepository.save(product);
    }
    public Optional<Product> obtenerProductoPorId(Integer id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            return Optional.empty();
        }else
            return product;
    }

    public Optional<Product> obtenerProductoPorNombre(String productName){
        Optional<Product> product = productRepository.findByProductname(productName);
        if(product.isEmpty())
            return  Optional.empty();
        else
            return product;
    }

    public List<Product> obtenerProductoPorFiltro(String filtro){
        return productRepository.filtrarProductosPorNombreSQL(filtro);
    }
}
