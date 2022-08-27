angular.module('market-front').controller('profileController', function ($scope, $http, $location) {

    $scope.loadOrders = function () {
        $http({
            url: 'http://localhost:5555/core/api/v1/orders',
            method: 'GET'
        }).then(function (response) {
            $scope.orders = response.data;
        });
    };

    $scope.loadMyProfile = function () {
        $http({
            url: 'http://localhost:5555/auth/api/v1/users/me',
            method: 'GET'
        }).then(function (response) {
            $scope.userProfile = response.data;
        });
    };
    $scope.toPay = function (orderId){
        $location.path('/order_pay/' + orderId);
    }

    $scope.loadOrders();
    $scope.loadMyProfile();
});