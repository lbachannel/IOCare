package com.fpoly.iocare.service;

import java.util.List;

import com.fpoly.iocare.model.Campain;

public interface CampainService {
	/*--Xóa chiến dịch-*/
	public void deleteById(String id);
	
	/*--Tìm kiếm chiến dịch theo mã chiến dịch--*/
	Campain findById(String id);
	
	/*--Hiển thị tất cả chiến dịch--*/
	List<Campain> findAll();
	
	/*--Thêm mới chiến dịch--*/
	Campain create(Campain campain);
	
	/*--Kiểm tra mã chiến dịch có tồn tại hay chưa--*/
	boolean existsById(String id);

	Campain update(Campain campain);
}
