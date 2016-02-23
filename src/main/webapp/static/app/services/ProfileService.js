/**
 * Created by Viktor on 19.01.2016.
 */

roomie.factory('ProfileService', function($http, $rootScope) {

    return {

        getAllFullUserProfiles: function(page_number) {
            return $http({
                url: '/roomie/public/api/User?page=' + page_number,
                method: "GET"
            });
        },


        getMyPersonalProfile: function(user_id) {
            return $http({
                url: '/roomie/public/api/PersonalProfile/' + user_id,
                method: "GET"
            });
        },

        saveMyPersonalProfile: function(profileData) {
            return $http({
                url: '/roomie/public/api/PersonalProfile/' + $rootScope.currentUser.id,
                method: "PUT",
                // Necessary to indicate that the sent data is JSON
                headers: { 'Content-Type' : 'application/x-www-form-urlencoded' },
                data: $.param(profileData)
            });
        },

        createMyPersonalProfile: function(profileData) {
            return $http({
                url: '/roomie/public/api/PersonalProfile',
                method: "POST",
                // Necessary to indicate that the sent data is JSON
                headers: { 'Content-Type' : 'application/x-www-form-urlencoded' },
                data: $.param(profileData)
            });
        },


        getMyRoommateProfile: function(user_id) {
            return $http({
                url: '/roomie/public/api/RoommateProfile/' + user_id,
                method: "GET"
            });
        },

        saveMyRoommateProfile: function(profileData) {
            //alert(profileData.first_name);
            return $http({
                url: '/roomie/public/api/RoommateProfile/' + $rootScope.currentUser.id,
                method: "PUT",
                // Necessary to indicate that the sent data is JSON
                headers: { 'Content-Type' : 'application/x-www-form-urlencoded' },
                data: $.param(profileData)
            });
        },

        createMyRoommateProfile: function(profileData) {
            return $http({
                url: '/roomie/public/api/RoommateProfile',
                method: "POST",
                // Necessary to indicate that the sent data is JSON
                headers: { 'Content-Type' : 'application/x-www-form-urlencoded' },
                data: $.param(profileData)
            });
        },

        saveUser: function(userData) {
            return $http({
                url: '/roomie/public/api/User/' + $rootScope.currentUser.id,
                method: "PUT",
                // Necessary to indicate that the sent data is JSON
                headers: { 'Content-Type' : 'application/x-www-form-urlencoded' },
                data: $.param(userData)
            });
        }

    }

});