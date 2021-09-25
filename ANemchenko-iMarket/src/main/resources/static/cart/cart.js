angular.module('app').controller('cartController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/iMarket/';

    $scope.loadCart = function(){
        $http({
            url: contextPath + 'api/v1/cart',
            method: 'GET'
        }).then(function (response){
            $scope.cart = response.data;
        });
    }


    $scope.deleteProductFromScope = function(productIndex){
            $scope.products.content.splice(productIndex, 1);
    };

    $scope.deleteProductById = function(productIndexDB, productIndexScope ){
            $http({
                    url: contextPath + 'api/v1/products',
                    method: 'DELETE',
                    params: {
                        'p' : productIndexDB
                    }
                }).then(function (response){
                    $scope.deleteProductFromScope(productIndexScope)
                });
    };

    $scope.addToCart = function(productId){
        $http({
            url: contextPath + 'api/v1/cart/add/' + productId ,
            method: 'GET'
        }).then(function (response){
            $scope.loadCart();
        });
    };
    $scope.removeOneFromCart = function(productId){
            $scope.cart
            $http({
                url: contextPath + 'api/v1/cart/remove/' + productId ,
                method: 'GET'
            }).then(function (response){
                $scope.loadCart();
            });
        };
    $scope.clearCart = function(productId){
            $http({
                url: contextPath + 'api/v1/cart/clear/' + productId ,
                method: 'GET'
            }).then(function (response){
                $scope.loadCart();
            });
    };
    $scope.createOrder = function(){
        $http({
            url: contextPath + 'api/v1/orders/create',
            method: 'POST'
        }).then(function (response){
            alert('Заказ создан');
            //$scope.loadCart();
            //$scope.loadOrders();
            $location.path('/store');
        });
    };
    $scope.loadCart();
});