app.service('usertextbtnService',['$q','$http',function($q,$http){
	var factory = {
			userlogin : userlogin,
			fileUpload : fileUpload,
			getGstinList : getGstinList,
			getRefIdStatus : getRefIdStatus,
			generateDeleteFile : generateDeleteFile
	}
	return factory;
	
	function userlogin(rqstObj){
		var defer = $q.defer();
		var data = JSON.stringify(rqstObj);
		var request = {
		    method: 'POST',
		    url   : '/GSTTestingProject/sag/gstservice/login',
		    headers: {
		    	'Content-Type': 'text/plain',
		    	'Accept'      : 'application/json'
		    },
		    data : data
		};
		 $http(request).then(function (response) {
             defer.resolve(response);
         },function (error) {
        	 defer.reject(error);
         });
		 return defer.promise;
	}
	
	function fileUpload(rqstObj){
		var defer = $q.defer();
		var data = rqstObj;
		var request = {
                method: 'POST',
                url   : '/GSTTestingProject/sag/gstservice/upload',
                headers: {
                	'Content-Type': 'text/plain',
			    	'Accept'      : 'application/json'
                },
			    data : data
            };
			 $http(request).then(function (response) {
                 defer.resolve(response);
             },function (error) {
            	 defer.reject(error);
             });
			 return defer.promise;
	}
	
	function getGstinList() {
		var defer = $q.defer();
		var request = {
                method: 'GET',
                url   : '/GSTTestingProject/sag/gstservice/gstinList'
            };
			 $http(request).then(function (response) {
                 defer.resolve(response);
             },function (error) {
            	 defer.reject(error);
             });
			 return defer.promise;
	}
	
	function getRefIdStatus(rqstData) {
		var defer = $q.defer();
		var request = {
                method: 'POST',
                url   : '/GSTTestingProject/sag/gstservice/statusReport',
                headers: {
                	'Content-Type': 'text/plain',
			    	'Accept'      : 'application/json'
                },
			    data : JSON.stringify(rqstData)
            };
			 $http(request).then(function (response) {
                 defer.resolve(response);
             },function (error) {
            	 defer.reject(error);
             });
			 return defer.promise;
	}
	
	
	function generateDeleteFile(deletedData) {
		var defer = $q.defer();
		var request = {
                method: 'POST',
                url   : '/GSTTestingProject/sag/gstservice/deleteContent',
                headers: {
                	'Content-Type': 'text/plain',
			    	'Accept'      : 'application/json'
                },
			    data : deletedData
            };
			 $http(request).then(function (response) {
                 defer.resolve(response);
             },function (error) {
            	 defer.reject(error);
             });
			 return defer.promise;
	}
	
	
	
}]);
