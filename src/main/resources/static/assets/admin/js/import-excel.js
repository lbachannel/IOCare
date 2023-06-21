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
	
	$scope.tableDataAssigned = []; // Mảng chứa danh sách sinh viên đã phân công
	
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
		//Gọi về yêu cầu xác thực user1
		$http.get("/security/user1").then(resp => {
			
			//Nếu đúng vai trò user1 thì tiến hành import
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
			 
		}).catch(error => {
			
			//Nếu không phải admin thì tiến hành xử lí lỗi!
			$scope.errorHandle(error);
			
		});
		
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

  	/*-- Hàm gọi xử lí lỗi khi xác thực --*/
    $scope.errorHandle = function(error){
	
		//Xuất ra lỗi gặp phải
		console.log(error);
		
		//Load lại yêu cầu để chuyển hướng đến trang đăng nhập
		$window.location.href = "/security/login/form";
	}
  
	/*--Reset form--*/
	$scope.reset = function() {
		$scope.form = {};
		$scope.students = [];
        var inputFile = document.getElementById('file-excel');
        inputFile.value = '';
	}
	$scope.load_all();
	$scope.load_filename();
	$scope.load_semester();
	$scope.load_campaign();
	$scope.load_employees();
<<<<<<< HEAD
=======
  
>>>>>>> parent of 08a169a (Merge branch 'LongKiet')
  
/*--Phân công form--*/



// Function để phân công sinh viên từ bảng "Chưa phân công" sang bảng "Đã phân công"
$scope.assignStudents = function() {
  // Kiểm tra xem đã chọn mã nhân sự hay chưa
  if (!$scope.form.selectedEmployee) {
    // Hiển thị thông báo lỗi nếu chưa chọn mã nhân sự
    alert("Vui lòng chọn mã nhân sự trước khi phân công!");
    return;
  }

  // Lặp qua danh sách sinh viên trong mảng tableData
  for (var i = $scope.tableData.length - 1; i >= 0; i--) {
    var student = $scope.tableData[i];
    // Kiểm tra xem sinh viên có được chọn hay không
    if (student.selected) {
      // Thiết lập employeeId cho sinh viên đã phân công
      student.employeeId = $scope.form.selectedEmployee;
      // Thêm sinh viên vào mảng tableDataAssigned
      $scope.tableDataAssigned.push(student);
      alert("Phân công thành công!")
      // Xóa sinh viên khỏi mảng tableData
      $scope.tableData.splice(i, 1);
    }
  }
  $scope.sortTableDataAssigned();
};

// Function để kiểm tra xem có sinh viên nào được chọn hay không
$scope.hasSelectedStudents = function() {
  return $scope.tableData.some(function(student) {
    return student.selected;
  });
};

// Function để cập nhật danh sách sinh viên đã được chọn
$scope.updateSelectedStudents = function(student) {
  // Kiểm tra xem sinh viên có được chọn hay không
  if (student.selected) {
    // Thêm sinh viên vào mảng tableDataAssigned
    $scope.tableDataAssigned.push(student);
  } else {
    // Xóa sinh viên khỏi mảng tableDataAssigned
    var index = $scope.tableDataAssigned.indexOf(student);
    if (index !== -1) {
      $scope.tableDataAssigned.splice(index, 1);
    }
  }
  $scope.assignStudents = function() {
  // Kiểm tra xem đã chọn mã nhân sự hay chưa
  if (!$scope.form.selectedEmployee) {
    // Hiển thị thông báo lỗi nếu chưa chọn mã nhân sự
    alert("Vui lòng chọn mã nhân sự trước khi phân công!");
    return;
  }

  // Lặp qua danh sách sinh viên trong mảng tableData
  for (var i = $scope.tableData.length - 1; i >= 0; i--) {
    var student = $scope.tableData[i];
    // Kiểm tra xem sinh viên có được chọn hay không
    if (student.selected) {
      // Thiết lập employeeId cho sinh viên đã phân công
      student.employeeId = $scope.form.selectedEmployee;
      // Thêm sinh viên vào mảng tableDataAssigned
      $scope.tableDataAssigned.push(student);
      // Xóa sinh viên khỏi mảng tableData
      $scope.tableData.splice(i, 1);
    }
  }

  // Gọi hàm sortTableDataAssigned() để sắp xếp lại bảng "Đã phân công"
  $scope.sortTableDataAssigned();
};
};
<<<<<<< HEAD
=======


>>>>>>> parent of 08a169a (Merge branch 'LongKiet')
	
	// Thêm một mảng selectedStudents để lưu trạng thái chọn của sinh viên
$scope.selectedStudents = [];

// Hàm cập nhật danh sách sinh viên được chọn
$scope.updateSelectedStudents = function(student) {
    if (student.selected) {
        $scope.selectedStudents.push(student);
    } else {
        var index = $scope.selectedStudents.findIndex(function(selectedStudent) {
            return selectedStudent.studentId === student.studentId;
        });
        $scope.selectedStudents.splice(index, 1);
    }
};

// Hàm cập nhật mã nhân sự cho các sinh viên được chọn
$scope.updateSelectedEmployees = function() {
    var selectedEmployeeId = $scope.form.selectedEmployee;
    $scope.selectedStudents.forEach(function(student) {
        student.employee.employeeId = selectedEmployeeId;
    });
};

// Cập nhật danh sách sinh viên được chọn khi chọn mã nhân sự
$scope.update = function() {
    $scope.updateSelectedEmployees();
};

// Cập nhật trạng thái chọn của sinh viên khi thay đổi danh sách sinh viên
$scope.$watch('tableData', function(newTableData, oldTableData) {
    if (newTableData !== oldTableData) {
        newTableData.forEach(function(student) {
            var isSelected = $scope.selectedStudents.some(function(selectedStudent) {
                return selectedStudent.studentId === student.studentId;
            });
            student.selected = isSelected;
        });
    }
}, true);

	
});