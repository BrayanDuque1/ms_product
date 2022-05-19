package co.com.bancolombia.model.bankingproduct.gateways;

import co.com.bancolombia.model.bankingproduct.BankingProduct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankingProductRepository {
    Flux<BankingProduct> listBankingProducts();

    Mono<BankingProduct> getBankingProductById(String id);

    Mono<BankingProduct> addBankingProduct(BankingProduct bankingProduct);

}
