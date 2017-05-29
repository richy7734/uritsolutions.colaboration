var PostModule = angular.module('PostModule', []);

PostModule.service('PostService', ['$http', '$q', 'REST_URI', function ($http, $q, REST_URI) {
    this.addPost = function (post) {
        console.log('Post service reached...!!!');
        console.log('Posting the data to the URL: ' + REST_URI + '/save/post');

        var deferred = $q.defer();

        $http.post(REST_URI + '/save/post',post).then(
            function (response) {
                deferred.resolve(response.data);
            }, function (error) {
                deferred.resolve(error);
            });
    }

   

    
}]);