var garageApp = angular.module('garageApp', ['ngMaterial', 'ngTable']);

garageApp.controller('AppCtrl', function ($scope, $mdDialog, $http) {
    $scope.status = '  ';
    $scope.customFullscreen = false;
    $scope.car = {};

    $http({method : 'GET',
        url : 'http://localhost:8085/garage/summary'})
        .then(function successCallback(response) {
            $scope.numberOfCars = response.data.numberOfCars;
            $scope.valueOfCars =  response.data.valueOfCars;
            $scope.lentCars =  response.data.lentCars;
            $scope.repairedCars =  response.data.repairedCars;
        }, function errorCallback(response) {
            alert("Error");
        });

    $scope.showAllCarsDialog = function (ev) {
        $mdDialog.show({
            controller: showAllCarsDialogController,
            templateUrl: 'all_cars.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose: true,
            fullscreen: $scope.customFullscreen
        })
    };

    $scope.showAddCarsDialog = function (ev) {
        $mdDialog.show({
            controller: showAddCarsDialogController,
            templateUrl: 'add_cars.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose: true,
            fullscreen: $scope.customFullscreen
        })
    };

    function showAllCarsDialogController($scope, $mdDialog) {
        $http({
            method : 'GET',
            url : 'http://localhost:8085/garage/car',
            headers:{'Access-Control-Allow-Origin': '*'}})
            .then(function successCallback(response) {
                $scope.cars = response.data;
            }, function errorCallback(response) {
                alert("Error");
            });

        $scope.cancel = function () {
            $mdDialog.cancel();
        };

        $scope.showImage = function (image) {
            var srcPath = '<img src=data:image/png;base64,' + image + '>';

            var windowHandle = window.open('about:blank','windowName','width=250, height=250');
            windowHandle.document.write(srcPath);
        };
    }

    function showAddCarsDialogController($scope, $mdDialog) {
        $scope.cancel = function () {
            $mdDialog.cancel();
        };

        $scope.addCar = function () {
            $http({
                method : 'POST',
                url : 'http://localhost:8085/garage/car',
                data: $scope.car})
        };
    }



});