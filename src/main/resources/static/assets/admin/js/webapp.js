var app = angular.module("myapp", ["ngRoute"]);

app.config(function($routeProvider){
	$routeProvider
	.when("", {
		templateUrl: "/assets/admin/index.html"
	})
	.otherwise({
		redirectTo: ""
	});
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