/**
 * Created by Viktor on 17.01.2016.
 */

roomie.controller('RentalUnitController',
    ['$scope', '$http', '$state', '$rootScope', '$filter', '$stateParams', '$location', 'RentalUnitService',

        function($scope, $http, $state, $rootScope, $filter, $stateParams, $location, RentalUnitService)
        {
    		$scope.map = { 
    			center: { 
    				latitude: 40.7127837, 
    				longitude: -74.0059413
    			}, 
    			zoom: 11 
    		};
    	
            $scope.rentalUnits = [];
            $scope.property = {};
            //$scope.rentalUnitId = 1;

            $scope.pagination = {
                currentPage: 1,
                totalItems: 0
            };

            $scope.types = [
                {value: 'Shared room', text: 'Shared room'},
                {value: 'Private room', text: 'Private room'},
                {value: 'Apartment', text: 'Apartment'},
                {value: 'House', text: 'House'}
            ];

            $scope.classes = [
                {value: 'Small', text: 'Small'},
                {value: 'Standard', text: 'Standard'},
                {value: 'Large', text: 'Large'},
                {value: 'Luxury', text: 'Luxury'}
            ];

            $scope.furnished = [
                {value: 'Unfurnished', text: 'Unfurnished'},
                {value: 'Only shared rooms', text: 'Only shared rooms'},
                {value: 'Fully furnished', text: 'Fully furnished'}
            ];

            $scope.datePicker = {
                isOpen: false,
                minDate: new Date()
            };

            $scope.openPopUp = function() {
                $scope.datePicker.isOpen = true;
            };

            $scope.toggleActivation = function() {
                $scope.property.property_active = ($scope.property.property_active + 1) % 2;
                $scope.saveRentalUnit();
            };


            /**
             * Redirecting to show only the chosen property from the list
             * @param id
             */
            $scope.showProperty = function(id) {
                $location.url('/propertyPage/' + id);
                //console.log(id);
            };

            /**
             * Necessary to properly show the editable select drop-down
             */
            $scope.showType = function(property) {
                var selected = [];
                if(property.type) {
                    selected = $filter('filter')($scope.types, {value: property.type});
                }
                return selected.length ? selected[0].text : 'Not set';
            };

            /**
             * Necessary to properly show the editable select drop-down
             */
            $scope.showClass = function(property) {
                var selected = [];
                if(property.class) {
                    selected = $filter('filter')($scope.classes, {value: property.class});
                }
                return selected.length ? selected[0].text : 'Not set';
            };

            /**
             * Necessary to properly show the editable select drop-down
             */
            $scope.showFurniture = function(property) {
                var selected = [];
                if(property.furniture) {
                    selected = $filter('filter')($scope.furnished, {value: property.furniture});
                }
                return selected.length ? selected[0].text : 'Not set';
            };


            $scope.getAllRentalUnits = function() {
                $scope.loading = true;

                RentalUnitService.getAll($scope.pagination.currentPage).success(function (data) {
                    $scope.loading = false;
                    $scope.pagination.currentPage = data.current_page;
                    $scope.pagination.totalItems = data.total;
                    $scope.pagination.perPage = data.per_page;
                    $scope.rentalUnits = data.data;
                    //console.log(data);
                }).error(function (data) {
                    console.log(data);
                    $scope.loading = false;
                })
            };

            $scope.getSingleRentalUnit = function() {
                $scope.loading = true;

                console.log($stateParams.id);

                RentalUnitService.getSingle($stateParams.id).success(function(data) {
                    $scope.loading = false;
                    console.log($stateParams.id);
                    $scope.property = data;
                    //console.log($scope.property);
                    //console.log($scope.property.location.id);
                    $scope.property.move_in_from = new Date($scope.property.move_in_from);
                    $scope.photos = data.property_picture;
                    
                    //settings for the location
                    $scope.map.center.latitude = $scope.property.location.latitude;
                    $scope.map.center.longitude = $scope.property.location.longitude;
                    $scope.map.zoom = 15;
                    
                    //console.log(data);
                }).error(function(data) {
                    console.log(data);
                    $scope.loading = false;
                });
            };

            $scope.saveRentalUnit = function() {

                if(!$rootScope.currentUser)
                    return;

                $scope.loading = true;
                
                $scope.property.move_in_from = $scope.property.move_in_from.toISOString().slice(0, 10);
                function replacer(key,value)
                {
                    if (key=="user") return undefined;
                    else if (key=="location") return undefined;
                    else if (key=="property_picture") return undefined;
                    else if (key=="updated_at") return undefined;
                    else if (key=="private_bathroom"){
                    	if(value==1)return true;
                    	else return false;
                    }else if (key=="created_at"){
                    	return value.substring(0,9);
                    }
                    else return value;
                }
                var result = JSON.stringify($scope.property, replacer);
                var propertyNew = JSON.parse(result);
                RentalUnitService.saveProperty(propertyNew).success(function (data) {
                    $scope.loading = false;
                    $scope.getSingleRentalUnit();
                    //$scope.property = data;
                    //console.log(data);
                }).error(function (data) {
                    console.log(data);
                    $scope.loading = false;
                })
            };

            $scope.createProperty = function() {
                if(!$rootScope.currentUser)
                    return;

                $scope.loading = true;
                $scope.property.user_id = $rootScope.currentUser.id;
                $scope.property.move_in_from = $scope.property.move_in_from.toISOString().slice(0, 10);

                //console.log($scope.property);
                RentalUnitService.createNewProperty($scope.property).success(function (data) {
                    $scope.loading = false;
                    $state.go('propertyPage', { id: data.id });
                    //$scope.getSingleRentalUnit();
                    //$scope.property = data;
                    //console.log(data);
                }).error(function (data) {
                    console.log(data);
                    $scope.loading = false;
                })
            };


            /**
             * Initialization of the Rental Unit controller
             */
            $scope.init = function() {
                if($stateParams.id !== undefined) {
                    //$scope.rentalUnitId = $stateParams.id;
                    $scope.getSingleRentalUnit();

                } else {
                    $scope.getAllRentalUnits();
                }
            };

            $scope.init();
        }

    ]);
//change because of github problem