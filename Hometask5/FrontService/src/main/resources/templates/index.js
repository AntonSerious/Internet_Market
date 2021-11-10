angular.module('app').controller('storeController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8191/FrontService/';
    $scope.loadPage = function(){
        $http({
            url: contextPath + 'products',
            method: 'GET'
        }).then(function (response){
            console.log(response);
            $scope.products = response.data
        });
    };
    $scope.generatePagesIndexes = function(startPage, endPage){
        let arr = [];
        for(let i = startPage; i< endPage + 1; i++){
            arr.push(i);
        }
        return arr;
    }
    $scope.loadPage();
});
