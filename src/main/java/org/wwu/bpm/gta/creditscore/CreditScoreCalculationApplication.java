package org.wwu.bpm.gta.creditscore;

import java.util.Map;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;


@ProcessApplication("Credit Score Calculation")



public class CreditScoreCalculationApplication extends ServletProcessApplication {

  @PostDeploy
  public void evaluateDecisionTable(ProcessEngine processEngine, Map<String, Object> variables) {

    DecisionService decisionService = processEngine.getDecisionService();

    VariableMap checkRecordVariables = Variables.createVariables()
      .putValue("durationDay", variables.get("durationDay"))
      .putValue("creditScore", variables.get("creditScore"));

    DmnDecisionTableResult CheckRecord = decisionService.evaluateDecisionTableByKey("CheckRecord", checkRecordVariables);
    
    int investigationNecessity = CheckRecord.getSingleEntry();

    
  }




}
