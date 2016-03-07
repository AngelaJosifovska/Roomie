/**
 * Created by Viktor on 07.03.2016.
 */

roomie.controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, user_match) {
	
	$scope.user_match = user_match;

	$scope.cancel = function () {
		$uibModalInstance.dismiss('cancel');
	};
	
});