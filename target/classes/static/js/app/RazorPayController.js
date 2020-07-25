'use strict'
var module = angular.module('razorpayDemo.controllers', []);
module.controller("RazorPayController", ["$scope", "RazorPayService",
    function($scope, RazorPayService) {
        $scope.userDto = {
            userId: null,
            userName: null,
            skillDtos: []
        };
        $scope.skills = [];
        RazorPayService.getUserById(1).then(function(value) {
            console.log(value.data);
        }, function(reason) {
            console.log("error occured");
        }, function(value) {
            console.log("no callback");
        });
        $scope.saveUser = function() {
            $scope.userDto.skillDtos = $scope.skills.map(skill => {
                return {
                    skillId: null,
                    skillName: skill
                };
            });
            RazorPayService.saveUser($scope.userDto).then(function() {
                console.log("works");
                RazorPayService.getAllUsers().then(function(value) {
                    $scope.allUsers = value.data;
                }, function(reason) {
                    console.log("error occured");
                }, function(value) {
                    console.log("no callback");
                });
                $scope.skills = [];
                $scope.userDto = {
                    userId: null,
                    userName: null,
                    skillDtos: []
                };
            }, function(reason) {
                console.log("error occured");
            }, function(value) {
                console.log("no callback");
            });
        }
    }
]);