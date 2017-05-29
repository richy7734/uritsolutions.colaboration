
var ChatModule = angular.module('ChatModule',[]);

ChatModule.service('ChatService',['$q','$timeout','REST_URI', function($q, $timeout, REST_URI){
	console.log("Inside ChatService");

	var service = {}, listener = $q.defer(), socket = {
		client : null,
		stomp : null
	}, messageIds = [];
	console.log("Variables initialized...!!");

	service.RECONNECT_TIMEOUT = 30000;
	service.SOCKET_URL = REST_URI+"/chat";
	service.CHAT_TOPIC =  REST_URI+"/topic/message";
	service.CHAT_BROKER = REST_URI+"/topic/chat";

	console.log("Socket Service URLs initialized...!!");


	service.receive = function() {
		console.log("Recieve")
		return listener.promise;
	};

	service.send = function(message) {
		console.log('Inside ChatService.send')
		console.log('Message :' + message)
		var id = Math.floor(Math.random() * 1000000);
		console.log('@@@' + id);
		socket.stomp.send(service.CHAT_BROKER, {
			priority : 9
		}, JSON.stringify({
			message : message,
			id : id
		}));
		messageIds.push(id);
		console.log('Inside chat service ' + messageIds + " " + message)
	};

	console.log("Establishing Connection...!!!");

	var reconnect = function() {
		$timeout(function() {
			initialize();
		}, this.RECONNECT_TIMEOUT);
	};

	var getMessage = function(data) {
		console.log("getmessage")
		var message = JSON.parse(data), out = {};
		out.message = message.message;
		out.time = new Date(message.time);
		return out;
	};

	var startListener = function() {
		console.log("Listener initiated....")
		socket.stomp.subscribe(service.CHAT_TOPIC, function(data) {
			listener.notify(getMessage(data.body));
		});
	};

	var initialize = function() {
		console.log("Socket initializing with the service URL : "+service.SOCKET_URL);
		socket.client = new SockJS(service.SOCKET_URL);
		console.log("Socket Client initialized");
		socket.stomp = Stomp.over(socket.client);
		console.log("Stomp socket set over client socket..");
		console.log("Starting connection and listener...");
		socket.stomp.connect({}, startListener);
		console.log("Connection and listener established...");
		socket.stomp.onclose = reconnect;
	};

	initialize();
	return service;
}]);