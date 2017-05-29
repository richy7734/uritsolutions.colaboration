
app.controller('ChatController',['ChatService',
		function(ChatService) {
			var me = this;
			console.log("Chat Controller Invoked")
			me.message = "";
			me.messages = [{'message':'My First Message','time':'22:52'}];
			me.max = 140;
			me.today = new Date();
			console.log("Variables Initialized...");

			me.addMessage = function() {
				console.log("Send Button listened...!! ");
				console.log('$$$ ' + me.message)
				ChatService.send(me.message);
				console.log("After send() in chat Constroller ");
				me.message = "";
				console.log("outside message " + me.message);
			};

			ChatService.receive().then(null, null, function(message) {
				me.messages.push(message);
				console.log("receive" + message);
			});
		}
]);