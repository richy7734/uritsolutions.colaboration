var PostModule = angular.module('PostModule', []);

PostModule.service('PostService', ['$http', '$q', 'REST_URI', function ($http, $q, REST_URI) {
    this.addPost = function (post) {
        console.log('Post service reached...!!!');
        console.log('Posting the data to the URL: ' + REST_URI + '/save/post');

        var deferred = $q.defer();

        return $http.post(REST_URI + '/save/post',post).then(
            function (response) {
                return response.data;
            },null
        );
    }
    this.uploadFileToUrl = function (file, post) {
        var fd = new FormData();
        fd.append('file', file);

        $http({
            method: 'POST',
            url: REST_URI + '/image/upload/post/' + post.id, // The URL to Post.
            headers: { 'Content-Type': undefined }, // Set the Content-Type to undefined always.
            data: fd,
            transformRequest: function (data, headersGetterFunction) {
                return data;
            }
        }).then(
            function (response) {
                alert(response.data);
            }, function (error) {
                alert(error);
            }
        );

    }

   

    
}]);