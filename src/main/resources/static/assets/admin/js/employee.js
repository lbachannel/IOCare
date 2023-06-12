let pathSemester = "http://localhost:8080/rest";
app.controller("ctrl-employee", function($scope, $http) {
	$scope.form = {};
	$scope.items = [];

	$scope.create = function() {
		var item = angular.copy($scope.form);
		var url = `${pathSemester}/employee`;
		$http.post(url, item).then(resp => {
			$scope.items.push(item);
			$scope.reset();
			console.log("Insert Successfully!", resp);
			alert("Thêm mới thành công!");
		}).catch(error => {
			console.log("Adding new encountered an error. Please check again.", error);
			alert("Email đã được sử dụng");
		});
	}





});