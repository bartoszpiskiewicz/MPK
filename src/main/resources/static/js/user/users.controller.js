angular.module('com.mpk.users').controller(
		'UsersController',
		function($window,usersService, navigationService) {
			var self = this;
			self.users={};
			self.getUsers = function(){
				usersService.getUsers().then(function(response){
					self.users=response;
				})
			}
			self.getUsers();
			self.showRegistrationModal = function(){
				navigationService.showRegistrationModal();
			}
			self.closeRegistrationModal = function(){
				navigationService.closeRegistrationModal();
			}
			self.setUserToEdit = function(user){
				user.edit=true;
			}
			self.editUser = function(user){
				usersService.editUser(user).then(function(response){
					user.edit=false;
				})
			}
			self.deleteUser = function(user){
				usersService.deleteUser(user).then(function(response){
					$window.location.reload();
				})
			}
			self.setRole = function(user,role){
				usersService.setRole(user,role).then(function(respone){
					$window.location.reload();
				})
			}
		});