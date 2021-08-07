angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/iMarket/'

    $scope.loadPage = function(pageIndex){
        $http({
            url: contextPath + 'api/v1/products',
            method: 'GET',
                params: {
                    'p' : pageIndex
                }
            }).then(function (response){
                console.log(response);
                $scope.products = response.data
                $scope.navList = $scope.generatePagesIndexes(1, $scope.products.totalPages)
            });
    };

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
    $scope.generatePagesIndexes = function(startPage, endPage){
        let arr = [];
        for(let i = startPage; i< endPage + 1; i++){
            arr.push(i);
        }
        return arr;
    }
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
    $scope.loadOrders = function(){
           $http({
                url: contextPath + 'api/v1/orders',
                method: 'GET'
           }).then(function (response){
                $scope.orders = response.data;
           });
        };


    $scope.createOrder = function(){
                $http({
                    url: contextPath + 'api/v1/orders/create',
                    method: 'POST'
                }).then(function (response){
                    alert('Заказ создан');
                    $scope.loadCart();
                    $scope.loadOrders();
                });
    };


    $scope.loadPage(1);
    $scope.loadCart();
    $scope.loadOrders();

});