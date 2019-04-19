<?php
require "conn.php";
$authority_usrnm = $_POST["ausrname"];
$authority_eml = $_POST["aemailid"];
$authority_pasd = $_POST["apassw"];
$authority_stllnm = $_POST["astllnm"];
$authority_stlltyp = $_POST["astlltyp"];
$authority_fattendfreq = $_POST["afairfreq"];
$authority_cntry = $_POST["acntry"];
$authority_cty = $_POST["acty"];
$authority_zpcd = $_POST["azpcd"];
$authority_mblno = $_POST["amblno"];
$mysql_qry = "insert into authority_info_table (authority_username, authority_email_address, authority_password, authority_stall_name, authority_stall_type, authority_fair_attending_frequency_per_year, authority_country, authority_city, authority_zipcode, authority_mobileno) values ('$authority_usrnm ', '$authority_eml', '$authority_pasd', '$authority_stllnm', '$authority_stlltyp', '$authority_fattendfreq', '$authority_cntry', '$authority_cty', '$authority_zpcd', '$authority_mblno');";
if($conn->query($mysql_qry) === TRUE) {
	echo "insert success";
}
else {
	echo "insert not success";
}
$conn->close();
?>