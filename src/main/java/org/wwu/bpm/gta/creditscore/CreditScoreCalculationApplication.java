package org.wwu.bpm.gta.creditscore;

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
  public void evaluateDecisionTable(ProcessEngine processEngine) {

    DecisionService decisionService = processEngine.getDecisionService();

    VariableMap variables = Variables.createVariables()
      .putValue("durationDay", 100)
      .putValue("creditScore", 8);

    DmnDecisionTableResult CheckRecord = decisionService.evaluateDecisionTableByKey("CheckRecord", variables);
    String investigationNecessity = CheckRecord.getSingleEntry();

    System.out.println("Is it necessary to investigate: " + investigationNecessity);
  }

}
