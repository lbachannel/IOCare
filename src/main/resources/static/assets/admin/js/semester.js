let pathSemester = "http://localhost:8080/rest";
app.controller("ctrl-semester", function($scope, $http, $filter, $window, $rootScope) {
	$scope.form = {};
	$scope.items = [];
	$scope.startTime = new Date();
	$scope.endTime = new Date();

	/*--Xóa học kỳ--*/
	$scope.delete = function(semesterId) {
		var url = `${pathSemester}/semester/${semesterId}`;

		// Hiển thị hộp thoại xác nhận
		var confirmed = window.confirm('Bạn có chắc chắn muốn xóa học kỳ này?');

		if (confirmed) {
			$http.delete(url).then(resp => {
				var index = $scope.items.findIndex(item => item.semesterId == semesterId);
				$scope.items.splice(index, 1);
				$scope.reset();
				console.log("Success", resp);

				// Hiển thị cửa sổ thông báo khi xóa thành công
				window.alert('Xóa học kỳ thành công!');
			}).catch(error => {
				console.log("Error", error);
			});
		}
	};
	
	$scope.located = function(){
		var url = `http://localhost:8080/campaign-management`;
		$http.get(url).then(resp => {
			$window.location.href = 'http://localhost:8080/campaign-management';
		}).catch(errors => {
			console.log("Error", errors);
		});
	}

	$scope.isDisabled = true;

	/*--Hiển thị học kỳ lên form--*/
	$scope.edit = function(semesterId) {
		var url = `${pathSemester}/semester/${semesterId}`;

		$http.get(url).then(resp => {
			$scope.isDisabled = false;
			$scope.form = resp.data;
			$scope.form.startTime = new Date($scope.form.startTime);
			$scope.form.endTime = new Date($scope.form.endTime);
			console.log("Success", resp);
			
		}).catch(errors => {
			console.log("Not OK");
			console.log("Error", errors);
		});
	}

	/*--Hiển thị tất cả học kỳ--*/
	$scope.findAll = function() {
		var url = `${pathSemester}/semester`;
		$http.get(url).then(resp => {
			$scope.items = resp.data;
			console.log("Success", resp)
		}).catch(errors => {
			console.log("Error", errors)
		});
	}


	/*--Gọi API Backend tạo mới học kỳ--*/
	$scope.create = function() {
		var item = angular.copy($scope.form);
		var url = `${pathSemester}/semester`;
		$http.post(url, item).then(resp => {
			$scope.items.push(item);
			$scope.reset();
			console.log("Insert value to Semester Successfully!", resp);
			alert("Thêm mới thành công!");
		}).catch(error => {
			console.log("Adding new encountered an error. Please check again.", error);
			alert("Mã học kì đã tồn tại!");
		});
	}



	/*--Reset form--*/
	$scope.reset = function() {
		$scope.form = {};
		$scope.startTime = '';
		$scope.endTime = '';
		$scope.isDisabled = true;

	}
	//Update form
	$scope.update = function() {
		var semester = angular.copy($scope.form);

		var url = `${pathSemester}/semester/${semester.semesterId}`;

		$http.put(url, semester).then(resp => {
			var index = $scope.items.findIndex(item => item.semesterId == semester.semesterId);
			$scope.items[index] = resp.data;

			console.log("Update value to Semester Successfully!", resp);
			alert("Cập nhật thành công!");
		}).catch(error => {
			console.log("Adding new encountered an error. Please check again.", error);
			alert("Cập nhật không thành công!");
		});
	};



	/*--Gọi hàm reset--*/
	$scope.reset();

	/*--Gọi hàm hiển thị tất cả học kỳ--*/
	$scope.findAll();
});