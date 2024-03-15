package co.simplon.site.business.service.product;

import co.simplon.site.business.convert.ProductConvert;
import co.simplon.site.business.dto.ProductDTO;
import co.simplon.site.persistance.entity.Product;
import co.simplon.site.persistance.repository.product.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {


    @Autowired
    public void setProductRepo(IProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    private IProductRepository productRepo;

    @Override
    public List<ProductDTO> listerProducts() {
        final List<Product> result = productRepo.findAll();
        return ProductConvert.getInstance().convertListEntityToListDto(result);
    }

    @Override
    public ProductDTO findById(int id) {
        final Product result = productRepo.getReferenceById(id);
        return ProductConvert.getInstance().convertEntityToDto(result);
    }

    @Override
    public void addProduct(ProductDTO product) {
     productRepo.save(ProductConvert.getInstance().convertDtoToEntity(product));

    }


    @Override
    public void modifyProduct(int id, ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setDesc(productDTO.getDesc_product());
            existingProduct.setImg(productDTO.getImg_product());
            existingProduct.setName(productDTO.getName_product());
            existingProduct.setPrice(productDTO.getPrice_product());
            existingProduct.setRef(productDTO.getRef_product());
            existingProduct.setQuantity(productDTO.getQuantity_product());

            productRepo.save(existingProduct);

    }}

    @Override
    public void deleteProduct(int id) {
        productRepo.deleteById(id);

    }
}
