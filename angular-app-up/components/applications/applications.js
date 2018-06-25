(function(){
    'use strict';
    angular.module('App')
    .controller('applicationsController', applicationsController);

    function applicationsController(RestServices, AuthService){
        var self = this;
        function clear(){
            self.applicationWrongCompany = false;
            self.applicationAlreadyCompany = false;
            self.applicationWrongRent = false;
            self.appSuccess = false;
        }
        self.showAppSummary = function(){
            clear();
            self.aName = AuthService.user.name;
            self.aSurname = AuthService.user.surname;
            self.aId = AuthService.user.id;
            self.aStreet = AuthService.user.address.street;
            self.aBuilding = AuthService.user.address.building;
            self.aPostal = AuthService.user.address.postcode;
            self.aCity = AuthService.user.address.city;
            self.aType = self.selectedType;
            if (self.selectedType=="NEW_COMPANY"){
                if (!AuthService.user.ownCompany){
                    $('#showAppSummary').modal('show');
                }
                else{
                    self.applicationAlreadyCompany = true;
                }
            }
            else if (self.selectedType=="UNREGISTER_COMPANY"){
                if (AuthService.user.ownCompany){
                    $('#showAppSummary').modal('show');
                }
                else{
                    self.applicationWrongCompany = true;
                }
            }
            else{
                if (!AuthService.user.socialContribution.rent){
                    $('#showAppSummary').modal('show');
                }
                else{
                    self.applicationWrongRent = true;
                }
            }
        }
        paypal.Button.render({
            env: 'sandbox', // sandbox | production
            style: {
                label: 'paypal',
                size:  'medium',    // small | medium | large | responsive
                shape: 'rect',     // pill | rect
                color: 'blue',     // gold | blue | silver | black
                tagline: false    
            },
            client: {
                sandbox:    'AZDxjDScFpQtjWTOUtWKbyN_bDt4OgqaF4eYXlewfBP4-8aqX3PiV8e1GWU6liB2CUXlkA59kJXE7M6R',
                production: '<insert production client id>'
            },
            payment: function(data, actions) {
                return actions.payment.create({
                    payment: {
                        transactions: [
                            {
                                amount: { total: '0.01', currency: 'USD' }
                            }
                        ]
                    }
                });
            },
            onAuthorize: function(data, actions) {
                return actions.payment.execute().then(function() {
                    var objectApp = {
                        name: AuthService.user.name,
                        surname: AuthService.user.surname,
                        city: AuthService.user.address.city,
                        building: AuthService.user.address.building,
                        street: AuthService.user.address.street,
                        postcode: AuthService.user.address.postcode,
                        country: AuthService.user.address.country,
                        userID: AuthService.user.id,
                        type: self.selectedType
                    }
                    console.log(objectApp);
                    RestServices.sendApplication(objectApp).then(function(){
                        self.applicationSendInfo = true;
                        $('#showAppSummary').modal('hide');
                    
                    });
                });
            }
        }, '#paypal-button-container');
    }

})();