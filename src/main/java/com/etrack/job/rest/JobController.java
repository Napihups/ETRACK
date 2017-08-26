package com.etrack.job.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.etrack.global.GlobalAppConstant;
import com.etrack.job.model.JobRequestModel;
import com.etrack.job.service.IJobService;
import com.etrack.job.util.JobUtility;
import com.etrack.security.JwtTokenUtility;

@RestController
@RequestMapping("job")
public class JobController {

	@Autowired
	private IJobService jobService;
	
	@Autowired
	private JwtTokenUtility jwtTokenUtil;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> createNewJob(@RequestBody JobRequestModel jobReqModel, HttpServletRequest request){
		if(JobUtility.validateJobRequest(jobReqModel)){
			 String token = request.getHeader(GlobalAppConstant.APP_TOKEN_HEADER);
			 String username = jwtTokenUtil.getUsernameFromToken(token);
			 jobReqModel.setReqBy(username);
			jobService.createNewJob(jobReqModel);
			
			return null;
		} else {
			return ResponseEntity.ok("Content of the JOB is invalid, please check again before submitting");
		}
	}
	
	
}
