var LoginModule = angular.module('LoginModule',[]);

LoginModule.service('LoginService', ['$http', '$q', 'REST_URI', function ($http, $q, REST_URI) {

    this.login = function (user) {
        console.log('Login service reached...!!!');
        console.log('Logging in with the URL : ' + REST_URI + '/login');

        //var deferred = $q.defer();

        return $http.post(REST_URI + '/login',user).then(
            function (response) {
                console.log('Login service got the user logged in : '+response.data.name);
                //deferred.resolve(response.data);
                return response.data;
            }, null
            
        );   
    }

    this.logout = function(user){
        console.log('Login service reached...!!!');
        console.log('Logging out with the URL : ' + REST_URI + '/logout');

        $http.post(REST_URI + '/logout',user).then(
            function (response) {
                console.log('Login service got the user '+response.data.name+'logged out.');
            }, null
            
        )
    }

   

    
}]);