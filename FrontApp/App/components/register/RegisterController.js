
app.controller('RegisterController', ['RegisterService', function (RegisterService) {

    var me = this;
    me.user = {};
    console.log('Hello to register controller: ');
    me.register = function () {
        console.log('Register button clicked by :'+me.user.name);

        RegisterService.register(me.user).then(
            function (data) {
                me.message = 'Thanks' + data.name ;
                console.log(me.message);
            },
            function (error) {
                console.log(error);
            }
        );


    }
}]);