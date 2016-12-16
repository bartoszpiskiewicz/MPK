angular.module('com.mpk.busLine', [])
.directive('busline', function() {
  return {
    restrict: 'E',
    templateUrl: 'views/busLine/busLineFormAdd.html',
  };
});