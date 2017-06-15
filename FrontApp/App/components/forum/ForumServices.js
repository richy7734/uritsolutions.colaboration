var ForumModule = angular.module('ForumModule', []);

ForumModule.service('ForumServices', ['$http', '$q', 'REST_URI', function ($http, $q, REST_URI) {

    this.create = function (forum, user) {

        console.log('Forum Service reached.');
        console.log('Posting the data to the URL: ' + REST_URI + '/create/forum');
        console.log('Forum name : '+forum.name);
        return $http.post(REST_URI + '/create/forum/' + user.id,forum).then(
            function (response) {
                return (response.data);

            }, null
        );
    }

    this.getForums = function () {
        return $http.post(REST_URI + '/get/forums').then(
            function (response) {
                return response.data;
            }, null
        );
    }

    this.getForumsByUserId = function (userId) {
        return $http.post(REST_URI + '/get/peoples/by/currentUser/' + userId).then(
            function (response) {
                return response.data;
            }, null
        );
    }
    this.joinForum = function (frmId, user) {
        return $http.post(REST_URI + '/join/forum/' + frmId, user).then(
            function (response) {
                return response.data;
            }, null
        );
    }

    this.enterForum = function (id) {
        return $http.post(REST_URI + '/enter/forum/' + id).then(
            function (response) {
                return response.data;
            }, null
        );
    }

    this.getRequests = function (id) {
        return $http.post(REST_URI + '/get/people/for/approval/' + id).then(
            function (response) {
                return response.data;
            }, null
        );
    }

    this.approve = function (id) {
        console.log('The id is: '+id);
        return $http.post(REST_URI + '/approve/member/' + id).then(
            function (response) {
                return response.data;
            }, null
        );
    }

    this.getCurrentPeople = function (userId) {
        return $http.post(REST_URI + '/get/peoples/by/currentUser/' + userId).then(
            function (response) {
                return response.data;
            }, null
        );
    }

    this.showPost = function (frmId) {
        return $http.post(REST_URI + '/get/post/list/' + frmId).then(
            function (response) {
                return response.data;
            }, null
        );
    }

    this.addPost = function (frmId, username, post) {
        return $http.post(REST_URI + '/forum/post/' + frmId + '/' + username, post).then(
            function (response) {
                return response.data;
            }, null
        );
    }

    this.getComments = function (id) {
        return $http.post(REST_URI + '/get/comments/' + id).then(
            function (response) {
                return response.data;
            }, function (error) {
                return error;
            }
        );
    }

    this.commentSave = function (comment) {
        console.log('The comment is :' + comment.content);
        return $http.post(REST_URI + '/comment/save/' + comment.pid + '/' + comment.username, comment.content).then(
            function (response) {
                return response.data;
            }, function (error) {
                return error;
            }
        );
    }
}]); 