package com.fpoly.iocare.rest.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.iocare.model.Campain;
import com.fpoly.iocare.model.Semester;
import com.fpoly.iocare.service.CampainService;
import com.fpoly.iocare.service.impl.CampainServiceImpl;

@CrossOrigin("*")
@RestController
public class CampainRestController {
	@Autowired
	CampainService campainService = new CampainServiceImpl();
	
	/*--Hiển thị tất cả chiến dịch--*/
	@GetMapping("/rest/campain")
	public ResponseEntity<List<Campain>> findAll(){
		return ResponseEntity.ok(campainService.findAll());
	}
	
	/*--Hiển thị 1 chiến dịch dựa vào mã chiến dịch--*/
	@GetMapping("/rest/campain/{campaignId}")
	public ResponseEntity<Campain> findOne(@PathVariable("campaignId") String campaignId){
		if(campainService.existsById(campaignId)) {
			return ResponseEntity.ok(campainService.findById(campaignId));
		}
		return ResponseEntity.notFound().build();
	}
	
	/*--Tạo mới 1 chiến dịch--*/
	@PostMapping("/rest/campain")
	public ResponseEntity<Campain> post(@RequestBody Campain campain){
		if(campainService.existsById(campain.getCampaignId())) {
			return ResponseEntity.badRequest().build(); // 400 bad request
		}
		campainService.create(campain);
		return ResponseEntity.ok(campain);
	}
	
	@PutMapping("/rest/campain/{campaignId}")
	public ResponseEntity<Campain> updateCampain(@PathVariable("campaignId") String campaignId, @RequestBody Campain updatedCampain) {
	    if (campainService.existsById(campaignId)) {
	        Campain currentCampain = campainService.findById(campaignId);
	        currentCampain.setCampaignId(updatedCampain.getCampaignId()); // Cập nhật mã chiến dịch
	        currentCampain.setCampaignName(updatedCampain.getCampaignName()); // Cập nhật tên chiến dịch

	        Campain savedCampain = campainService.update(currentCampain); // Lưu chiến dịch đã cập nhật

	        return ResponseEntity.ok(savedCampain);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}


	
	/*--Xóa chiến dịch--*/
	@DeleteMapping("/rest/campain/{campaignId}")
	public ResponseEntity<Void> delete(@PathVariable("campaignId") String campaignId){
		if(campainService.existsById(campaignId)) {
			campainService.deleteById(campaignId);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
