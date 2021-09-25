angular.module('app').controller('ordersController', function ($rootScope, $scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/iMarket/'

    $scope.loadOrders = function(){
           if(!$rootScope.isUserLoggedIn()){
            return;
           }
           $http({
                url: contextPath + 'api/v1/orders',
                method: 'GET'
           }).then(function (response){
           console.log(response);
                $scope.orders = response.data;
           });
        };



    $scope.isUserLoggedIn = function () {
        if ($localStorage.webMarketUser) {
            return true;
        } else {
            return false;
        }
    };
    $scope.loadOrders();
});