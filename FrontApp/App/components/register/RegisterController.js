
app.controller('RegisterController', ['RegisterService','$location', function (RegisterService,$location) {

    var me = this;
    me.user = {};
    
    console.log('Hello to register controller: ');
    me.register = function () {
        console.log('Register button clicked by :'+me.user.name);

        RegisterService.register(me.user).then(
            function (data) {
                me.message = 'Thanks' + data.name ;
                console.log(me.message);
                alert('Thank you '+data.name+'. Please wait until admin approves your account.');
                $location.path('/home');
            },
            function (error) {
                console.log(error);
                alert('Account already present.... Please select different username or email.');
            }
        );


    }
}]);