app.controller('AdminController', ['$http', '$scope', 'REST_URI', '$location', '$rootScope', '$cookieStore', 'AdminServices',
    function ($http, $scope, REST_URI, $location, $rootScope, $cookieStore, AdminServices) {

        console.log('Hello to Admin controller....!!');
        var me = this;
        $scope.users = {};
        AdminServices.getUsers().then(
            function (data) {
                $scope.users = data;
            }, function (error) {
                alert(error);
            }
        );


        $scope.approve = function (userId) {
            console.log('Approving user....!');
            AdminServices.approveUser(userId).then(
                function (data) {
                    $scope.users = data;
                }, function (error) {
                    console.log(error);
                }
            );
        }


    }]);