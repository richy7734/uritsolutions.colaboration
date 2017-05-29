var NavBarModule = angular.module('NavBarModule',[]);

app.controller('NavBarController',['$scope','$location', '$rootScope', '$cookieStore',
    function($scope,$location, $rootScope, $cookieStore){
        var me = this;
        me.currentUser = '';
        me.btn = {};
    $scope.navActive = function(nav){
        if(nav == 'home'){
            $scope.home = 'active';
            $scope.profile = '';
            $scope.register = '';
        }else if (nav == 'profile') {
            $scope.home = '';
            $scope.profile = 'active';
            $scope.register = '';
        }else if(nav == 'register'){
            $scope.home = '';
            $scope.profile = '';
            $scope.register = 'active';
        }
    }

    me.currentUser = $cookieStore.get('currentUser');
    if(me.currentUser != null){
        me.btn = {lable : 'Logout',url : '#!/logout/', dataTarget : ''};
    }else{
        me.btn = {lable : 'Login',url : '', dataTarget : '#loginModal'}; 
    }

    me.logout = function() {
								console.log("logout")
								$rootScope.currentUser = {};
								$cookieStore.remove('currentUser');
								UserService.logout()
								$location.path('/');

							}


}]);