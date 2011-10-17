function addEmail() {
	var index = $("#emails tr").length;
	$("#emails").append(
		"<tr>" +
		"	<td>" +
		"		<select name='emails[" + index + "].rel' onchange='setLabel(this)'>" +
		"			<option></option>" +
		"			<option value='http://schemas.google.com/g/2005#home'>Home</option>" +
		"			<option value='http://schemas.google.com/g/2005#work'>Work</option>" +
		"			<option value='http://schemas.google.com/g/2005#other'>Other</option>" +
		"		</select>" +
		"	</td>" +
		"	<td><input type='text' name='emails[" + index + "].label' class='label'/></td>" +
		"	<td><input type='text' name='emails[" + index + "].address'/></td>" +
		"	<td><input type='checkbox' name='emails[" + index + "].primary' onchange='setPrimary(this)'/></td>" +
		"</tr>");
}

function addPhone() {
	var index = $("#phones tr").length;
	$("#phones").append(
		"<tr>" +
		"	<td>" +
		"		<select name='phones[" + index + "].rel' onchange='setLabel(this)'>" +
		"			<option></option>" +
		"			<option value='http://schemas.google.com/g/2005#assistant'>Assistant</option>" +
		"			<option value='http://schemas.google.com/g/2005#callback'>Callback</option>" +
		"			<option value='http://schemas.google.com/g/2005#car'>Car</option>" +
		"			<option value='http://schemas.google.com/g/2005#company_main'>Company Main</option>" +
		"			<option value='http://schemas.google.com/g/2005#fax'>Fax</option>" +
		"			<option value='http://schemas.google.com/g/2005#home'>Home</option>" +
		"			<option value='http://schemas.google.com/g/2005#home_fax'>Home Fax</option>" +
		"			<option value='http://schemas.google.com/g/2005#idsn'>ISDN</option>" +
		"			<option value='http://schemas.google.com/g/2005#main'>Main</option>" +
		"			<option value='http://schemas.google.com/g/2005#mobile'>Mobile</option>" +
		"			<option value='http://schemas.google.com/g/2005#other'>Other</option>" +
		"			<option value='http://schemas.google.com/g/2005#other_fax'>Other Fax</option>" +
		"			<option value='http://schemas.google.com/g/2005#pager'>Pager</option>" +
		"			<option value='http://schemas.google.com/g/2005#radio'>Radio</option>" +
		"			<option value='http://schemas.google.com/g/2005#telex'>Telex</option>" +
		"			<option value='http://schemas.google.com/g/2005#tty_tdd'>TTY TDD</option>" +
		"			<option value='http://schemas.google.com/g/2005#work'>Work</option>" +
		"			<option value='http://schemas.google.com/g/2005#work_fax'>Work Fax</option>" +
		"			<option value='http://schemas.google.com/g/2005#work_mobile'>Work Mobile</option>" +
		"			<option value='http://schemas.google.com/g/2005#work_pager'>Work Pager</option>" +
		"		</select>" +
		"	</td>" +
		"	<td><input type='text' name='phones[" + index + "].label' class='label'/></td>" +
		"	<td><input type='text' name='phones[" + index + "].number'/></td>" +
		"	<td><input type='checkbox' name='phones[" + index + "].primary' onchange='setPrimary(this)'/></td>" +
		"</tr>");
}

function setLabel(select) {
	var disable = select.value != "";
	$(select).closest("tr").find(".label").prop("disabled", disable);
}

function setPrimary(checkbox) {
	if(!$(checkbox).prop('checked')) {
		return;
	}

	$(checkbox).closest("tbody").find("input[type='checkbox']").each(function(index, element) {
		if(element != checkbox) {
			$(element).removeAttr('checked');
		}
	});
}