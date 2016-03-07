/**
 * Created by Viktor on 06.03.2016.
 */

roomie.controller('MatchController',
    ['$scope', '$http', '$rootScope', '$location', '$filter', '$stateParams', '$uibModal', 'MatchService',

        function($scope, $http, $rootScope, $location, $filter, $stateParams, $uibModal, MatchService)
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
    		
            $scope.goals = [
                {value: 'Just a roommate', text: 'Just a roommate'},
                {value: 'Roommate with an apartment', text: 'Roommate with an apartment'},
                {value: 'Just a room', text: 'Just a room'},
                {value: 'Anything', text: 'Anything'}
            ];

            /**
             * Necessary to properly show the editable select drop-down
             */
            $scope.showGoal = function(r_profile) {
            	var selected = [];
                if(r_profile.looking_for) {
                	selected = $filter('filter')($scope.goals, {value: r_profile.looking_for});
                }
                return selected.length ? selected[0].text : 'Not set';
            };
    		
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
	                $scope.match = data;
	                if(data.potential_user != null) {
	                	$scope.user = data.potential_user;
	                    $scope.p_profile = data.potential_user.personal_profile;
	                    $scope.r_profile = data.potential_user.roommate_profile;
	                }
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
	                $rootScope.totalMatches = data;
	                console.log(data);
	            }).error(function (data) {
	                console.log(data);
	                $scope.loading = false;
	            })
	        };
	        
	        $scope.saveUserChoice = function(match) {
	            $scope.loading = true;
	
	            if(!$rootScope.currentUser)
	                return;
	            
        		match.from_user = $rootScope.currentUser,
        		match.to_user = $scope.user,
	
	            MatchService.saveChoice($rootScope.currentUser.id, match).success(function (data) {
	                $scope.loading = false;
	                $scope.getNextSuggestion();
	                $scope.getListOfMatches();
	                $scope.getTotalMatches();
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
            
            $scope.openContactDialog = function() {
            	var modalInstance = $uibModal.open({
            	      animation: true,
            	      templateUrl: 'contactModal',
            	      controller: 'ModalInstanceCtrl',
//            	      size: size,
            	      resolve: {
            	    	  user_match: function () {
            	    		  return $scope.user;
            	    	  }
            	      }
            	});
            };
            
            $scope.positiveAnswer = function() {
            	
            	var match = {
            		interested: true
            	}
            	
            	if($scope.match.interested_for_me)
            		$scope.openContactDialog();
            	$scope.saveUserChoice(match);
            };
            
            $scope.negativeAnswer = function() {
            	
            	var match = {
                	interested: false
                }
            	
            	$scope.saveUserChoice(match);
            };

        }

    ]);