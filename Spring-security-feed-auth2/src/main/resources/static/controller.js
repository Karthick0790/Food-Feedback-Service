var formApp = angular.module('formApp', [])

	.controller('formController', function($scope, $http) {
		
		$scope.menu = [
			{"foodName":"orange", "feed":"good"}
		];
		
		$scope.feedInputs = [
			{"foodName":"dummy", "feed":"good"}
		];
		
		$http.get("/name")
		.then(function(response) {
			console.log('name success');
			$scope.username = response.data;
		}, function(response) {
			console.log('name fail');
		});
		
		$http.get("http://localhost:8080/feedback/menu")
		.then(function(response) {
			console.log('success');
			$scope.menu = response.data;
		}, function(response) {
			console.log('fail');
		});

		$scope.formData = {};
		
		$scope.submitFeed = function () {
			$http.post("http://localhost:8080/feedback/add",$scope.formData)
			.then(function(response) {
				console.log('submit success '+response);
			}, function(response) {
				console.log('submit failed '+response);
			});
		};
		
		$scope.submitFeeds = function () {
			$http.post("http://localhost:8080/feedback/add",$scope.menu)
			.then(function(response) {
				console.log('submitFeeds success '+response);
			}, function(response) {
				console.log('submitFeeds failed '+response);
			});
		};
		
	});
