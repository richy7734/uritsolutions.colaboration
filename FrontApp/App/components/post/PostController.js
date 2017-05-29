app.controller('PostController',['PostService',function(PostService){
    var me = this;

    me.post = {};
    me.getPost = function(){
        console.log("Post Controller reached...!!");
        PostService.addPost(me.post).then(
            function (data) {
                me.message = data.title;
                console.log(me.message+' saved successfully...!!!!');
            },
            function (error) {
                console.log(error);
            }
        );   
    }
}]);