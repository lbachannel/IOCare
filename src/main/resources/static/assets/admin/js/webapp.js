var app = angular.module("myapp", ["ngRoute"]);

app.controller("dropdownController", function($scope, $window) {
	$scope.logoff = function() {
		console.log("oke");
		$window.location.href = 'http://localhost:8080/security/logoff';
	}
});

app.config(function($routeProvider, $locationProvider) {

	$routeProvider
		.when("/", {
			templateUrl: "/assets/admin/layout/home.html"
		})
		.when("/campaign-management", {
			templateUrl: "/assets/admin/layout/campaign-management.html"
		})
		.when("/student-support", {
			templateUrl: "/assets/admin/layout/student-support.html"
		})
		.when("/account-management", {
			templateUrl: "/assets/admin/layout/account-management.html"
		})
		.when("/data-management", {
			templateUrl: "/assets/admin/layout/data-management.html"
		})
		.when("/authority-management", {
			templateUrl: "/assets/admin/layout/authority-management.html"
		})
		.otherwise({
			redirectTo: "/"
		});

	$locationProvider.html5Mode(true);
});

app.run(function($rootScope) {

	$rootScope.$on('$routeChangeStart', function() {
		$rootScope.loading = true;
	});
	$rootScope.$on('$routeChangeSuccess', function() {
		$rootScope.loading = false;
	});
	$rootScope.$on('$routeChangeError', function() {
		$rootScope.loading = false;
	});
});
// Điều hướng trang 
app.run(function($rootScope, $location, $window, $http) {
	// Xử lý trước khi reload trang
	$rootScope.$on('$locationChangeStart', function(event) {
		// Thực hiện các xử lý bạn muốn trước khi trang được reload
		// Ví dụ: Hiển thị thông báo xác nhận hoặc lưu dữ liệu trước khi reload
		$http.get('/security').then(function(response) {
			var userInfo = response.data;
			if (userInfo != true) {
				var url = `http://localhost:8080/security`;
				$http.get(url).then(resp => {
					$window.location.href = 'http://localhost:8080/security';
				}).catch(errors => {
					console.log("Error", errors);
				});
			}
		});
	});
});


$scope.isDisabled = true;
$scope.located();