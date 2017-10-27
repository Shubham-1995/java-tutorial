var app = angular.module('userApp',[]);
app.controller('userCtrl',userCtrl);
function userCtrl($scope,userFactory){
	$scope.logOut =function(){
		userFactory.sessionOut("sessionTimeOut").then(function(response){
			console.log(response);
		},function(err){
			console.log(err);
		});
	}
}