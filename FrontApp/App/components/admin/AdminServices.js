var AdminModule = angular.module('AdminModule',[]);

AdminModule.service('AdminServices',['$http','$q','REST_URI',function($http,$q,REST_URI){

    this.getUsers = function(){
        return $http.post(REST_URI+'/get/users/admin').then(
            function(response){
                return response.data;
            },function(error){
                return error;
            }
        );
    }

    this.approveUser = function(id){
        return $http.post(REST_URI+'/approve/user/'+id).then(
            function(response){
                return response.data;
            },function(error){
                return error;
            }
        );
    }

    

}]);