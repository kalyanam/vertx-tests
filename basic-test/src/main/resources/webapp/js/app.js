var app = angular.module('MyApp',[]);

app.controller('DataController', ['$scope', '$http', function($scope, $http){
    var vm = this;

    $http.get('http://localhost:8081/products')
    .then(function(data){
        vm.data = data;
    }, function(error){
        vm.hasFailed = true;
        vm.errorMessage = error;
    });
}]);