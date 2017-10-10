var BasicModule =  angular.module('BasicModule',[]);

    BasicModule.service('BasicServices',['$http','$q','REST_URI',function($http,$q,REST_URI){

    this.getComments = function(id){
        console.log('Posting to the URL : '+REST_URI+'/get/comments/'+id);
        return $http.post(REST_URI+'/get/comments/'+id).then(
            function(response){
                return response.data;
            },function(error){
                return error;
            }
        );
    }

    this.commentSave = function(comment,pid){
        console.log('The comment is :'+comment.content);
        console.log('Posting to the URL : '+REST_URI+'/comment/save/'+pid);
        return $http.post(REST_URI+'/comment/save/'+pid,comment).then(
            function(response){
                return response.data;
            },function(error){
                return error;
            }
        );
    }

    

}]);
