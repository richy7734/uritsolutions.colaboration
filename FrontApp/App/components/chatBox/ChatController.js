
app.controller('ChatController', ['ChatService', '$scope', '$cookieStore', 'REST_URI',
	function (ChatService, $scope, $cookieStore, REST_URI) {
		var me = this;
		console.log("Chat Controller Invoked")
		me.message = "";
		me.messages = [];
		me.max = 140;
		me.userId = '';
		me.imgPath = '';

		me.today = new Date();
		console.log("Variables Initialized...");

		me.addMessage = function () {
			me.currentUser = $cookieStore.get('currentUser');
			console.log("Send Button listened...!! ");
			console.log('$$$ ' + me.message)
			me.userId = me.currentUser.id;
			ChatService.send(me.message, me.userId);
			console.log("After send() in chat Constroller ");
			me.message = "";

			console.log("outside message " + me.message);
		};

		ChatService.receive().then(null, null, function (message) {
			me.messages.push(message);
			me.imgPath = REST_URI+'/resources/images/';
			console.log("receive" + message.userId);
		});
	}
]);