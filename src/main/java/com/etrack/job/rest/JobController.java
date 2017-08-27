package com.etrack.job.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.etrack.global.GlobalAppConstant;
import com.etrack.global.GlobalResponse;
import com.etrack.job.exception.InvalidJobDataException;
import com.etrack.job.model.JobRequestModel;
import com.etrack.job.service.IJobService;
import com.etrack.job.util.JobUtility;
import com.etrack.security.JwtTokenUtility;

@Controller
@RequestMapping("job")
public class JobController {

	@Autowired
	private IJobService jobService;

	@Autowired
	private JwtTokenUtility jwtTokenUtil;

	/**
	 * Upon Calling this endpoint, joReqModel should be mapped with the JSON
	 * parsed from the front-end js. as of now The fields that are to be
	 * provided from the front end is ..(Subject to changes) [JOB_DESCRIPTION]
	 * [JOB_REMARKS] [REQUEST_TO] The field below will be initialize from this
	 * endpoint upon calling. [JOB_NO] [REQUEST_BY] [JOB_STATUS] [OPEN_DATE] [CLOSE_DATE] -
	 * will not be filled at the first creation of job.Will be fill at runtime
	 * 
	 * @param jobReqModel
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> createNewJob(@RequestBody JobRequestModel jobReqModel, HttpServletRequest request) {
	
		String token = request.getHeader(GlobalAppConstant.APP_TOKEN_HEADER);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		jobReqModel.setReqBy(username);
		JobUtility.setOpenJobDate(jobReqModel);
		JobUtility.setJobNumber(jobReqModel);	
		jobReqModel.setJobStatus(JobRequestModel.OPEN);
		try{
			boolean i = jobService.createNewJob(jobReqModel);
			GlobalResponse resp = new GlobalResponse();
	       	resp.setSuccess(true);
	       	resp.setMsg("Succesfully Created Job ");
	       	return ResponseEntity.ok(resp);
		} catch(InvalidJobDataException e){
			GlobalResponse resp = new GlobalResponse();
	       	resp.setSuccess(false);
	       	resp.setMsg("Failed to Create job ");
			return ResponseEntity.ok(resp);
		}
	}
	
	
	//FOR TESTING 
	@RequestMapping("/112")
	@PreAuthorize("hasAnyRole('USER')")
	public String displayJob(Model model){
//		JobRequestModel mod = new JobRequestModel();
//		mod.setJobDescription("This is the Test DESCRIPTION");
//		mod.setRemarks("This is th remarks of the job ");
//		mod.setJobNo("1125");
//		mod.setJobStatus(JobRequestModel.OPEN);
//		mod.setOpenDate(DateUtil.getTimestamp(new Date()));
//		mod.setReqBy("Napihups");
//		mod.setReqTo("Jatizso");
		model.addAttribute("DESC","This is the Test DESCRIPTION");
		model.addAttribute("REMARKS","This is th remarks of the job");
		model.addAttribute("JOB_NO", "1125");		
		return "jobview";
		
	}
	
	
	
//	@RequestMapping("/job/{id}")
//	@PreAuthorize("hasAnyRole('USER')")
//	public ResponseE
	
//	@RequestMapping("/dashboard")
//	@PreAuthorize("hasAnyRole('USER')")
//	public String welcome(Model model, HttpServletRequest request){
//		 String token = request.getHeader(TOKEN_HEADER);
//       String username = jwtTokenUtil.getUsernameFromToken(token);
//       EUserDetails user = (EUserDetails) userDetailsService.loadUserByUsername(username);
//		model.addAttribute("message" , "Welcome to JSP spring boot");
//		model.addAttribute("welcomename", user.getUserName());
//		return "welcome";
//	}

}
