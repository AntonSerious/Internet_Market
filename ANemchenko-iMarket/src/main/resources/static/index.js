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
    $scope.loadPage(1);
    $scope.showProductInfo = function(productIndex){
        $http({
                url: contextPath + 'api/v1/products',
                method: 'GET',
                params: {
                    'p' : productIndex
                }
            }).then(function (response){
                alert("something");

            });
    };
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
});