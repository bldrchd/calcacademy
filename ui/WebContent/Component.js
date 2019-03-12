sap.ui.define([
	"sap/ui/core/UIComponent"
], function (UIComponent) {
	"use strict";

	return UIComponent.extend("web-calculator.Component", {

		metadata : {
			manifest: "json"
		},

		init : function () {			
			UIComponent.prototype.init.apply(this, arguments);	
			var oCalculationModel = new sap.ui.model.json.JSONModel();
			this.setModel(oCalculationModel, "InputPanel");		
			
			var oHistoryModel = new sap.ui.model.json.JSONModel();
			this.setModel(oHistoryModel, "CalcHistory");		
			
			var oIdModel = new sap.ui.model.json.JSONModel();
			this.setModel(oIdModel, "Id");
		}

	});
});