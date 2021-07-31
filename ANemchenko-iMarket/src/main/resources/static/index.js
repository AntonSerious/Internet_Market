angular.module('app', []).controller('indexController', function ($scope, $http) {

    $scope.loadProducts = function () {
        $http({
            url: 'http://localhost:8189/iMarket/products',
            method: 'GET',
            params: {
            }
        }).then(function (response){
            console.log(response);
            $scope.products = response.data;

        });
    };
    $scope.counterValue = 1;
    $scope.clickIncrementButton = function(){
        $scope.counterValue +=1;
    };


    $scope.loadPage = function(pageIndex){
        $http({
            url: 'http://localhost:8189/iMarket/products_page',
            method: 'GET',
                params: {
                    'p' : pageIndex
                }
            }).then(function (response){
                console.log(response);
                $scope.products = response.data
            });
    };
    $scope.loadPage(1);
    $scope.showProductInfo = function(productIndex){
        $http({
                url: 'http://localhost:8189/iMarket/products',
                method: 'GET',
                params: {
                    'p' : productIndex
                }
            }).then(function (response){
                alert("deleted");

            });
    };
    $scope.deleteProductFromScope = function(productIndex){
            $scope.products.splice(productIndex, 1);
        };

    $scope.deleteProductById = function(productIndexDB, productIndexScope ){
            $http({
                    url: 'http://localhost:8189/iMarket/products/delete',
                    method: 'GET',
                    params: {
                        'p' : productIndexDB
                    }
                }).then(function (response){
                    $scope.deleteProductFromScope(productIndexScope)
                });
    };
});