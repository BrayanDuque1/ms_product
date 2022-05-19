package co.com.bancolombia.usecase.bankingproduct;


import co.com.bancolombia.model.bankingproduct.BankingProduct;
import co.com.bancolombia.model.bankingproduct.gateways.BankingProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BankingProductUseCase {
    private final BankingProductRepository bankingProductRepository;

    public Flux<BankingProduct> listBankingProducts() {
        return bankingProductRepository.listBankingProducts();
    }

    public Mono<BankingProduct> getBankingProductById(String id) {
        return bankingProductRepository.getBankingProductById(id);
    }

    public Mono<BankingProduct> addBankingProduct(BankingProduct bankingProduct) {
        return bankingProductRepository.addBankingProduct(bankingProduct);
    }




}
