'use strict'
var demoApp = angular.module('razorpayDemo', ['ui.bootstrap', 'razorpayDemo.controllers',
    'razorpayDemo.services'
]);
demoApp.constant("CONSTANTS", {
    getUserByIdUrl: "/user/getUser/",
    getAllUsers: "/user/getAllUsers",
    saveUser: "/user/saveUser"
});