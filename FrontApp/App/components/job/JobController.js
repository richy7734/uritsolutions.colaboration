app.controller('JobController', ['$http', '$scope', 'REST_URI', '$location', '$rootScope', '$cookieStore', 'JobServices',
    function ($http, $scope, REST_URI, $location, $rootScope, $cookieStore, JobServices) {

        console.log('Hello to Admin controller....!!');
        var me = this;
        $scope.jobPost = {};
        me.application = {};
        $scope.applications = {};
        me.currentUser = $cookieStore.get('currentUser');
        
        JobServices.getJobs().then(
            function (data) {
                $scope.jobPost = data;
            }, function (error) {
                alert(error);
            }
        );


        $scope.apply = function (jobId) {
            console.log('Applying jon....!');
            me.application.userEmail = me.currentUser.email;
            me.application.jobId = jobId;
            me.application.status = 'A';
            me.application.remark = 'applied';

            JobServices.applyJob(me.application).then(
                function (data) {
                    alert('Your job application has been submitted, a mail will be sent to you for further details.');
                }, function (error) {
                    alert('Ooops... there was an error while submitting your application. Please try again later...!!');
                }
            );
        }

        $scope.getApplications = function(jobId) {
            JobServices.getApplications(jobId).then(
                function(data){
                   $scope.applications = data;
                },function(error){
                    $scope.applications.userEmail = "No applications...!!";
                }
            );
        }


    }]);