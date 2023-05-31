/**
 *	active màu xanh khi chọn tab ở sidebar 
 */
app.controller('SidebarController', function($scope, $location) {
    $scope.isActive = function(route) {
        return $location.path() === route;
    };
    
    $scope.setActive = function(route) {
        $location.path(route);
    };
});