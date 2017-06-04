
app.controller('FriendsController', ['FriendsService', '$location', '$rootScope', '$cookieStore', '$scope', '$http', '$routeParams',
    function (FriendsService, $location, $rootScope, $cookieStore, $scope, $http, $routeParams) {
        var me = this;
        me.friends = {};
        me.friend = {};
        $scope.users = {};
        me.event = '';
        me.id = '';
        console.log("Friends Controller Invoked");
        me.currentUser = $cookieStore.get('currentUser');

        $rootScope.$on('CallFrndRequests',function(){
            console.log('Friend Controller reached...!!');
            FriendsService.getFrndRequests(me.currentUser).then(
                function(data){
                    me.friends = data;
                    
                },function(error){
                    alert(error);
                }
            );
        });

        me.accept = function(id){
            console.log('Accepting request of ID: '+ id);
            FriendsService.acceptRequest(id).then(
                function(data){
                    alert('Request accepted..!!');
                },
                function(error){
                    alert(error);
                }
            );
        }
      

        if ($routeParams.id != null || $routeParams != 0 || $routeParams != '0') {
            if (me.currentUser != null || me.currentUser != ''){
                me.friend.userId = me.currentUser.id;
                me.friend.frndId = $routeParams.id;
                FriendsService.sendRequest(me.friend).then(
                    function (data) {
                        me.friend = data;
                    },
                    function (error) {
                        alert(error);
                    }
                );
            }
        }
    }
]);