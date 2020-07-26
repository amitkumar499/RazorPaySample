'use strict'
angular.module('razorpayDemo.services', []).factory('RazorPayService', ["$http", "CONSTANTS", function($http, CONSTANTS) {
    var service = {};
    service.createPaymentOrder = function(request) {
        var url = "/service/createPaymentOrder";
        return $http.post(url,request);
    }
    service.capturePayment = function(request) {
         var url = "/service/capturePayment";
         return $http.post(url,request);
    }
    return service;
}]);