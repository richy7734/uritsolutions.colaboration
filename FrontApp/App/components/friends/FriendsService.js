var FriendsModule = angular.module('FriendsModule',[]);

FriendsModule.service('FriendsService',['$http','$timeout','REST_URI', function($http, $timeout, REST_URI){
    this.getFrndRequests = function(user){
        console.log('Posting request to : '+REST_URI+'/list/friends')
        return $http.post(REST_URI+'/list/friends',user).then(
            function(response){
                
                return response.data;
            }
            ,function(error){
                
                return error;
            }
        );
    }

    this.searchFriends = function(name){
        return $http.post(REST_URI+'/search',name).then(
            function(response){
                
                return response.data;
            }
            ,null
        );
    }

    this.sendRequest = function(friends){
        return $http.post(REST_URI+'/send/friend/request',friends).then(
            function(response){
                
                return response.data;
            }
            ,function(error){
                return error;
            }
        );
    }

    this.acceptRequest = function(id){
        return $http.post(REST_URI+'/friend/accept',id).then(
            function(response){
                
                return response.data;
            }
            ,function(error){
                return error;
            }
        );
    }

}]);
