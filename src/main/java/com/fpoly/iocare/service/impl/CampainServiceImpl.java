package com.fpoly.iocare.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.iocare.dao.CampainDAO;
import com.fpoly.iocare.model.Campain;
import com.fpoly.iocare.service.CampainService;

@Service
public class CampainServiceImpl implements CampainService{
	
	@Autowired
	CampainDAO dao;
	
	/*--Xóa chiến dịch--*/
	@Override
	public void deleteById(String id) {
		dao.deleteById(id);	
	}
	
	/*--Tìm kiếm chiến dịch theo mã chiến dịch--*/
	@Override
	public Campain findById(String id) {
		return dao.findById(id).get();
	}
	
	/*--Thêm mới chiến dịch--*/
	@Override
	public Campain create(Campain campain) {
		return dao.save(campain);
	}
	
	/*--Kiểm tra mã chiến dịch có tồn tại hay chưa--*/
	@Override
	public boolean existsById(String id) {
		if(dao.existsById(id)) 
			return true;
		return false;
	}
	
	/*--Hiển thị tất cả học kỳ--*/
	@Override
	public List<Campain> findAll() {
		return dao.findAll();
	}

}
