var NavBarModule = angular.module('NavBarModule',[]);

app.controller('NavBarController',['$scope','$location', '$rootScope', '$cookieStore','LoginService',
    function($scope,$location, $rootScope, $cookieStore,LoginService){
        var me = this;
        me.currentUser = '';
        me.currentUser = null;
        me.name = '';
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

    me.searchFrnds = function(){
        $rootScope.$emit('CallSearchFriends',me.name);
    }

    me.frndRequests = function(){
        console.log('Redirecting to Friend controller...!!');
        $location.path('/listUser/0');
        $rootScope.$emit('CallFrndRequests');
    }
}]);