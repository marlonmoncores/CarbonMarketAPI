package com.hackthon.m100u.CarbonMarketAPI.api;

import com.hackthon.m100u.CarbonMarketAPI.api.helper.PrincipalHelper;
import com.hackthon.m100u.CarbonMarketAPI.api.to.UserMonthlyReportInputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.UserMonthlyReportOutputTO;
import com.hackthon.m100u.CarbonMarketAPI.domain.facade.UserReportFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping
@CrossOrigin
public class ReportController {

    @Autowired
    private UserReportFacade userReportFacade;

    @PutMapping(path = "/user/report", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserMonthlyReportOutputTO> createMonthlyReport(@RequestBody UserMonthlyReportInputTO userMonthlyReportInputTO, Principal principal) {
        userMonthlyReportInputTO.setIdUser(PrincipalHelper.getUser(principal));
        UserMonthlyReportOutputTO userReport = userReportFacade.createMonthlyReport(userMonthlyReportInputTO);
        return ResponseEntity.ok().body(userReport);
    }
}
