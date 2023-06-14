let pathSemester = "http://localhost:8080/rest";
app.controller("ctrl-semester", function($scope, $http, $filter, $timeout) {
	$scope.form = {};
	$scope.items = [];
	$scope.startTime = null;
	$scope.endTime = null;

	/*--Xóa học kỳ--*/
	$scope.delete = function(semesterId) {
		var xacNhan = confirm("Bạn có muốn xóa không?");
		if (xacNhan) {
			var url = `${pathSemester}/semester/${semesterId}`;
			$http.delete(url).then(resp => {
				// tìm ra phần tử tại vị trí sẽ xóa.
				var index = $scope.items.findIndex(item => item.semesterId == semesterId);
				$scope.items.splice(index, 1); // tại vị trí đó và xóa 1 phần tử
				$scope.reset();
				console.log("Success", resp);
			}).catch(error => {
				console.log("Error", error);
				$scope.hideErrorAfterDelay();
			});
		} else {
			// Người dùng chọn "Cancel", không thực hiện xóa
			console.log("Xóa bị hủy");
		}
	}

	$scope.isDisabled = true;

	$scope.formatDate = function(dateString) {
	  if (!dateString) return null;
	  var date = new Date(dateString);
	  var formattedDate = date.toISOString().substring(0, 10);
	  return formattedDate;
	};
	/*--Hiển thị học kỳ lên form--*/
	$scope.edit = function(semesterId) {
	    var url = `${pathSemester}/semester/${semesterId}`;
	
	    $http.get(url).then(resp => {
	        $scope.isDisabled = false;
	        $scope.isIdDisabled = true;
	        $scope.form = resp.data;
	        $scope.startTime = new Date(resp.data.startTime); // Chuyển đổi sang định dạng ngày
	        $scope.endTime = new Date(resp.data.endTime); // Chuyển đổi sang định dạng ngày
	        console.log("Success", resp);
	    }).catch(errors => {
	        console.log("Error", errors);
	        $scope.hideErrorAfterDelay();
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

	$scope.isDuplicateSemesterId = false;
	$scope.showMaxLengthError = false;

	$scope.checkDuplicateSemesterId = function(campaignId) {
		$scope.isDuplicateSemesterId = $scope.items.some(function(item) {
			return item.semesteri === campaignId;
		});
	};

	$scope.hideErrorAfterDelay = function() {
		$timeout(function() {
			$scope.submitted = false;
		}, 5000);
	};




	/*--Gọi API Backend tạo mới học kỳ--*/
$scope.create = function() {
    if ($scope.myForm.$valid) {
        var item = angular.copy($scope.form);
        var url = `${pathSemester}/semester`;

        if (item.semesterId.length > 5) {
            $scope.myForm.semesterId.$setValidity('length', false);
            $timeout(function() {
                $scope.myForm.semesterId.$setValidity('length', true);
            }, 5000);
            return;
        }

        // Check duplicate semesterId
        var duplicate = $scope.items.some(function(existingItem) {
            return existingItem.semesterId === item.semesterId;
        });

        if (duplicate) {
            $scope.myForm.semesterId.$setValidity('duplicate', false);
            $timeout(function() {
                $scope.myForm.semesterId.$setValidity('duplicate', true);
            }, 5000);
            return;
        }

        $http.post(url, item).then(resp => {
            $scope.items.push(item);
            console.log("Insert value to Semester Successfully!", resp);
            alert("Thêm mới thành công!");
            $scope.reset();
        }).catch(error => {
            console.log("Adding new encountered an error. Please check again.", error);
            alert("Đã xảy ra lỗi khi thêm mới học kì. Vui lòng kiểm tra lại.");
        });
    } else {
        $scope.submitted = true;
        $scope.hideErrorAfterDelay();
    }
};





	$scope.reset = function() {
	  $scope.form = {};
	  $scope.startTime = ""; // Thay đổi thành null để xóa giá trị ngày bắt đầu
	  $scope.endTime = ""; // Thay đổi thành null để xóa giá trị ngày kết thúc
	  $scope.isDisabled = true;
	  $scope.isIdDisabled = false;
	  $scope.formChanges = false;
	
	  if ($scope.myForm) {
	    $scope.myForm.semesterId.$setValidity('duplicate', true);
	    $scope.myForm.semesterId.$setValidity('length', true);
	    $scope.myForm.$setPristine();
	    $scope.myForm.$setUntouched();
	  }
	};

	$scope.isDisabled = true;
	$scope.isIdDisabled = false;

	$scope.formChanges = false;

	$scope.checkFormChanges = function() {
	  $scope.formChanges = true;
	}
	//Update form
	$scope.update = function() {
		if (!$scope.formChanges) {
    // Không có sự thay đổi, không thực hiện cập nhật
   		return;
  	}
    var item = angular.copy($scope.form);
    $http
        .put(`${pathSemester}/semester/${item.semesterId}`, item)
        .then((resp) => {
            var index = $scope.items.findIndex((p) => p.semesterId == item.semesterId);
            $scope.items[index] = resp.data;
            console.log("Cập nhật học kì thành công!", resp);
			$scope.reset();
            alert("Cập nhật học kì thành công!");
        })
        .catch((error) => {
            console.log("Lỗi khi cập nhật học kì!", error);
            $scope.reset();
            alert("Lỗi khi cập nhật học kì!");
        });
	};





	/*--Gọi hàm reset--*/
	$scope.reset();

	/*--Gọi hàm hiển thị tất cả học kỳ--*/
	$scope.findAll();
});