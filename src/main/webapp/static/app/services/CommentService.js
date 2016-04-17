/**
 * Created by Viktor on 26.01.2016.
 */

roomie.factory('CommentService', function($http) {

    return {

        // get all the rental units
        // page_number is useful for pagination
        getAllComments: function(property_id, page_number) {
            return $http({
                url: '/roomie/public/api/RentalUnit/' + property_id + '/Comment' + '?page=' + page_number,
                method: "GET"
            });
        },


        saveComment: function(property_id, comment_obj) {
            return $http({
                url: '/roomie/public/api/RentalUnit/' + property_id + '/Comment',
                method: "POST",
                //Necessary to indicate that the sent data is JSON
                headers: { 'Content-Type' : 'application/json' },
                data: comment_obj
            });
        },

        deleteComment: function(property_id, comment_id) {
            return $http({
                url: '/roomie/public/api/RentalUnit/' + property_id + '/Comment/' + comment_id,
                method: "DELETE"
            });
        }

    }

});