let pathCampaign = "http://localhost:8080/rest";
app.controller("ctrl-campaign", function($scope, $http, $filter){
	$scope.form = {};
	$scope.items = [];
	
	/*--Xóa chiến dịch--*/
	$scope.delete2 = function(campaignId) {
	  // Hiển thị hộp thoại xác nhận
	  var xacNhan = confirm("Bạn có muốn xóa không?");
	  
	  // Kiểm tra câu trả lời của người dùng
	  if (xacNhan) {
	    var url = `${pathCampaign}/campaign/${campaignId}`;
	    $http.delete(url).then(resp => {
	      // Tìm ra phần tử tại vị trí sẽ xóa.
	      var index = $scope.items.findIndex(item => item.campaignId == campaignId);
	      $scope.items.splice(index, 1); // Xóa 1 phần tử tại vị trí đó
	      $scope.reset();
	      console.log("Success", resp);
	    }).catch(error => {
	      console.log("Error", error);
	    });
	  } else {
	    // Người dùng chọn "Cancel", không thực hiện xóa
	    console.log("Xóa bị hủy");
	  }
	}

	
	$scope.isDisabled = true;
	$scope.isIdDisabled = false;
	
	/*--Hiển thị chiến dịch lên form--*/
	$scope.edit2 = function(campaignId){
		var url = `${pathCampaign}/campaign/${campaignId}`;
		
		$http.get(url).then(resp => {
			$scope.isDisabled = false;
			$scope.isIdDisabled = true;
			$scope.form = resp.data;
			console.log("Success", resp);
		}).catch(errors => {
			console.log("Error", errors);
		});
	}
	
	/*--Hiển thị tất cả chiến dịch--*/
	$scope.findAll2 = function(){
		var url = `${pathCampaign}/campaign`;
		$http.get(url).then(resp => {
			$scope.items = resp.data;
			console.log("Success", resp)
		}).catch(errors => {
			console.log("Error", errors)
		});
	}
	
	/*--Gọi API Backend tạo mới học kỳ--*/
	$scope.create2 = function(){
		var item = angular.copy($scope.form);
		var url = `${pathCampaign}/campaign`;
		$http.post(url, item).then(resp => {
			$scope.items.push(item);
			$scope.reset();
			console.log("Insert value to Campain Successfully!", resp);
		}).catch(error => {
			console.log("Adding new encountered an error. Please check again.", error);
			alert("Mã chiến dịch đã tồn tại!");
		});
	}
	
	$scope.formChanges = false;

	$scope.checkFormChanges = function() {
	  $scope.formChanges = true;
	}
	
	/*--update*/
	$scope.update2 = function() {
	if (!$scope.formChanges) {
    // Không có sự thay đổi, không thực hiện cập nhật
   		return;
  	}
    var item = angular.copy($scope.form);
    $http
        .put(`${pathCampaign}/campaign/${item.campaignId}`, item)
        .then((resp) => {
            var index = $scope.items.findIndex((p) => p.campaignId == item.campaignId);
            $scope.items[index] = resp.data;
            $scope.reset2();
            console.log("Cập nhật chiến dịch thành công!", resp);
            alert("Cập nhật chiến dịch thành công!");
        })
        .catch((error) => {
			$scope.reset2();
            console.log("Lỗi khi cập nhật chiến dịch!", error);
            alert("Lỗi khi cập nhật chiến dịch!");
        });
	};


	/*--Reset form--*/
	$scope.reset2 = function(){
		$scope.form = {};
		$scope.isDisabled = true;
		$scope.isIdDisabled = false;
		$scope.formChanges = false;
	}
	
	
	/*--Gọi hàm reset--*/
	$scope.reset2();
	
	/*--Gọi hàm hiển thị tất cả học kỳ--*/
	$scope.findAll2();
});