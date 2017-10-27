app.service('formService',testService);

function testService($q,$http){
	var self =this;
	
	self.getLoginUser = function(uiObject){
		var defer = $q.defer();
	    var requestObject = {
	    		method:'POST',
	    		url:'BackButtonServlet',
	    		data :  angular.toJson(uiObject),
	    		headers:{
	    			 'Content-Type': 'application/json'
	    		}
	    };
	    $http(requestObject).then(function (response) {
            defer.resolve(response);
        },function (error) {
       	 defer.reject(error);
        });
		return defer.promise;
	}
}