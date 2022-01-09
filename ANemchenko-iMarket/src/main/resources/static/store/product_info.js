angular.module('app').controller('productInfoController', function ($scope, $http, $localStorage, $routeParams) {
    const contextPath = 'http://localhost:8189/iMarket/';
    $scope.loadProductInfo = function(productId){
        $http({
            url: contextPath + 'api/v1/products/' + productId + '/info',
            method: 'GET'
        }).then(function (response){
            //console.log(response);
            $scope.productInfo = response.data
        });
    };
    $scope.loadProductComments = function(productId){
            $http({
                url: contextPath + 'api/v1/products/' + productId + '/comments',
                method: 'GET'
            }).then(function (response){
                $scope.productComments = response.data
                console.log(response.data);
            });
        };
    $scope.isCommentSent = function(productId){
                $http({
                    url: contextPath + 'api/v1/products/' + productId + '/isCommentSent',
                            method: 'GET'
                    }).then(function (response){
                        if(response.data == true){
                            $scope.isCommentSentResult = true;
                            $scope.loadProductComments($routeParams.productId)
                        } else {
                            $scope.isCommentSentResult = false;
                        }
                    });
            };
    $scope.isProductBought = function(productId){
        $http({
            url: contextPath + 'api/v1/orders/isProductOrdered',
                    method: 'GET',
                    params: {
                            p : productId
                            }
            }).then(function (response){
                if(response.data == true){
                    $scope.isProductBoughtResult = true;
                    $scope.isCommentSent($routeParams.productId)
                } else {
                    $scope.isProductBoughtResult = false;
                    $scope.loadProductComments($routeParams.productId)
                }
            });
    };
    $scope.tryToSendComment = function(productId){
        alert($scope.comment);


        $http.post(contextPath + 'api/v1/products/' + productId + '/comments', '{comment:' + $scope.comment + '}')
            .then(function successCallback(response) {
                alert('Отзыв успешно отправлен');
                $scope.isCommentSentResult = true;
            }, function errorCallback(response) {
            });
    };
    $scope.loadProductInfo($routeParams.productId);
    $scope.isProductBought($routeParams.productId);
    console.log($scope.isProductBoughtResult);

});