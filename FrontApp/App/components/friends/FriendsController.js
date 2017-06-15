
app.controller('FriendsController', ['FriendsService', '$location', '$rootScope', '$cookieStore', '$scope', '$http', '$routeParams','REST_URI',
    function (FriendsService, $location, $rootScope, $cookieStore, $scope, $http, $routeParams,REST_URI) {
        var me = this;
        me.friends = {};
        me.friend = {};
        me.users = {};
        me.event = '';
        me.myFriends = {};
        me.id = '';
        me.friends = null;
        me.users = null;
        console.log("Friends Controller Invoked");
        me.currentUser = $cookieStore.get('currentUser');
        me.imageUrl = REST_URI+'/resources/images/'+me.currentUser.id+'.jpg';
        me.imgPath = REST_URI+'/resources/images/';
        FriendsService.getFrndRequests(me.currentUser).then(
            function (data) {
                me.myFriends = data;

            }, function (error) {
                alert(error);
            }
        );

        me.uploadFile = function () {
           
            console.log('file is ');
            console.dir($scope.myFile);
            FriendsService.uploadFileToUrl($scope.myFile,me.currentUser);
            me.imageUrl = REST_URI+'/resources/images/'+me.currentUser.id+'.jpg';

        };

        me.myFrnds = function () {
            me.users = null;
            me.friends = null;
            FriendsService.getFrndRequests(me.currentUser).then(
                function (data) {
                    me.myFriends = data;

                }, function (error) {
                    alert(error);
                }
            );
        }

        me.getUsers = function () {
            me.friends = null;
            me.myFriends = null;
            console.log('Fetching Users....!!');
            FriendsService.getUsers().then(
                function (data) {
                    console.log('Users fetched');
                    me.users = data;

                }, function (error) {
                    alert(error);
                }
            );
        }

        me.frndRequests = function () {
            me.users = null;
            me.myFriends = null;
            console.log('Friend Controller reached...!!');
            FriendsService.getFrndRequests(me.currentUser).then(
                function (data) {
                    me.friends = data;

                }, function (error) {
                    alert(error);
                }
            );
        }

        me.accept = function (id) {
            console.log('Accepting request of ID: ' + id);
            FriendsService.acceptRequest(id,me.currentUser.id).then(
                function (data) {
                    alert('Request accepted..!!');
                },
                function (error) {
                    alert(error);
                }
            );
        }
        me.sendRequest = function (id) {

                me.friend.userId = me.currentUser.id;
                me.friend.frndId = id;
                console.log('Sending friend request...!!');
                FriendsService.sendRequest(me.friend).then(
                    function (data) {
                        me.friend = data;
                    },
                    function (error) {
                        alert(error);
                    }
                );

        }

        if ($routeParams.id != null || $routeParams != 0 || $routeParams != '0') {

        }
    }
]);