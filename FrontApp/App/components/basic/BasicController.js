app.controller('BasicController',['$http', '$scope', 'REST_URI','$location', '$rootScope', '$cookieStore','BasicServices',
 function ($http, $scope, REST_URI, $location, $rootScope, $cookieStore,BasicServices) {

    console.log('Hello to Basic controller....!!');
    var me = this;
    $scope.post = {};
    me.p = {};
    $scope.imageUrl;
    me.comment = {};

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
        $scope.currentUser = $cookieStore.get('currentUser');
        me.p=po;
        console.log($scope.p.title+' selected...!!!');
    }

    me.comment = function(){
        var post = $scope.post;
        post.comments.push(me.comment);

        console.log('The Comment : '+me.comment.content);
        JSON.parse(me.comment);
        console.log('The Comment after parsing : '+me.comment);
        BasicServices.commentSave(me.comment,$scope.p.id).then(
            function(data){
                $scope.post = response.data;
                console.log($scope.post[$scope.p.id].title);
                $scope.imageUrl = REST_URI+'/resources/images/posts';
            },function(error){
                console.log(error);
            }
        );
    }
    

}]);