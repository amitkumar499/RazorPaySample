'use strict'
var module = angular.module('razorpayDemo.controllers', []);
module.controller("RazorPayController", ["$scope", "RazorPayService",
            function($scope, RazorPayService) {
                $scope.amount = "";
                $scope.currency = "";
                $scope.orderId = "";
                $scope.options = {};
                $scope.message="";
                $scope.currencies = ["INR", "USD"];
                $scope.createOrderAndPay = function() {
                $scope.message="";
                    var request = {
                        "amount": $scope.amount + "00",
                        "currency": $scope.currency
                    };
                    RazorPayService.createPaymentOrder(request).then(successCallback, errorCallback);
                }

                function successCallback(response) {
                    $scope.orderId = response.data.orderId;
                    if($scope.orderId){
                    $scope.options = {
                        "key": "rzp_test_58XUlAvksxpuAb", // Enter the Key ID generated from the Dashboard
                        "amount": $scope.amount * 100, // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise
                        "currency": $scope.currency,
                        "name": "ChqBook Test Corp",
                        "description": "Test Transaction",
                        "image": "https://example.com/your_logo",
                        "order_id": $scope.orderId, //This is a sample Order ID. Pass the `id` obtained in the response of Step 1
                        "handler": function(response) {
                            var captureRequest = {
                                "orderId": $scope.orderId,
                                "transactionId": response.razorpay_payment_id,
                                "signature": response.razorpay_signature,
                            }
                            RazorPayService.capturePayment(captureRequest).then(function() {
                                        console.log("works");
                                            $scope.allUsers = value.data;
                                        }, function(reason) {
                                            console.log("error occured");
                                        }, function(value) {
                                            console.log("no callback");
                                        });
                                        console.log(response);

                                },
                                "prefill": {
                                    "name": "Test User",
                                    "email": "test.user@example.com",
                                    "contact": "9999999999"
                                },
                                "notes": {
                                    "address": "Razorpay Corporate Office"
                                },
                                "theme": {
                                    "color": "#F37254"
                                }
                        };
                        var rzp1 = new Razorpay($scope.options);
                        rzp1.open();
                        }else{
                        $scope.message="Error while creating order...please try again";
                        }
                    }

                    function errorCallback(error) {
                        //error code
                    }
                }
            ]);