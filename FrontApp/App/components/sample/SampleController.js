app.controller('SampleController', ['$scope', 'SampleService', function ($scope, SampleService) {

    var me = this;
    me.dat = 'Welcome';
    me.name='';

        me.select = function () {
        SampleService.senddata(me.dat).then(
            function (data) {
                me.name = data;
                console.log(data);
            },
            function (error) {
                console.log(error);
            }
        );
    }
}]);