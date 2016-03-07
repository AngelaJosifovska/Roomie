/**
 * Created by Viktor on 17.01.2016.
 */

roomie.factory('MatchService', function($http) {

    return {
    	
    	getTotalMatches: function(user_id) {
            return $http({
                url: '/roomie/public/api/Matcher/' + user_id + '/total',
                method: "GET"
            });
        },
        
        getNextMatch: function(user_id) {
        	return $http({
                url: '/roomie/public/api/Matcher/' + user_id,
                method: "GET"
            });
        },
        
        getAllMatches: function(user_id, page_number) {
            return $http({
                url: '/roomie/public/api/Matcher/' + user_id + '/all?page=' + page_number,
                method: "GET"
            });
        },
        
        saveChoice: function(user_id, matchData) {
        	return $http({
                url: '/roomie/public/api/Matcher/' + user_id,
                method: "POST",
                // Necessary to indicate that the sent data is JSON
                headers: { 'Content-Type' : 'application/json' },
                data: matchData
            });
        },

    }

});