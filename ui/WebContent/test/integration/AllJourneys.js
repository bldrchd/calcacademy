jQuery.sap.require("sap.ui.qunit.qunit-css");
jQuery.sap.require("sap.ui.thirdparty.qunit");
jQuery.sap.require("sap.ui.qunit.qunit-junit");
jQuery.sap.require("sap.ui.qunit.qunit-coverage");

QUnit.config.autostart = false;

sap.ui.require([
"sap/ui/test/Opa5",
'sap/ui/test/opaQunit',

], function(Opa5, opaTest) {

   "use strict";
   
	QUnit.module("User interaction with calculator");
	
   Opa5.extendConfig({
  	 viewNamespace: "ui.view."
   });
   
   opaTest("Should see the result from the calculation on the page", function(Given, When, Then) {
		Given.iStartMyAppInAFrame({source: "./mockServer.html"});
		var input = "1 + 3.25";
	  	var result = "4.25";
	  	
	  	When.onTheIndexPage.iEnterInput(input);
	  	When.onTheIndexPage.iClickSubmitButton();
	  	
	  	Then.onTheIndexPage.iShouldSeeTheResult(result).and.iTeardownMyAppFrame();
	  });

   sap.ui.require([
   ], function() {
  	 QUnit.start();
   });
});