angular.module('com.mpk.buses').controller(
		'BusesController',
		function($window,busesService) {
			var self = this;
			self.bus={};
			self.sameRegistrationNumberInUse=false;
			self.editBus = function(bus){
				/*busesService.editBus(bus).then(function(response){
					bus.edit=false;
				}) */
				self.sameRegistrationNumberInUse=false;
				busesService.editBus(bus).then(function(response){
						bus.edit=false;
					},
					function(response){
						if(response.status == 409){
							self.sameRegistrationNumberInUse=true;
						}
					});

			}
			self.add = function(){
				self.sameRegistrationNumberInUse=false;
				busesService.addBus(self.bus).then(function(response){
						busesService.closeBusAddModal();
						$window.location.reload();
					},
					function(response){
						if(response.status == 409){
							self.sameRegistrationNumberInUse=true;
						}
					});
			}
			self.buses={};
			self.getBuses = function(){
				busesService.getBuses().then(function(response){
					self.buses=response;
				})
			}
			self.getBuses();
			self.setBusToEdit = function(bus){
				bus.edit=true;
			}
			self.deleteBus = function(bus){
				busesService.deleteBus(bus).then(function(response){
					$window.location.reload();
				})
			}
			self.setStatus = function(bus,status){
				busesService.setStatus(bus,status).then(function(respone){
					$window.location.reload();
				})
			}
			self.openBusAddModal = function() {
				busesService.showBusAddModal();
			}
			self.closeBusAddModal = function(){
				console.log("chuj");
				busesService.closeBusAddModal();
			}
		});