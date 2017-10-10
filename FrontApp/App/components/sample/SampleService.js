var SampleModule = angular.module('SampleModule',[]);

SampleModule.service('SampleService',['$http','REST_URI',function($http,REST_URI){
    this.senddata = function(data){
        return $http.post(REST_URI+'/get/sample/data',data).then(
            function(response){
                return response.data;
            },null
            
        );
    }
}])

