var app = angular.module("myapp", ["ngRoute"]);

app.config(function($routeProvider, $locationProvider){
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

app.run(function ($rootScope) {
    $rootScope.$on('$routeChangeStart', function () {
        $rootScope.loading = true;
    });
    $rootScope.$on('$routeChangeSuccess', function () {
        $rootScope.loading = false;
    });
    $rootScope.$on('$routeChangeError', function () {
        $rootScope.loading = false;
        alert("Lỗi");
    });
});