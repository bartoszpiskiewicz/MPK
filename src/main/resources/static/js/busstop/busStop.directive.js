angular.module('com.mpk.busStop', [])
.directive('busstop', function() {
  return {
    restrict: 'E',
    templateUrl: 'views/busStop/busStopFormAdd.html',
  };
});