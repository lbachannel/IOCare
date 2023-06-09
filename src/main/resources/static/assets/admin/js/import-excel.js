app.controller("ctrl-import", function($scope, $http) {
	$scope.form = {};
	/*--Mảng chứa list học kỳ--*/
	$scope.semesters = [];
	/*--Mảng chứa list chiến dịch--*/
	$scope.campaigns = [];
	
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
          let student = {
            studentId: row.getCell(1).value,
            subjectId: row.getCell(2).value,
            gender: true && row.getCell(3).value,
            mark: +row.getCell(4).value,
            country: row.getCell(5).value
          }
          var url = "/rest/student";
          $http.post(url, student).then(resp => {
            console.log("Thành công", resp.data);
          }).catch(error => {
            console.log("Lỗi", error);
          });
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
});