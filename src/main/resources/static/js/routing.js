(function() {
	"use strict";
	var module = angular.module('com.mpk');
	module.config(function($stateProvider, $urlRouterProvider){
		$urlRouterProvider.otherwise('/homepage');
		$stateProvider
			.state('test',
				{
					url: '/test',
					template: '<test></test>'
				})
			.state('test2',
				{
					url: '/test2',
					template: '<registration></registration>'
				})
			.state('homepage',
				{
					url: '/homepage',
					template: '<homepage></homepage>'
				})
			.state('busadd',
				{
					url: '/busadd',
					template:'<busadd></busadd>'
				})
			.state('buslineadd',
				{
					url: '/buslineadd',
					template:'<buslineadd></buslineadd>'
				})
			.state('busstopadd',
				{
					url: '/busstopadd',
					template:'<busstopadd></busstopadd>'
				})
			.state('workscheduleadd',
				{
					url: '/workscheduleadd',
					template:'<workscheduleadd></workscheduleadd>'
				})
			.state('users',
				{
					url: '/users',
					template:'<users></users>'
				})
			.state('buses',
				{
					url: '/buses',
					template:'<buses></buses>'
				})
			.state('busstoptable',
				{
					url: '/busstoptable',
					template:'<busstoptable></busstoptable>'
				})
	})
}());