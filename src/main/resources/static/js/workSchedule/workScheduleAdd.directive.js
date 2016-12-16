angular.module('com.mpk.workscheduleadd', [])
    .directive('workscheduleadd', function() {
        return {
            restrict: 'E',
            templateUrl: 'views/workSchedule/workScheduleAdd.html'
        };
    });