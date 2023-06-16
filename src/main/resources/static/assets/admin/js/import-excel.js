let pathImportedData = "http://localhost:8080/rest";
app.controller("ctrl-import", function($scope, $http) {
	$scope.form = {};
	$scope.items = [];
	/*--Mảng chứa list nhân sự--*/
	$scope.employees = [];
	/*--Mảng chứa list tên file thư mục--*/
	$scope.fileExcelNames = [];
	/*--Mảng chứa list học kỳ--*/
	$scope.semesters = [];
	/*--Mảng chứa list chiến dịch--*/
	$scope.campaigns = [];
	
	$scope.fileName = '';
	$scope.students = [];
	
	/*--Mảng chứa danh sách sinh viên từ file excel--*/
	$scope.tableData = [];
	
	/*--Cập nhật tên nhân sự phụ trách cho thuộc tính của sinh viên--*/
	$scope.update = function(){
		var student = angular.copy($scope.form);
		var url = `/rest/student/` + $scope.form.selectedEmployee;
		$http.put(url, student).then(resp => {
			var index = $scope.items.findIndex(item => item.studentId == student.studentId);
			$scope.items[index] = resp.data;
			alert("Cập nhật student thành công")
		}).catch(error => {
			console.log("Update failed.", error);
			alert("Cập nhật student thất bại")
		});
	};

	/*--Hiển thị danh sách sinh viên dựa vào tên file excel--*/
	$scope.showStudents = function() {
		$http.get(`/rest/student/` + $scope.selectedFileName).then(resp =>{
			$scope.tableData = resp.data;
		});
	};
	
	
	/*--Lấy tất cả nhân sự--*/
	$scope.load_employees = function(){
		$http.get("/rest/employees").then(resp =>{
			$scope.employees = resp.data;
		});
	};
	
	/*--Lấy tất cả học kỳ vào select box--*/
	$scope.load_semester = function(){
		$http.get("/rest/semester").then(resp => {
			$scope.semesters = resp.data;
		});
	};
	/*--Lấy tất cả chiến dịch vào select box--*/
	$scope.load_campaign = function(){
		$http.get("/rest/campaign").then(resp => {
			$scope.campaigns = resp.data;
		});
	};
	/*--Function xử lý nhập tệp--*/
	$scope.import = function(files) {
		var reader = new FileReader();
		reader.onloadend = async () => {
			if ($scope.form.campaign.campaignId === 'CSVH') {
				var workbook = new ExcelJS.Workbook();
				await workbook.xlsx.load(reader.result);
				const worksheet = workbook.getWorksheet('Sheet1');
				// Kiểm tra xem workbook có worksheet là tên 'Sheet1' hay không
				if(!worksheet){
					// Không tìm thấy worksheet
					alert('Không tìm thấy worksheet có tên "Sheet1". Vui lòng kiểm tra file Excel.');
					$scope.load_all();
					$scope.load_filename();
					$scope.load_semester();
					$scope.load_campaign();
					$scope.reset();
					return;
				}
				var firstRow = worksheet.getRow(1);
				var firstCell = firstRow.getCell(1).value;
				var secondCell = firstRow.getCell(2).value;
				if(firstCell === 'studentId' && secondCell === 'subjectId'){
					worksheet.eachRow((row, index) => {
						if (index > 1) {
							var student = {
								studentId: row.getCell(1).value,
								subjectId: row.getCell(2).value,
								imported: {
									importedFileName: '',
								}
							};
$scope.students.push(student);
						}
					});	  
				}else{
					alert('File excel không phù hợp. Vui lòng chọn file vắng học!');
					$scope.load_all();
					$scope.load_filename();
					$scope.load_semester();
					$scope.load_campaign();
					$scope.reset();
					return;
				}
			}else if ($scope.form.campaign.campaignId === 'CSHP') {
				var workbook = new ExcelJS.Workbook();
				await workbook.xlsx.load(reader.result);
				const worksheet = workbook.getWorksheet('Sheet1');
				// Kiểm tra xem workbook có worksheet là tên 'Sheet1' hay không
				if(!worksheet){
					// Không tìm thấy worksheet
					alert('Không tìm thấy worksheet có tên "Sheet1". Vui lòng kiểm tra file Excel.');
					$scope.load_all();
					$scope.load_filename();
					$scope.load_semester();
					$scope.load_campaign();
					$scope.reset();
					return;
				}
				var firstRow = worksheet.getRow(1);
				var firstCell = firstRow.getCell(1).value;
				var secondCell = firstRow.getCell(2).value;
				if (firstCell === 'studentId' && secondCell === 'totalFee'){
					worksheet.eachRow((row, index) => {
						if (index > 1) {
							var student = {
								studentId: row.getCell(1).value,
								totalFee: row.getCell(2).value,
								imported: {
									importedFileName: '',
								}
							};
							$scope.students.push(student);
						}
					});
				}else{
					alert('File excel không phù hợp. Vui lòng chọn file học phí!');
					$scope.load_all();
					$scope.load_filename();
					$scope.load_semester();
					$scope.load_campaign();
					$scope.reset();
					return;
				}
			}else{
				alert('File excel không phù hợp!');
				$scope.load_all();
				$scope.load_filename();
				$scope.load_semester();
				$scope.load_campaign();
				$scope.reset();
				return;
			}

			

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
		item.importedFileName = $scope.fileName;
		var url = `${pathImportedData}/imported`;
		$http.post(url, item).then(resp => {
			$scope.load_all();
			$scope.load_filename();
			console.log("Import file thành công", resp);

			$scope.students.forEach(function(student){
				student.imported.importedFileName = $scope.fileName;
				$http.post("/rest/student", student).then(resp => {
					$scope.reset();
console.log("Thêm danh sách sinh viên thành công", resp.data);
				}).catch(error => {
					console.log("Lỗi", error);
				});
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
  
    /*--Lấy tất cả tên thư mục excel vào select box--*/
    $scope.load_filename = function(){
		$http.get("/rest/imported").then(resp => {
			$scope.fileExcelNames = resp.data;
		});	
	};
  
  
  
	/*--Reset form--*/
	$scope.reset = function() {
		$scope.form = {};
		$scope.students = [];
        var inputFile = document.getElementById('file-excel');
        inputFile.value = '';
	}
	
	$scope.selectedRows = [];

    $scope.selectRow = function(student) {
        var index = $scope.selectedRows.indexOf(student);
        if (index === -1) {
            $scope.selectedRows.push(student);
        } else {
            $scope.selectedRows.splice(index, 1);
        }
    };

    $scope.isSelected = function(student) {
        return $scope.selectedRows.indexOf(student) !== -1;
    };

    $scope.updateSelectedRows = function() {
        angular.forEach($scope.selectedRows, function(student) {
            $scope.updateEmployeeId(student);
        });
    };

    $scope.updateEmployeeId = function(student) {
        var url = `/rest/student/${student.studentId}`;
        $http.put(url, student).then(function(response) {
            console.log("Cập nhật mã nhân sự thành công:", response);
        }).catch(function(error) {
            console.log("Lỗi khi cập nhật mã nhân sự:", error);
        });
    };
	$scope.load_all();
	$scope.load_filename();
	$scope.load_semester();
	$scope.load_campaign();
	$scope.load_employees();
});