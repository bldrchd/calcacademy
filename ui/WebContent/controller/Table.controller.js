sap.ui.define([ "sap/ui/core/mvc/Controller", "sap/ui/model/json/JSONModel",
		"sap/m/MessageToast" ], function(Controller, JSONModel, MessageToast) {
	"use strict";
	return Controller.extend("ui.controller.Table", {
		onInit : function() {
			var oController = this.getView().getController();
			oController.initTable();
			oController.poll();
		},

		initTable : function() {
			this.oModel = this.getOwnerComponent().getModel("CalcHistory");
			this.oModel.loadData('/api/calculate/history');
			this.getView().setModel(this.oModel, "CalcHistory");
		},
		
		poll : function() {
			var view = this.getView();
			var oController = view.getController();
			setInterval(function() {
					var oIdModel = view.getModel("Id");
					var idArray = oIdModel.getProperty("/");
					if (!jQuery.isEmptyObject(idArray)) {
						var uri = idArray[0].uri;
						$.ajax({
							url : uri,
							type : "GET",
							success : function(data) {
								if (data.result != undefined) {
									oController.updateTable(data);
									idArray.shift();
								}
							},
						})
					}
				}, 2000);	
		},
		
		updateTable : function(newData) {
			var oldData = this.oModel.getProperty("/");
			oldData.push(newData);
			this.oModel.setData(oldData);
			this.getView().setModel(this.oModel, "CalcHistory");
		},
		
		clearTable : function() {
			$.get({
				async : false,
				url : '/api/calculate/cleanup',
				type : 'delete',
				contentType : "application/json",
				error : function(xhr) {
					sap.m.MessageToast.show("Deleting history failed.", {
						duration : 150,
						my : "center center",
						at : "center center"
					});
				}
			});
			this.oModel.loadData('/api/calculate/history');
			this.getView().setModel(oModel, "CalcHistory");
		}

	});
});
