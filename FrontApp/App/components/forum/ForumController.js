
app.controller('ForumController', ['ForumServices', '$location', '$rootScope', '$cookieStore', '$scope',
    function (ForumServices, $location, $rootScope, $cookieStore, $scope) {

        var me = this;
        $scope.forumForm = null;
        $scope.forumPage = '';
        $scope.myForumsPage = null;
        $scope.myForumPage = null;
        $scope.listRequests = null;
        $scope.getPostPage = null;
        $scope.postPage = null;

        me.forum = {};
        $scope.forums = {};
        $scope.people = null;
        $scope.peoples = {};
        me.post = {};
        $scope.myForum = {};
        $scope.currentForum = {};
        $scope.p = {};
        $scope.comment = {};
        $scope.comments = {};

        console.log('Hello to Forum controller ');

        $scope.currentUser = $cookieStore.get('currentUser');


        $scope.addForum = function () {
            $scope.forumForm = 'ok';
            $scope.forumPage = null;
            $scope.myForumsPage = null;
            $scope.myForumPage = null;
            $scope.listRequests = null;

            ForumServices.getForums().then(
                function (data) {
                    $scope.forum = data;
                }, function (error) {
                    console.log(error);
                }
            );
        }

        $scope.showForums = function () {
            $scope.forumForm = null;
            $scope.forumPage = 'ok';
            $scope.myForumsPage = null;
            $scope.myForumPage = null;
        }

        $scope.myForums = function () {
            $scope.forumForm = null;
            $scope.forumPage = null;
            $scope.myForumsPage = 'ok';
            $scope.myForumPage = null;

            ForumServices.getForumsByUserId($scope.currentUser.id).then(
                function (data) {
                    $scope.peoples = data;
                }, function (error) {
                    console.log(error);
                }
            );
        }

        $scope.enterForum = function (id) {
            $scope.forumForm = null;
            $scope.forumPage = null;
            $scope.myForumsPage = null;
            $scope.myForumPage = 'ok';
            $scope.getPostPage = null;
            $scope.postPage = 'ok';

            ForumServices.enterForum(id).then(
                function (data) {
                    $scope.people = data;
                }, function (error) {
                    alert('Sorry either you are not part of the forum or admin has not accepted your request...!!!');
                }
            );

            ForumServices.showPost($scope.people.grpId).then(
                function (data) {
                    $scope.post = data;
                }, function (error) {
                    console.log(error);
                }
            );
        }

        $scope.getRequests = function (id) {
            $scope.forumForm = null;
            $scope.forumPage = null;
            $scope.myForumsPage = null;
            $scope.myForumPage = null;
            $scope.listRequests = 'ok';

            ForumServices.getRequests(id).then(
                function (data) {
                    console.log('Fetched peoples for approval...!!');
                    $scope.peoples = data;
                }, function (error) {
                    alert('No requests yet recieved...!!!');
                }
            );
        }

        $scope.showPost = function (frmId) {
            $scope.getPostPage = null;
            $scope.postPage = 'ok';
            ForumServices.showPost(frmId).then(
                function (data) {
                    $scope.post = data;
                }, function (error) {
                    console.log(error);
                }
            );
        }

        $scope.addPost = function () {
            $scope.getPostPage = 'ok';
            $scope.postPage = null;

        }

        me.getPost = function () {

            ForumServices.addPost($scope.people.grpId, $scope.currentUser.username, me.post).then(
                function (data) {
                    $scope.post = data;
                }, function (error) {
                    console.log(error);
                }
            );
        }
        $scope.getComment = function (id) {
            $scope.currentUser = $cookieStore.get('currentUser');
            $scope.p.id = id;
            ForumServices.getComments(id).then(
                function (data) {
                    $scope.comments = data;
                }, function (error) {
                    console.log(error);
                }
            );
        }

        $scope.comment = function () {

            $scope.comment.pid = $scope.p.id;
            $scope.comment.username = $scope.currentUser.username;
            console.log($scope.comment.pid + ' ' + $scope.comment.username + ' ' + $scope.comment.cntent);
            ForumServices.commentSave($scope.comment).then(
                function (data) {
                    $scope.comments = data;
                }, function (error) {
                    console.log(error);
                }
            );
        }


        ForumServices.getForums().then(
            function (data) {
                $scope.forums = data;
            }, function (error) {
                console.log(error);
            }
        );
        ForumServices.getCurrentPeople($scope.currentUser.id).then(
            function (data) {
                $scope.peoples = data;
            }, function () {
                alert('Sorry either you are not part of the forum or admin has not accepted your request...!!!');
            }
        );

        $scope.joinForum = function (frmId) {
            ForumServices.joinForum(frmId, $scope.currentUser).then(
                function (data) {
                    alert('Join request has been sent. you will get a mail onece approved by the group admin.');
                }, function (error) {
                    console.log(error);
                }
            );
        }

        $scope.createForum = function () {
            console.log('Create Forum button clicked by :' + $scope.currentUser.name);

            ForumServices.create(me.forum, $scope.currentUser).then(
                function (data) {

                    alert('Thank you ' + data.name + '. Please wait until admin approves your account.');

                },
                function (error) {
                    console.log(error);
                    alert('Unable to create forum.');
                }
            );
        }

        $scope.approve = function (id) {
            ForumServices.approve(id).then(
                function (data) {
                    $scope.peoples = data;
                }, function (error) {
                    console.log(error);
                }
            );
        }


    }]);