/**
 *	active màu xanh khi chọn tab ở sidebar 
 */
app.controller('SidebarController', function($scope, $location, $http, $window) {
    $scope.isActive = function(route) {
        return $location.path() === route;
    };
    
    $scope.setActive = function(route) {
	
		//Nếu trang được yêu cầu tải nằm trong danh sách này thì bắt buộc phải kiểm tra vai trò
		if(route == '/authority-management' || route == '/account-management'){
			
			//Gọi về yêu cầu xác thực admin
			$http.get("/security/admin").then(resp => {
				
				//Nếu đúng vai trò admin thì tải trang được yêu cầu
				 $location.path(route);
				 
			}).catch(error => {
				
				//Nếu không phải admin thì tiến hành xử lí lỗi!
				$scope.errorHandle(error, route);
				
			});
			
		//Tùy chọn thêm phân quyền
		/**
		//Nếu trang được yêu cầu tải nằm trong danh sách này thì bắt buộc phải kiểm tra vai trò
		 if(route == '?1' || ...){
			
			//Gọi về yêu cầu xác thực vai trò mong muốn (xem ở file WebSecurityConfig)
			$http.get("?2").then(resp => {
				
				//Nếu đúng vai trò thì tải trang được yêu cầu
				 $location.path(route);
				 
			}).catch(error => {
				
				//Nếu không đúng thì tiến hành xử lí lỗi!
				$scope.errorHandle(error, route);
				
			});
		*/
			
		//Nếu không nằm trong danh sách trên	
		}else{
			//Tải trang được yêu cầu
			 $location.path(route);
		}
       
    };
    
    //Hàm gọi xử lí lỗi khi xác thực
    $scope.errorHandle = function(error, route){
	
		//Xuất ra lỗi gặp phải
		console.log(error);
		
		//Load lại yêu cầu để chuyển hướng đến trang đăng nhập
		$window.location.href = route;
	}
  
});