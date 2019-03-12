sap.ui.define([ "sap/ui/core/mvc/Controller",
				"sap/ui/model/json/JSONModel", "sap/m/MessageToast" ],
	function(Controller, JSONModel, MessageToast) {
	"use strict";
	return Controller.extend("ui.controller.App", {

		onPress : function() {
			var view = this.getView();
			var oController = view.getController();
			var expression = view.byId("input").getValue();
			$.ajax({
				type: "POST", 
				url: '/api/calculate/result',
				data: JSON.stringify({input: expression}),
				success: function(data) {
					oController.addIdToArray(data);
				},
				contentType: "application/json",
				error: function(xhr) {
					view.getController().displayErrorMessage(xhr);
				}
				
			});	
			view.byId("input").setValue("");
		},
		
		addIdToArray : function(data) {
			var idModel = this.getView().getModel("Id");
			var idArray;
			if (jQuery.isEmptyObject(idModel.getProperty("/"))) {
				idArray = new Array();
			} else {
				idArray = idModel.getProperty("/");
			}
			idArray.push(data);
			idModel.setData(idArray);
			
			this.getView().getController().clearErrorMessageField();
		},
		
		clearErrorMessageField  : function() {
			var oModel = new JSONModel({
				errorMessage : ""
			});
			this.getView().setModel(oModel, "InputPanel");
		},
		
		displayErrorMessage : function(xhr) {
			var errorText = JSON.parse(xhr.responseText).errorMessage;
			var oModel = new JSONModel({
				errorMessage : errorText
			});
			this.getView().setModel(oModel, "InputPanel");
		}
		
		
	});
});
