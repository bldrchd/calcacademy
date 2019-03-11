sap.ui.define([ "sap/ui/core/mvc/Controller", 
                  "sap/m/MessageToast",
                  "sap/ui/model/json/JSONModel",
                  "sap/ui/model/resource/ResourceModel"
], function(Controller, MessageToast, JSONModel, ResourceModel) {
	"use strict";

	return Controller.extend("com.sap.calc.calculatorui.controller.CalcController", {
				onInit : function() {
					var oData = {
							expression : "enter expression"
					};
					var oModel = new JSONModel(oData);
					this.getView().setModel(oModel);

					var myModel = new ResourceModel({
						bundleName: "com.sap.calc.calculatorui.calc"
					});
					this.getView().setModel(myModel, "calc");
				},
				
				onCalculate : function() {
					var calc = new ResourceModel({
						bundleName: "com.sap.calc.calculatorui.calc"
					});
					this.getView().setModel(calc, "calc");
					var oBundle = this.getView().getModel("calc").getResourceBundle();
//from here - press on calculate
					var view = this.getView();
					var expression = view.byId("expressioninput").getValue();
					var responsedata;
					$.ajax({
						type: "POST", 
						//url: 'http://localhost:8081/com.sap.calcacademy.calculator/expression',
						url: 'https://calci311648trial.hanatrial.ondemand.com/com.sap.calcacademy.calculator-0.0.1-SNAPSHOT/expression',
						data: expression, 
						crossDomain: true,
						//data: JSON.stringify(expression),
						
						success: function(data) {
							var resultID = data.resultID;
							var expression = data.expression;
							responsedata = $.ajax({ 
								type: "GET",   
								//url: "http://localhost:8081/com.sap.calcacademy.calculator/result/"+resultID,
								url: "https://calci311648trial.hanatrial.ondemand.com/com.sap.calcacademy.calculator-0.0.1-SNAPSHOT/result/"+resultID,   
								async: false,
								dataType: "json",
								crossDomain: true,
								success: function (ndata) {
									var jresult = ndata.result;
									var oModel2 = new JSONModel({
										result : jresult,
										resultID : ndata.resultID,
										expression : data.expression
									});
									view.setModel(oModel2, "CalculatorUIPanel");
								}
		                    	}).responseText;
							alert(responsedata);
						},
						error: function (xhr) {
							view.getController().displayErrorMessage(xrh);
						}
					});
					view.byId("expressioninput").setValue("");
				}
				
			});
});