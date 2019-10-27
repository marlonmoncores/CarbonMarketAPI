package com.hackthon.m100u.CarbonMarketAPI.domain.facade;

import com.hackthon.m100u.CarbonMarketAPI.api.to.UserMonthlyReportInputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.UserMonthlyReportOutputTO;
import com.hackthon.m100u.CarbonMarketAPI.domain.Consumption;
import com.hackthon.m100u.CarbonMarketAPI.domain.UserPurchase;
import com.hackthon.m100u.CarbonMarketAPI.domain.service.CalculateCarbonConsumptionService;
import com.hackthon.m100u.CarbonMarketAPI.domain.service.ReadUserBuy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class UserReportFacade {

    @Autowired
    private ReadUserBuy readUserBuy;

    @Autowired
    private CalculateCarbonConsumptionService calculateCarbonConsumptionService;


    public UserMonthlyReportOutputTO createMonthlyReport(UserMonthlyReportInputTO userMonthlyReportInputTO) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date begin = dateFormat.parse(userMonthlyReportInputTO.getYear()+"-"+userMonthlyReportInputTO.getMonth()+"-01");
            Date end = dateFormat.parse(userMonthlyReportInputTO.getYear()+"-"+(1+userMonthlyReportInputTO.getMonth())+"-01");
            List<UserPurchase> userPurchaseList = readUserBuy.findByInterval(userMonthlyReportInputTO.getIdUser(),begin,end);
            UserMonthlyReportOutputTO reportOutputTO = new UserMonthlyReportOutputTO();
            Consumption carbonConsumption = calculateCarbonConsumptionService.calculateTotalConsumption(userPurchaseList);
            reportOutputTO.setConsumption(carbonConsumption);

            return reportOutputTO;
        } catch (ParseException e) {
            throw new RuntimeException("ERROR CONVERTING DATE");
        }
    }
}
