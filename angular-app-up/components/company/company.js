(function(){
    'use strict';
    angular.module('App')
    .controller('companyController', companyController);

    function companyController($scope, AuthService, RestServices){
        var self = this;
        $scope.tab = 1;
        var id;
        $scope.setTab = function(newTab){
            $scope.tab = newTab;
        }
        $scope.isSet = function(tabNum){
          return $scope.tab === tabNum;
        }
        if (AuthService.user.ownCompany){
            self.companyTabs = true;
            self.companyName = AuthService.user.ownCompany.companyName;
            self.companyAddressCity = AuthService.user.ownCompany.address.city;
            self.companyAddressBuilding =  AuthService.user.ownCompany.address.building;
            self.companyAddressStreet =  AuthService.user.ownCompany.address.street;
            self.companyAddressPost = AuthService.user.ownCompany.address.postcode;
            self.companyAddressCountry = AuthService.user.ownCompany.address.country;
            self.companyTID = AuthService.user.ownCompany.nip;
            self.companyBRN = AuthService.user.ownCompany.regon;
            self.companyID = AuthService.user.ownCompany.companyID;
        }
        else{
            self.noCompanyInfo = true;
        }
        RestServices.getAllCompanies().then(function(){
            for (var i=0; i<RestServices.data().length; i++){
                if (RestServices.data()[i].companyID == AuthService.user.ownCompany.companyID)
                    self.employees = RestServices.data()[i].employees; 
            }
        });
        self.userVal = function(){
            if(self.userInput.length>1){
                RestServices.findUser(self.userInput).then(function(){
                    self.userList = RestServices.data();
                });
            }else{
                RestServices.showAllUsers().then(function(){
                    self.userList = RestServices.data();
                });
            }
        }
        self.setEmployer = function(iden){
            id = iden;
            RestServices.findUserById(id).then(function(){
                console.log(RestServices.data());
                self.eName = RestServices.data().name;
                self.eSurname = RestServices.data().surname;
                self.eDateOfBirth = RestServices.data().dateOfBirth;
                self.eaCity = RestServices.data().address.city;
                self.eaBuild = RestServices.data().address.building;
                self.eaStreet = RestServices.data().address.street;
                self.eaPost = RestServices.data().address.postcode;
                if(RestServices.data().laborFundContribution){
                
                    if(RestServices.data().laborFundContribution.active)
                    self.lf = "Active";
                    else
                    self.lf = "Not active";
                    self.lfAmount = RestServices.data().laborFundContribution.amount;
                    self.lfFromDate = RestServices.data().laborFundContribution.fromDate;
                    self.lfToDate = RestServices.data().laborFundContribution.toDate;
                }
                if(RestServices.data().healthContribution){
                    if(RestServices.data().healthContribution.insured)
                    self.hc = "Active";
                    else
                    self.hc = "Not active";
                    self.hcAmount = RestServices.data().healthContribution.amount;
                    self.hcFromDate = RestServices.data().healthContribution.fromDate;
                    self.hcToDate = RestServices.data().healthContribution.toDate;
                }
                if(RestServices.data().socialContribution){
                    self.sc = "Active";
                    self.scAmount = RestServices.data().socialContribution.amount;
                    self.scFromDate = RestServices.data().socialContribution.fromDate;

                    
                    if(RestServices.data().socialContribution.pension.granted)
                        self.scp = "Granted";
                    else
                        self.scp = "Not granted";
                    self.scpAmount = RestServices.data().socialContribution.pension.amount;
                    self.scpFromDate = RestServices.data().socialContribution.pension.fromDate;

                    if (RestServices.data().socialContribution.rent.paid)
                        self.scr = "Paid";
                    else
                        self.scr = "Not paid";
                    self.scrAmount = RestServices.data().socialContribution.rent.amount;
                    self.scrFromDate = RestServices.data().socialContribution.rent.fromDate;
                    self.scrToDate = RestServices.data().socialContribution.rent.toDate;
                }
            });
        }
        self.showAppSummary = function(){
            self.aName = AuthService.user.name;
            self.aSurname = AuthService.user.surname;
            self.aId = AuthService.user.id;
            self.aStreet = AuthService.user.address.street;
            self.aBuilding = AuthService.user.address.building;
            self.aPostal = AuthService.user.address.postcode;
            self.aCity = AuthService.user.address.city;
            self.aType = self.selectedType;
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