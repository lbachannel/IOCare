let pathSemester = "http://localhost:8080/rest";
app.controller("ctrl-semester", function($scope, $http, $filter){
	$scope.form = {};
	$scope.items = [];
	$scope.startTime = new Date();
	$scope.endTime = new Date();
	
	/*--Xóa học kỳ--*/
	$scope.delete = function(semesterId){
        var url = `${pathSemester}/semester/${semesterId}`;
        $http.delete(url).then(resp => {
            // tìm ra phần tử tại vị trí sẽ xóa.
            var index = $scope.items.findIndex(item => item.semesterId == semesterId);
            $scope.items.splice(index, 1); // tại vị trí đó và xóa 1 phần tử
            $scope.reset();
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }
	
	$scope.isDisabled = true;
	
	/*--Hiển thị học kỳ lên form--*/
	$scope.edit = function(semesterId){
		var url = `${pathSemester}/semester/${semesterId}`;
		
		$http.get(url).then(resp => {
			$scope.isDisabled = false;
			$scope.form = resp.data;
			$scope.startTime = moment($scope.form.startTime, 'YYYY-MM-DD').toDate();
        $scope.endTime = moment($scope.form.endTime, 'YYYY-MM-DD').toDate();
			console.log("Success", resp);
		}).catch(errors => {
			console.log("Error", errors);
		});
	}
	
	/*--Hiển thị tất cả học kỳ--*/
	$scope.findAll = function(){
		var url = `${pathSemester}/semester`;
		$http.get(url).then(resp => {
			$scope.items = resp.data;
			console.log("Success", resp)
		}).catch(errors => {
			console.log("Error", errors)
		});
	}

	
	/*--Gọi API Backend tạo mới học kỳ--*/
	$scope.create = function(){
		var item = angular.copy($scope.form);
		item.startTime = $filter('date')($scope.startTime, 'yyyy-MM-dd');
		var dateString = item.startTime;
		var trimmedDateString = dateString.slice(0, 10);
		item.endTime = $filter('date')($scope.endTime, 'yyyy-MM-dd');
		var dateString1 = item.endTime;
		var trimmedDateString1 = dateString1.slice(0, 10);
		var url = `${pathSemester}/semester`;
		$http.post(url, item).then(resp => {
			$scope.items.push(item);
			$scope.reset();
			console.log("Insert value to Semester Successfully!", resp);
		}).catch(error => {
			console.log("Adding new encountered an error. Please check again.", error);
			alert("Mã học kì đã tồn tại!");
		});
	}
	


	/*--Reset form--*/
	$scope.reset = function(){
		$scope.form = {};
		$scope.startTime = '';
		$scope.endTime = '';
		$scope.isDisabled = true;

	}
	
	
	/*--Gọi hàm reset--*/
	$scope.reset();
	
	/*--Gọi hàm hiển thị tất cả học kỳ--*/
	$scope.findAll();
});