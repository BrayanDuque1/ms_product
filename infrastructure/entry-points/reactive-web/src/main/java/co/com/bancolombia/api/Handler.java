package co.com.bancolombia.api;

import co.com.bancolombia.model.bankingproduct.BankingProduct;
import co.com.bancolombia.usecase.bankingproduct.BankingProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class Handler {
    private final BankingProductUseCase bankingProductUseCase;

    public Mono<ServerResponse> listBankingProducts(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bankingProductUseCase.listBankingProducts(), BankingProduct.class);
    }

    public Mono<ServerResponse> getBankingProductById(ServerRequest serverRequest) {
        return bankingProductUseCase.getBankingProductById(serverRequest.pathVariable("id"))
                .flatMap(product -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(product), BankingProduct.class))
                .switchIfEmpty(ServerResponse.notFound().build());


    }

    public Mono<ServerResponse> addBankingProduct(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(BankingProduct.class)
                .filter(product -> product.getProduct() != null && product.getId()!=null)
                .flatMap(bankingProductUseCase::addBankingProduct)
                .flatMap(product -> ServerResponse
                        .created(URI.create("/api/product/list"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(product))
                .switchIfEmpty(ServerResponse.badRequest().build());
    }
}
