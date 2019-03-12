package com.egabi.blockchain.chequeClearing.configuration;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.webflow.core.FlowException;
import org.springframework.webflow.execution.FlowExecutionOutcome;
import org.springframework.webflow.execution.repository.NoSuchFlowExecutionException;
import org.springframework.webflow.mvc.servlet.AbstractFlowHandler;

public class ChequeRegisterationFlowHandler  extends AbstractFlowHandler {

	
	
	    private static final String DEFAULT_URL = "/Registeration";

		@Override
		public String handleExecutionOutcome(FlowExecutionOutcome outcome, HttpServletRequest req, HttpServletResponse res) {
			System.out.println("user name ya ged3an "+req.getAttribute("user"));
			return DEFAULT_URL;
		}

		@Override
		public String handleException(FlowException e, HttpServletRequest request, HttpServletResponse response) {
			if (e instanceof NoSuchFlowExecutionException) {
				return DEFAULT_URL;
			}
			else {
				throw e;
			}
		}
}