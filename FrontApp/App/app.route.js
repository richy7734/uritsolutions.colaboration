// Specify the backend url from where you are going to get the values
app.constant('REST_URI', 'http://localhost:8084/uritsolution');


app.config(function ($routeProvider) {
    $routeProvider

        // route for the home page
        .when('/home'||'/', {
            templateUrl: 'App/components/basic/home.html',
            controller: 'BasicController',
            controllerAs:'basicCtrl'
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
        .when('/user', {
            templateUrl: 'App/components/friends/user.html',
            controller: 'FriendsController',
            controllerAs: 'frndCtrl'
        })
        .when('/listUser', {
            templateUrl: 'App/components/friends/listUser.html'
        })
        .when('/logout', {
            controller: 'LogoutController',
        });

    $routeProvider.otherwise({ redirectTo: '/login' });
});
app.run(function($rootScope, $location, $cookieStore, $http) {

	$rootScope.$on('$locationChangeStart', function(event, next, current) {
		console.log("$locationChangeStart")
		var restrictedPage = $.inArray($location.path(), [  '/home.html',
				 '/post', '/login', '/listUser',
				'/user' ]) === -1;

		console.log("restrictedPage:" + restrictedPage)
		var loggedIn = $rootScope.currentUser.id;
		$rootScope.username = loggedIn;
		console.log("loggedIn:" + loggedIn + " " + $rootScope.username)

		if (!loggedIn) {

			if (restrictedPage) {
				console.log("Navigating to login page:")

				$location.path('/login');
			}
		}

		else {

			var role = $rootScope.currentUser.role;
			var userRestrictedPage = $.inArray($location.path(),
					[ "/about" ]) == 0;

			if (userRestrictedPage && role != 'admin') {

				alert("You can not do this operation as you are logged as : "
						+ role)

			}

		}

	});
	$rootScope.currentUser = $cookieStore.get('currentUser') || {};

	if ($rootScope.currentUser) {
		$http.defaults.headers.common['Authorization'] = 'Basic '
				+ $rootScope.currentUser;
	}

});
