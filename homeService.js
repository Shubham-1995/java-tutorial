app.factory('userFactory',userFactory);

function userFactory($q,$http){
	var factoryObj ={
			sessionOut : sessionOut
	}
	return factoryObj;
	function sessionOut(sessionParam){
		var defer = $q.defered();
		var sesionObject = {
				method:'GET',
	    		url:'BackButtonServlet?action='+sessionParam,
	    		headers:{
	    			 'Content-Type': 'application/json'
	    		}	
		};
		$http(sesionObject).then(function(response){
			defer.resolve(response);
		},function(err){
			defer.reject(err);
		});
		return defer.promise;
	}
}