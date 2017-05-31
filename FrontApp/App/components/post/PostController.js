app.controller('PostController', ['PostService', '$scope', '$location', '$rootScope', '$cookieStore',
    function (PostService, $scope, $location, $rootScope, $cookieStore) {
        var me = this;

        me.post = {};
        me.currentUser = {};
        me.getPost = function () {
            console.log("Post Controller reached...!!");
            me.currentUser = $cookieStore.get('currentUser');
            me.post.username = me.currentUser.username;
            me.post.userId = me.currentUser.userId;
            if (me.currentUser != null || me.currentUser != ''){
                PostService.addPost(me.post).then(
                    function (data) {
                        me.message = data.title;
                        console.log(me.message + ' saved successfully...!!!!');
                    },
                    function (error) {
                        console.log(error);
                    }
                );
            }else{
                alert('Sorry you have to login first to Post or comment.....!!');
            }
        }
    }]);