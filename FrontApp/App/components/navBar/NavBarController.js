var NavBarModule = angular.module('NavBarModule',[]);

app.controller('NavBarController',['$scope','$location', '$rootScope', '$cookieStore','LoginService',
    function($scope,$location, $rootScope, $cookieStore,LoginService){
        var me = this;
        me.currentUser = '';
        me.currentUser = null;
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

    $rootScope.$on('CallLoginBtn',function(){
        me.currentUser = $cookieStore.get('currentUser');
    });

    me.logout = function(){
        console.log("logout")
        $rootScope.currentUser = {};
        $cookieStore.remove('currentUser');
        LoginService.logout(me.currentUser);
        me.currentUser = null;
        $location.path('/home');
    }

}]);