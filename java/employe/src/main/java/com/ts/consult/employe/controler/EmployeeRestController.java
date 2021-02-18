package com.ts.consult.employe.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ts.consult.employe.dto.ReceiveDto;
import com.ts.consult.employe.entities.EmployeEntity;
import com.ts.consult.employe.service.EmployeService;

@RestController
@RequestMapping(value = "/api/public", headers = "Accept=application/json")
public class EmployeeRestController {

	@Value("${build.version}")
	private String buildVersion;

	@Autowired
	private EmployeService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "{\"version\":\"" + buildVersion + "\"}";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ReceiveDto addEmploye(@RequestBody ReceiveDto rdto, @RequestHeader HttpHeaders headers) {

		EmployeEntity ee = rdto.getEmploye();
		rdto.setEmploye(service.addEmploye(ee));
		if (rdto.getEmploye().getId() == null) {
			rdto.setError("This user name has already on database");
		}

		return rdto;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<EmployeEntity> navigation(@RequestParam(defaultValue = "id") String column) {
		// System.out.println(column);
		List<EmployeEntity> ees = service.fillAllDiff(column);

		return ees;
	}
}
