

function addService(storeID){
var appname=window.location.pathname.substr(0, window.location.pathname.lastIndexOf('/updateService'));
document.getElementById('ourCare').action = appname+"/dropdownService?getQry=addService&storeID="+storeID;
document.getElementById('ourCare').submit();
}

function bookAppointment(storeID){
var appname=window.location.pathname.substr(0, window.location.pathname.lastIndexOf('/'));
document.getElementById('bookAppointment').action = appname+"/bookAppointment?getQry=bookAppointment&storeID="+storeID;
document.getElementById('bookAppointment').submit();
}

//For combos dropdown


function getServices(careType){
var appname=window.location.pathname.substr(0, window.location.pathname.lastIndexOf('/'));
document.getElementById('combo').action = appname+"/add?getQry=getServices&careType="+careType;
document.getElementById('combo').submit();
}

$(document).ready(function() {
   $("#careType").on('change',function() {
	 var careType = $(this).val();
      var s = '';
      if (careType !=null) {
	           // alert("careType : "+careType);
      	$.ajax({
	    type : 'Get',
        url : 'getServices',
        data : { "careType" : careType },
        success : function(result) {
	   //dataType: 'json'; 
	     // alert("json  "+result);
	     // alert("json length "+result.length);
	       var result = JSON.stringify(result)
                    // alert("result  "+result);
        	 var result = JSON.parse(result);
        	  console.log(result)
         // alert("result  "+result);
        	  for (var i = 0; i < result.length; i++) {
	
		      // alert("result i "+result[i].careName);
		 
         s += '<option value="'+ result[i].careName + '">'+ result[i].careName+ '</option>';
        	}
        	$('#careName').html(s);
        }
      });
     }
     //reset data
  
    	$('#careName').html(s);
   });
  });
  
// for employee domain form dropdown
function domainForm(storeID){
var appname=window.location.pathname.substr(0, window.location.pathname.lastIndexOf('/'));

document.getElementById('DomainObj').action = appname+"/addDomain?getQry=domainForm&storeID="+storeID;

document.getElementById('DomainObj').submit();
}

// Employee General triple dropdown

function editEmpProfile(storeID){
var appname=window.location.pathname.substr(0, window.location.pathname.lastIndexOf('/wecandoit'));

document.getElementById('EmpObj').action = "/dropdown?getQry=editEmpProfile&storeID="+storeID;


document.getElementById('EmpObj').submit();
}


function editEmpProfileBranch(branchID){
var appname=window.location.pathname.substr(0, window.location.pathname.lastIndexOf('/wecandoit'));

document.getElementById('EmpObj').action = appname+"/dropdown?getQrybranch=editEmpProfileBranch&branchID="+branchID;

document.getElementById('EmpObj').submit();
}




// Job openings triple dropdown

function openingsList(storeID){
var appname=window.location.pathname.substr(0, window.location.pathname.lastIndexOf('/dwovsk'));
document.getElementById('objJob').action = appname+"/saveJob?getQry=openingsList&storeID="+storeID;
document.getElementById('objJob').submit();
}

function openingsListBranch(branchID){
var appname=window.location.pathname.substr(0, window.location.pathname.lastIndexOf('/dwovsks'));
document.getElementById('objJob').action = appname+"/saveJob?getQrybranch=openingsListBranch&branchID="+branchID;
document.getElementById('objJob').submit();
}


//for appointment dropdown
function bookAppointmentjs(storeID){
var appname=window.location.pathname.substr(0, window.location.pathname.lastIndexOf('/'));
document.getElementById('savecustomerAppointment').action = appname+"/bookAppointment?getQry=bookAppointmentjs&storeID="+storeID;
document.getElementById('savecustomerAppointment').submit();
}

// For Vendor dropdown



function vendorForm(storeID){
var appname=window.location.pathname.substr(0, window.location.pathname.lastIndexOf('/'));



document.getElementById('objVendors').action = appname+"/addVendors?getQry=vendorForm&storeID="+storeID;



document.getElementById('objVendors').submit();
}
// For product selling dropdown

function sellingDropdownForm(storeID){
var appname=window.location.pathname.substr(0, window.location.pathname.lastIndexOf('/'));



document.getElementById('obj').action = appname+"/productList?getQry=sellingDropdownForm&storeID="+storeID;



document.getElementById('obj').submit();
}

