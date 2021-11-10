package com.anemchenko.endpoints;

import com.anemchenko.services.ProductService;
import com.anemchenko.soap.products.GetAllProductsRequest;
import com.anemchenko.soap.products.GetAllProductsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ProductsEndpoint {
    private static final String NAMESPACE_URI = "http://www.anemchenko.com/spring/ws/products";
    private final ProductService productService;



    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getGroupByTitle(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        //response.setProducts(productService.findAllSoap());
        productService.findAllSoap().forEach(response.getProducts()::add);
        return response;
    }


    /*
        Пример запроса: POST http://localhost:8189/iMarket/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.anemchenko.com/spring/ws/students">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */
}
