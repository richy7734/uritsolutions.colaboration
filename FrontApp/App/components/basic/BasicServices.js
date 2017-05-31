var BasicModule =  angular.module('BasicModule',[]);

    BasicModule.service('BasicServices',['$http','$q','REST_URI',function($http,$q,REST_URI){

        this.getPost = function(post){
        console.log('Post service reached...!!!!');
        console.log('Get post post module...!!');

        

        return $http.get(REST_URI + '/get/post/NVIDIA GTX 1080',post).then(
            function (response) {
                console.log('Post fectched sucessfully...!!!');
                return response.data;
            }, function (error) {
                console.log('Error in post fetching...!!!');
                return error;
            });
        }


}]);
