package com.ashokit.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.constants.AppConstant;
import com.ashokit.entity.Plan;
import com.ashokit.properties.AppProperties;
import com.ashokit.service.PlanServiceInterface;

@RestController
public class InsuranceRC {

	private PlanServiceInterface service;
	
	private Map<String, String> messages;

	//Autowired is optional because here only one constructor is present
	public InsuranceRC(PlanServiceInterface service, AppProperties appProps)
	{
		this.service = service;
		this.messages = appProps.getMessages();
	}
	
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories() {
		Map<Integer, String> categories = service.getPlanCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {
		String responseMsg = AppConstant.EMPTY_STR;

		boolean isSaved = service.savePlan(plan);
		
		if (isSaved) {
			responseMsg = messages.get(AppConstant.PLAN_SAVE_SUCC);
		} else {
			responseMsg = messages.get(AppConstant.PLAN_SAVE_FAIL);
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
	}

	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> plans() {
		List<Plan> allPlans = service.getAllPlans();
		return new ResponseEntity<>(allPlans, HttpStatus.OK);
	}

	@GetMapping("/plans/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {
		Plan plan = service.getPlanById(planId);
		return new ResponseEntity<>(plan, HttpStatus.OK);
	}

	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(@PathVariable Plan plan) {
		boolean isUpdated = service.updatePlan(plan);
		String msg = AppConstant.EMPTY_STR;

		if (isUpdated)
			msg = messages.get("planUpdateSucc");
		else
			msg = messages.get("planUpdateSucc");

		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {
		boolean isDeleted = service.deletePlanById(planId);
		String msg = "";

		if (isDeleted)
			msg = messages.get("planDeleteSucc");
		else
			msg = messages.get("planDeleteFail");

		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId, @PathVariable String status) {
		boolean isStatusChanged = service.planStatusChange(planId, status);
		String msg = "";

		if (isStatusChanged)
			msg = messages.get("planStatusChange");
		else
			msg = messages.get("planStatusChangeFail");

		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}