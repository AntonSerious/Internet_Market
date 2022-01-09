(function () {
    angular
        .module('app',['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider){
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/orders', {
                templateUrl: 'orders/orders.html',
                controller: 'ordersController'
            })
            .when('/profile', {
                templateUrl: 'profile/profile.html',
                controller: 'profileController'
            })
            .when('/admin', {
                templateUrl: 'admin/admin.html',
                controller: 'adminController'
            })
            .when('/store/:productId', {
                templateUrl: 'store/product_info.html',
                controller: 'productInfoController'
            })
            .otherwise({
                redirect: '/'
            });
    }

    function run($rootScope, $http, $localStorage){
        const contextPath = 'http://localhost:8189/iMarket/';
        if($localStorage.webMarketUser){
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.webMarketUser.token;
        }
        if(!$localStorage.guestCartId){
            $http.get(contextPath + 'api/v1/cart/generate')
                .then(function successCallback(response) {
                    $localStorage.guestCartId = response.data.value;
                });
        }
    }
})();

angular.module('app').controller('indexController', function($rootScope, $scope, $http, $localStorage, $location){
    const contextPath = 'http://localhost:8189/iMarket/';

    $scope.tryToAuth = function () {
        $http.post(contextPath + 'api/v1/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.webMarketUser = {username: $scope.user.username, token: response.data.token};
                    $scope.user.username = null;
                    $scope.user.password = null;
                    $http.get(contextPath + 'api/v1/cart/' + $localStorage.guestCartId + '/merge')
                        .then(function successCallback(response) {
                            //$localStorage.guestCartId = response.data.value;
                        });
               }
            }, function errorCallback(response) {
            });
    };
    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.webMarketUser) {
            return true;
        } else {
            return false;
        }
    };
    $scope.clearUser = function () {
        delete $localStorage.webMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };
    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
        $location.path('/');
    };

});

//angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {

//
//
//    $scope.deleteProductFromScope = function(productIndex){
//            $scope.products.content.splice(productIndex, 1);
//    };
//
//    $scope.deleteProductById = function(productIndexDB, productIndexScope ){
//            $http({
//                    url: contextPath + 'api/v1/products',
//                    method: 'DELETE',
//                    params: {
//                        'p' : productIndexDB
//                    }
//                }).then(function (response){
//                    $scope.deleteProductFromScope(productIndexScope)
//                });
//    };
//
//    $scope.addToCart = function(productId){
//        $http({
//            url: contextPath + 'api/v1/cart/add/' + productId ,
//            method: 'GET'
//        }).then(function (response){
//            $scope.loadCart();
//        });
//    };
//    $scope.removeOneFromCart = function(productId){
//            $scope.cart
//            $http({
//                url: contextPath + 'api/v1/cart/remove/' + productId ,
//                method: 'GET'
//            }).then(function (response){
//                $scope.loadCart();
//            });
//        };
//    $scope.clearCart = function(productId){
//            $http({
//                url: contextPath + 'api/v1/cart/clear/' + productId ,
//                method: 'GET'
//            }).then(function (response){
//                $scope.loadCart();
//            });
//    };
//
//
//
//
//

//
//    $scope.clearUser = function () {
//            delete $localStorage.summerUser;
//            $http.defaults.headers.common.Authorization = '';
//        };
//
//    $scope.tryToLogout = function () {
//            $scope.clearUser();
//            if ($scope.user.username) {
//                $scope.user.username = null;
//            }
//            if ($scope.user.password) {
//                $scope.user.password = null;
//            }
//        };
//
//
//    if ($localStorage.summerUser) {
//            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.summerUser.token;
//            $scope.loadOrders();
//        }
//
//
//    $scope.loadCart();
//    $scope.loadOrders();
//});