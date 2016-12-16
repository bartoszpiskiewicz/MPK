angular
		.module('com.mpk.registration')
		.service(
				'registrationService',
				function(Restangular,$rootScope) {
					return {
						register : function(user) {
								return Restangular.all('user').post(user);
						}
					};
				});