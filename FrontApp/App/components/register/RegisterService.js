var RegisterModule = angular.module('RegisterModule',[]);

RegisterModule.service('RegisterService',['$http','$q','REST_URI',function($http,$q,REST_URI){

    this.register = function(user){

        console.log('Register Service reached.');
        console.log('Posting the data to the URL: '+REST_URI+'/add/user');

        var deferred = $q.defer();

        return $http.post(REST_URI+'/add/user',user).then(
            function(response){
                return (response.data);

            },function(error){
                return (error);
            }
        );
    }
}]); 