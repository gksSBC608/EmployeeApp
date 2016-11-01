/**
 * 
 */
function initialize() {

	var nError = document.getElementById("nameError");
	var dError = document.getElementById("dsgnError");
	var sError = document.getElementById("salaryError");

	nError.innerHTML = "";
	dError.innerHTML = "";
	sError.innerHTML = "";
}
/**
 * 
 * @returns {Boolean}
 * 
 * This method validates the credentials enterd by the user to login, so taht
 * null or abrupt value doesn't goto the server
 */
function validate() {
	var validated = true;
	var nE = document.getElementById("name");
	var dE = document.getElementById("dsgn");
	var sE = document.getElementById("salary");
	var nError = document.getElementById("nameError");
	var dError = document.getElementById("dsgnError");
	var sError = document.getElementById("salaryError");
	var name = nE.value;
	var designation = dE.value;
	var salary = sE.value;

	/*if (name == "") {
		nError.innerHTML = "Name can't be blank";
		validated = false;
		nE.focus();
	}
	if (designation == "") {
		dError.innerHTML = "Designation can't be blank";
		validated = false;
		dE.focus();
	}
	if (salary == "") {
		sError.innerHTML = "Salary can't be blank";
		validated = false;
		sE.focus()
	}*/

	var alphaExp = /^[A-Za-z]{1,}[\s]{0,}[A-Za-z\s]{0,}$/;
	if (!name.match(alphaExp)) {
		validated = false;
		nE.focus();
		nError.innerHTML = "Name should be of expected format";
	}
	if (!designation.match(alphaExp)) {
		validated = false;
		dE.focus();
		dError.innerHTML = "Designation should be of expected format";
	}
	var salaryExp = /^\d+(\.\d{1,2})?$/;
	if (!salary.match(salaryExp)) {
		validated = false;
		sE.focus();
		sError.innerHTML = "Salary should be a decimal with 2 precision";
	}
	
	return validated;
}