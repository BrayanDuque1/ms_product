package co.com.bancolombia.repository;

import co.com.bancolombia.model.bankingproduct.BankingProduct;
import co.com.bancolombia.model.bankingproduct.gateways.BankingProductRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositoryAdapter implements BankingProductRepository {

    private final  List<BankingProduct> list =new ArrayList<>();

    public  RepositoryAdapter(){
        list.add(BankingProduct.builder().product("Cuenta de Ahorros").id("1").build());
        list.add(BankingProduct.builder().product("Cuenta Corriente").id("2").build());
    }

    @Override
    public Flux<BankingProduct> listBankingProducts() {
        return Flux.fromIterable(list);
    }

    @Override
    public Mono<BankingProduct> getBankingProductById(String id) {
        return Flux.fromIterable(list).filter(prduct -> prduct.getId().equals(id)).singleOrEmpty();
    }

    @Override
    public Mono<BankingProduct> addBankingProduct(BankingProduct bankingProduct) {
        list.add(bankingProduct);
        return Mono.just(bankingProduct);
    }


}
