app.controller('BasicController', function ($http, $scope, REST_URI) {

    console.log('Hello to Basic controller....!!');
    $scope.post = {};
    $http.get(REST_URI + '/get/post').then(
        function (response) {
            console.log('Post fectched sucessfully...!!!');
            $scope.post = response.data;
            console.log($scope.post.title);
        
        }, function (error) {
            console.log('Error in post fetching...!!!');
        });

});