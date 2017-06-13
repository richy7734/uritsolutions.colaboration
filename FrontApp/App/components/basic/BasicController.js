app.controller('BasicController',['$http', '$scope', 'REST_URI','$location', '$rootScope', '$cookieStore','BasicServices',
 function ($http, $scope, REST_URI, $location, $rootScope, $cookieStore,BasicServices) {

    console.log('Hello to Basic controller....!!');
    var me = this;
    $scope.post = {};
    $scope.p = {};
    $scope.imageUrl;
    me.comment = {};
    $scope.comments = {};
    $http.get(REST_URI + '/get/post').then(
        function (response) {
            console.log('Post fectched sucessfully...!!!');
            $scope.post = response.data;
            console.log($scope.post.title);
            $scope.imageUrl = REST_URI+'/resources/images/posts';
        
        }, function (error) {
            console.log('Error in post fetching...!!!');
        });

    $scope.getComment = function(id){
        $scope.currentUser = $cookieStore.get('currentUser');
        $scope.p.id=id;
        BasicServices.getComments(id).then(
            function(data){
                $scope.comments = data;
            },function(error){
                console.log(error);
            }
        );
    }

    me.comment = function(){
        
        me.comment.pid= $scope.p.id;
        me.comment.username = $scope.currentUser.username;
        console.log(me.comment.pid+' '+me.comment.username+' '+me.comment.cntent);
        BasicServices.commentSave(me.comment).then(
            function(data){
                $scope.comments = data;
            },function(error){
                console.log(error);
            }
        );
    }
    

}]);