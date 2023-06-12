let pathImportedData = "http://localhost:8080/rest";
app.controller("ctrl-import", function($scope, $http) {
	$scope.form = {};
	$scope.items = [];
	/*--Mảng chứa list học kỳ--*/
	$scope.semesters = [];
	/*--Mảng chứa list chiến dịch--*/
	$scope.campaigns = [];
	
	$scope.fileName = '';
	$scope.student = {};
	
	/*--Lấy tất cả học kỳ vào select box--*/
	$http.get("/rest/semester").then(resp => {
		$scope.semesters = resp.data;
	});
	
	/*--Lấy tất cả chiến dịch vào select box--*/
	$http.get("/rest/campaign").then(resp => {
		$scope.campaigns = resp.data;
	});
	
	/*--Function xử lý nhập tệp--*/
	$scope.import = function(files) {
    var reader = new FileReader();
    reader.onloadend = async () => {
      var workbook = new ExcelJS.Workbook();
      await workbook.xlsx.load(reader.result);
      const worksheet = workbook.getWorksheet('Sheet1');
      worksheet.eachRow((row, index) => {
        if (index > 1) {
          student = {
            studentId: row.getCell(1).value,
            subjectId: row.getCell(2).value,
          }        
        }
      });

      // Lưu tên tệp vào mảng importedFileNames
      $scope.importedFileNames.push(files[0].name);

      // Cập nhật giá trị cho biến $scope.fileName
      $scope.$apply(function() {
        $scope.fileName = files[0].name;
        
      });
    };
    reader.readAsArrayBuffer(files[0]);
  };
  
  // Lấy tên tệp từ mảng importedFileNames khi trang tải
  $scope.importedFileNames = localStorage.getItem('importedFileNames') ? JSON.parse(localStorage.getItem('importedFileNames')) : [];
  if ($scope.importedFileNames.length > 0) {
    fileName = $scope.importedFileNames[$scope.importedFileNames.length - 1];
  }
  
  /*--Thêm mới importedData--*/
  $scope.create = function(){
	  var item = angular.copy($scope.form);
	  item.importFileName = $scope.fileName;

	  var url = `${pathImportedData}/imported`;
	  $http.post(url, item).then(resp => {

		  console.log(resp.data)
		  $scope.items.push(item);
		  $scope.load_all();
		  console.log("Success", resp);
		  
		  var url = "/rest/student";
	      $http.post(url, student).then(resp => {
	        console.log("Thành công", resp.data);
	      }).catch(error => {
	        console.log("Lỗi", error);
	      });
	  }).catch(error => {
		  console.log("Error", error);
	  });
	  
	  
  }
  
  /*--Lấy tất cả importedData--*/
  $scope.load_all = function(){
	  var url = `${pathImportedData}/imported`;
	  $http.get(url).then(resp => {
		  $scope.items = resp.data;
		  console.log("Success", resp)
	  }).catch(errors => {
		  console.log("Error", errors)
	  });
  };
  $scope.load_all();
});