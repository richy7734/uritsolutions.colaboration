app.controller('BasicController',['$http', '$scope', 'REST_URI','$location', '$rootScope', '$cookieStore','BasicServices',
 function ($http, $scope, REST_URI, $location, $rootScope, $cookieStore,BasicServices) {

    console.log('Hello to Basic controller....!!');
    var me = this;
    $scope.post = {};
    me.p = {};
    $scope.imageUrl;
    $scope.comments = {};
    me.comment = {};
    $scope.currentUser = $cookieStore.get('currentUser');
    $http.get(REST_URI + '/get/post').then(
        function (response) {
            console.log('Post fectched sucessfully...!!!');
            $scope.post = response.data;
            console.log($scope.post.title);
            $scope.post.forEach(function(element) {
                console.log(element);
            }, this);
            $scope.imageUrl = REST_URI+'/resources/images/posts';
        
        }, function (error) {
            console.log('Error in post fetching...!!!');
        });

    $scope.getComment = function(po){
        
        me.p=po;
        console.log(me.p.title+' selected...!!!');
        BasicServices.getComments(me.p.id).then(
            function(data){
                $scope.comments = data;
            },null
        );
    }

    me.postComment = function(){
        me.comment.user = $scope.currentUser;
        console.log("Comment : "+me.comment);
        BasicServices.commentSave(me.comment,me.p.id).then(
            function(data){
                $scope.comments = data;
                $scope.imageUrl = REST_URI+'/resources/images/posts';
            },function(error){
                console.log(error);
            }
        );
    }
    

}]);