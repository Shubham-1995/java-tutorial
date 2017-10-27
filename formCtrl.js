var app = angular.module('testingProject',[]);
app.controller('formCtrl',testCtrl);
		
		
function testCtrl($scope,$window,formService){
	var jsonObject = {};
$scope.logiFun = function(){
	
	jsonObject["username"] = $scope.username;
	jsonObject["password"] = $scope.password;
	console.log(angular.toJson(jsonObject));
	formService.getLoginUser(jsonObject).then(function(response){
		console.log("response ------ "+angular.toJson(response));
		if(response['data'] == 'success'){
			$window.location.href = "/Testbackbutton/home.html"
		}else{
			alert(response['data']);
		}
	},function(error){
		console.log("error  === "+error);
	});
 }

}