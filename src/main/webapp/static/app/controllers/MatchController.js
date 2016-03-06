/**
 * Created by Viktor on 06.03.2016.
 */

roomie.controller('MatchController',
    ['$scope', '$http', '$rootScope', '$location', '$filter', '$stateParams', 'MatchService',

        function($scope, $http, $rootScope, $location, $filter, $stateParams, MatchService)
        {
    		$scope.fullProfiles = [];
    		$scope.match = {};
    		$scope.user = {};
    		$scope.p_profile = {};
    		$scope.r_profile = {
                cleanliness: 0,
                work_schedule: 0,
                sleep_schedule: 0,
                smoking: 0,
                drinking: 0,
                privacy: 0,
                guests: 0,
                eating_habits: 0,
                pets: 0
            };
            $scope.maxValue = 5;
            $scope.minValue = 0;
    	
    		$scope.pagination = {
                currentPage: 1,
                totalItems: 0
            };
    		
    		$scope.sexes = [
    		    {value: 'Male', text: 'Male'},
    		    {value: 'Female', text: 'Female'}
            ];
    		
    		$scope.statuses = [
                {value: 'Single', text: 'Single'},
                {value: 'Dating', text: 'Dating'},
                {value: 'In a Relationship', text: 'In a Relationship'},
                {value: 'Married', text: 'Married'}
    		];
    		
    		/**
             * Necessary to properly show the editable select drop-down
             */
            $scope.showGender = function(p_profile) {
                var selected = [];
                if(p_profile.gender) {
                    selected = $filter('filter')($scope.sexes, {value: p_profile.gender});
                }
                return selected.length ? selected[0].text : 'Not set';
            };
            
            /**
             * Necessary to properly show the editable select drop-down
             */
            $scope.showStatus = function(p_profile) {
                var selected = [];
                if(p_profile.relationship_status) {
                    selected = $filter('filter')($scope.statuses, {value: p_profile.relationship_status});
                }
                return selected.length ? selected[0].text : 'Not set';
            };
            
    	
    		$scope.getNextSuggestion = function() {
    			$scope.loading = true;
    			
	            if(!$rootScope.currentUser)
	                return;
	
	            MatchService.getNextMatch($rootScope.currentUser.id).success(function (data) {
	                $scope.loading = false;
//	                $scope.totalMatches = data;
	                $scope.match = data;
	                $scope.user = data.potential_user;
                    $scope.p_profile = data.potential_user.personal_profile;
                    $scope.r_profile = data.potential_user.roommate_profile;
	                console.log(data);
	            }).error(function (data) {
	                console.log(data);
	                $scope.loading = false;
	            })
    		};
    		
    		$scope.getListOfMatches = function() {
    			$scope.loading = true;
    			
	            if(!$rootScope.currentUser)
	                return;
	
	            MatchService.getAllMatches($rootScope.currentUser.id, $scope.pagination.currentPage).success(function (data) {
	                $scope.loading = false;
	                $scope.pagination.currentPage = data.current_page;
                    $scope.pagination.totalItems = data.total;
                    $scope.pagination.perPage = data.per_page;
                    $scope.fullProfiles = data.data;
	                console.log(data);
	            }).error(function (data) {
	                console.log(data);
	                $scope.loading = false;
	            })
    		};
    	
	    	$scope.getTotalMatches = function() {
	            $scope.loading = true;
	
	            if(!$rootScope.currentUser)
	                return;
	
	            MatchService.getTotalMatches($rootScope.currentUser.id).success(function (data) {
	                $scope.loading = false;
	                $scope.totalMatches = data;
	                console.log(data);
	            }).error(function (data) {
	                console.log(data);
	                $scope.loading = false;
	            })
	        };
	        
	        $scope.init = function() {
	        	$scope.getNextSuggestion();
	        	$scope.getListOfMatches();
            };
            
            $scope.positiveAnswer = function() {
            	console.log("YES");
            };
            
            $scope.negativeAnswer = function() {
            	console.log("NO");
            };

        }

    ]);