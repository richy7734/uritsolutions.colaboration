// Specify the backend url from where you are going to get the values
app.constant('REST_URI', 'http://localhost:8084/uritsolution');


app.config(function ($routeProvider) {
    $routeProvider

        // route for the home page
        .when('/home'||'/', {
            templateUrl: 'App/components/basic/home.html',
            controller: 'BasicController'
        })

        // route for the about page
        .when('/about', {
            templateUrl: 'pages/about.html',
            controller: 'BasicController'
        })

        // route for the register page
        .when('/register', {
            templateUrl: 'App/components/register/register.html',
            controller: 'RegisterController',
            controllerAs: 'regCtrl'
        })
        .when('/login', {
            templateUrl: 'App/components/login/login.html'
        })
        .when('/post', {
            templateUrl: 'App/components/post/getPost.html',
            controller: 'PostController',
            controllerAs: 'postCtrl'
        }) 
         .when('/friends/:id', {
            templateUrl: 'App/components/friends/friends.html',
            controller: 'FriendsController',
            controllerAs: 'frndCtrl'
        })
        .when('/listUser/:id', {
            templateUrl: 'App/components/friends/listUser.html',
            controller: 'FriendsController',
            controllerAs: 'frndCtrl'
        })
        .when('/logout', {
            controller: 'LogoutController',
        });

    $routeProvider.otherwise({ redirectTo: '/login' });
});