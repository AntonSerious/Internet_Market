angular.module('app').controller('storeController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/iMarket/';
    $scope.loadProducts = function(pageIndex){
        $http({
            url: contextPath + 'api/v1/products',
            method: 'GET',
                params: {
                    p : pageIndex,
                    title: $scope.filter ? $scope.filter.title : null,
                    min_price: $scope.filter ? $scope.filter.min_price : null,
                    max_price: $scope.filter ? $scope.filter.max_price : null
                }
        }).then(function (response){
            console.log(response);
            $scope.products = response.data
            $scope.navList = $scope.generatePagesIndexes(1, $scope.products.totalPages)
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
                url: contextPath + 'api/v1/cart/' + $localStorage.guestCartId +'/add/' + productId ,
                method: 'GET'
            }).then(function (response){
                //$scope.loadCart();
            });
        };
    $scope.loadProducts(1);
});
