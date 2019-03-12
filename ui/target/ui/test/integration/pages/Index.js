sap.ui.require([
		"sap/ui/test/Opa5",
		"sap/ui/test/actions/Press",
		"sap/ui/test/actions/EnterText",
		"sap/ui/test/matchers/Properties",
], function (Opa5, Press, EnterText, Properties) {
		"use strict";
		
		Opa5.createPageObjects({
				onTheIndexPage: {
						actions: {
							iEnterInput: function (expression) {
								return this.waitFor({
									controlType: "sap.m.Input",
									id: "input",
									viewName: "App",
									actions: new EnterText({
										text: expression
									}),
									errorMessage: "Did not received input."
								});
							},
							iClickSubmitButton: function () {
								return this.waitFor({
									controlType: "sap.m.Button",
									id: "input-submit",
									viewName: "App",
									actions: new Press(),
									errorMessage: "Did not find the 'Submit' button."
								});
							}
						},
						assertions: {
							iShouldSeeTheResult: function (result) {
									return this.waitFor({
										controlType: "sap.m.Text",
										id: "result",
										viewName: "App",
				                    	matchers : new Properties({
				                    		text : result
				                    	}),
				                    	success: function() {
				                    		Opa5.assert.ok(true, "I can see the result:  " + result);
				                    	},
				                    	errorMessage: "Cannot find the control or the value didn't matched the required result."
								});
							}
						}
				}
		});
});