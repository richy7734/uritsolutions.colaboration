app.controller('AdminController', ['$http', '$scope', 'REST_URI', '$location', '$rootScope', '$cookieStore', 'AdminServices',
    function ($http, $scope, REST_URI, $location, $rootScope, $cookieStore, AdminServices) {

        console.log('Hello to Admin controller....!!');
        var me = this;
        me.job = {};
        $scope.users = null;
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

        me.getJob = function() {
            AdminServices.addJob(me.job).then(
                function(data){
                    alert('Job successfully posted...!!');
                },function(error){
                    alert('There was an error in posting the job.');
                }
            );
        }


    }]);