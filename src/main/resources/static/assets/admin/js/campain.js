let pathCampain = "http://localhost:8080/rest";
app.controller("ctrl-campain", function($scope, $http, $filter){
	$scope.form = {};
	$scope.items = [];
	
	/*--Xóa chiến dịch--*/
	$scope.delete2 = function(campaignId){
        var url = `${pathCampain}/campain/${campaignId}`;
        $http.delete(url).then(resp => {
            // tìm ra phần tử tại vị trí sẽ xóa.
            var index = $scope.items.findIndex(item => item.campaignId == campaignId);
            $scope.items.splice(index, 1); // tại vị trí đó và xóa 1 phần tử
            $scope.reset();
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }
	
	$scope.isDisabled = true;
	
	/*--Hiển thị chiến dịch lên form--*/
	$scope.edit2 = function(campaignId){
		var url = `${pathCampain}/campain/${campaignId}`;
		
		$http.get(url).then(resp => {
			$scope.isDisabled = false;
			$scope.form = resp.data;
			console.log("Success", resp);
		}).catch(errors => {
			console.log("Error", errors);
		});
	}
	
	/*--Hiển thị tất cả chiến dịch--*/
	$scope.findAll2 = function(){
		var url = `${pathCampain}/campain`;
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
		var url = `${pathCampain}/campain`;
		$http.post(url, item).then(resp => {
			$scope.items.push(item);
			$scope.reset();
			console.log("Insert value to Campain Successfully!", resp);
		}).catch(error => {
			console.log("Adding new encountered an error. Please check again.", error);
			alert("Mã chiến dịch đã tồn tại!");
		});
	}
	/*--update*/
	$scope.update2 = function() {
    var item = angular.copy($scope.form);
    var url = `${pathCampain}/campain/${item.campaignId}`;
    $http
        .put(url, item)
        .then((resp) => {
            var index = $scope.items.findIndex((p) => p.campaignId == item.campaignId);
            $scope.items[index] = resp.data;
            $scope.reset2();
            console.log("Cập nhật chiến dịch thành công!", resp);
            alert("Cập nhật chiến dịch thành công!");
        })
        .catch((error) => {
            console.log("Lỗi khi cập nhật chiến dịch!", error);
            alert("Lỗi khi cập nhật chiến dịch!");
        });
};


	/*--Reset form--*/
	$scope.reset2 = function(){
		$scope.form = {};
		$scope.isDisabled = true;

	}
	
	
	/*--Gọi hàm reset--*/
	$scope.reset2();
	
	/*--Gọi hàm hiển thị tất cả học kỳ--*/
	$scope.findAll2();
});