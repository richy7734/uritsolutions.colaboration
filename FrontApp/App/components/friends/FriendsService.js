var FriendsModule = angular.module('FriendsModule', []);

FriendsModule.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function () {
                scope.$apply(function () {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

FriendsModule.service('FriendsService', ['$http', '$location', '$timeout', 'REST_URI', function ($http, $location, $timeout, REST_URI) {

    this.uploadFileToUrl = function (file, user) {
        var fd = new FormData();
        fd.append('file', file);

        $http({
            method: 'POST',
            url: REST_URI + '/image/upload/' + user.id, // The URL to Post.
            headers: { 'Content-Type': undefined }, // Set the Content-Type to undefined always.
            data: fd,
            transformRequest: function (data, headersGetterFunction) {
                return data;
            }
        }).then(
            function (response) {
                $location.path('/user');
            }, function (error) {
                alert(error);
            }
            );

    }

    this.getFrndRequests = function (user) {
        console.log('Posting request to : ' + REST_URI + '/list/friends')
        return $http.post(REST_URI + '/list/friends', user).then(
            function (response) {

                return response.data;
            }
            , function (error) {

                return error;
            }
        );
    }

    this.searchFriends = function (name) {
        return $http.post(REST_URI + '/search', name).then(
            function (response) {

                return response.data;
            }
            , null
        );
    }

    this.sendRequest = function (friends) {
        return $http.post(REST_URI + '/send/friend/request', friends).then(
            function (response) {

                return response.data;
            }
            , function (error) {
                return error;
            }
        );
    }

    this.acceptRequest = function (id, cuid) {
        return $http.post(REST_URI + '/friend/accept/' + cuid, id).then(
            function (response) {

                return response.data;
            }
            , function (error) {
                return error;
            }
        );
    }

    this.getUsers = function () {
        return $http.post(REST_URI + '/all/users').then(
            function (response) {

                return response.data;
            }
            , function (error) {
                return error;
            }
        );
    }

}]);
