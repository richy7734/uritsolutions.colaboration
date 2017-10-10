app.controller('BasicController',['$http', '$scope', 'REST_URI','$location', '$rootScope', '$cookieStore','BasicServices',
 function ($http, $scope, REST_URI, $location, $rootScope, $cookieStore,BasicServices) {

    console.log('Hello to Basic controller....!!');
    var me = this;
    $scope.post = {};
    $scope.p = {};
    $scope.imageUrl;
    me.comment = {};
    
    $http.get(REST_URI + '/get/post').then(
        function (response) {
            console.log('Post fectched sucessfully...!!!');
            $scope.post = response.data;
            console.log($scope.post.title);
            $scope.imageUrl = REST_URI+'/resources/images/posts';
        
        }, function (error) {
            console.log('Error in post fetching...!!!');
        });

    $scope.getComment = function(po){
        $scope.currentUser = $cookieStore.get('currentUser');
        $scope.p=po;
        console.log($scope.p.title+' selected...!!!');
    }

    me.comment = function(){
        
        me.comment.user = $scope.currentUser;
        me.comment.post = $scope.p;
        console.log('The Comment : '+me.comment);
        JSON.parse(md.comment);
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