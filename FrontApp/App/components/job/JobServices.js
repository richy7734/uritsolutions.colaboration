var JobModule = angular.module('JobModule',[]);

JobModule.service('JobServices',['$http','$q','REST_URI',function($http,$q,REST_URI){

    this.getJobs = function(){
        return $http.post(REST_URI+'/list/jobs').then(
            function(response){
                return response.data;
            },function(error){
                return error;
            }
        );
    }

    this.applyJob = function(application){
        return $http.post(REST_URI+'/add/job/application',application).then(
            function(response){
                return response.data;
            },function(error){
                return error;
            }
        );
    }

    this.getApplications = function(jobId){
        return $http.post(REST_URI+'/get/applications/'+jobId,).then(
            function(response){
                return response.data;
            },function(error){
                return error;
            }
        );
    }

    

}]);