var RegisterModule = angular.module('RegisterModule',[]);

RegisterModule.service('RegisterService',['$http','$q','REST_URI',function($http,$q,REST_URI){

    this.register = function(user){

        console.log('Register Service reached.');
        console.log('Posting the data to the URL: '+REST_URI+'/add/user');

        var deferred = $q.defer();

        $http.post(REST_URI+'/add/user',user).then(
            function(responce){
                deferred.resolve(response.data);

            },function(error){
                deferred.resolve(error);
            }
        );

        return deferred.promise;
    }
}]); 