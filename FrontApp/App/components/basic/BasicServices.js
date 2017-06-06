var BasicModule =  angular.module('BasicModule',[]);

    BasicModule.service('BasicServices',['$http','$q','REST_URI',function($http,$q,REST_URI){

    this.getComments = function(id){
        return $http.post(REST_URI+'/get/comments/'+id).then(
            function(response){
                return response.data;
            },function(error){
                return error;
            }
        );
    }

    this.commentSave = function(comment){
        console.log('The comment is :'+comment.content);
        return $http.post(REST_URI+'/comment/save/'+comment.pid+'/'+comment.username,comment.content).then(
            function(response){
                return response.data;
            },function(error){
                return error;
            }
        );
    }

    

}]);
