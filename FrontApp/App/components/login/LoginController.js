app.controller('LoginController', ['$scope', 'LoginService', '$location', '$rootScope', '$cookieStore', '$http',
    function ($scope, LoginService, $location, $rootScope, $cookieStore, $http) {
        var me = this;

        me.user = {};
        me.login = function () {
            console.log("Login Controller reached...!!");
            LoginService.login(me.user).then(

                function (data) {

                    me.user = data;
                    console
                        .log("user.errorCode: " + me.user.errorCode)
                    if (me.user.errorCode == "404") {
                        alert(me.user.errorMessage)

                        me.user.id = "";
                        me.user.password = "";

                    } else {
                        console.log("Valid credentials. Navigating to home page")
                        $rootScope.currentUser = me.user
                        $http.defaults.headers.common['Authorization'] = 'Basic '+ $rootScope.currentUser;
                        $cookieStore
                            .put('currentUser', $rootScope.currentUser);
                        $rootScope.$emit('CallLoginBtn');
                        $location.path('/home');

                    }

                },
                function (errResponse) {

                    console.error('Error while authenticate Users');
                    alert(errResponse.data.error);
                });

        }
        //Fetch all users

        me.getAllUsers = function () {
            console.log("Get all users..!!")
            UserService
                .getAllUsers()
                .then(
                function (data) {
                    self.users = data;
                },
                function (errResponse) {
                    console.error('Error while fetching Users');
                });
        };


    }]);