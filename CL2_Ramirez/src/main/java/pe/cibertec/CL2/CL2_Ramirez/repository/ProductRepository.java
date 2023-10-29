package pe.cibertec.CL2.CL2_Ramirez.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.cibertec.CL2.CL2_Ramirez.model.bd.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository  extends JpaRepository<Product, Integer>{


    Optional<Product> findByProductname(String ProductName);

    List<Product> findByProductnameContainingIgnoreCase(String filtro);

    @Query("SELECT c FROM Product c WHERE c.nameproduct LIKE %:filtro%")
    List<Product> filtrarProductosPorNombre(@Param("filtro") String filtro);

    @Query(value = "SELECT * FROM product WHERE nameproduct LIKE %:filtro%",
            nativeQuery = true)
    List<Product> filtrarProductosPorNombreSQL(@Param("filtro") String filtro);
}
