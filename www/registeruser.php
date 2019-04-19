<?php
require "conn.php";
$user_frstnm = $_POST["ufname"];
$user_lstnm = $_POST["ulname"];
$user_eml = $_POST["uemailid"];
$user_pasd = $_POST["upassw"];
$user_gndr = $_POST["ugndr"];
$user_bdate = $_POST["ubdate"];
$user_prfsn = $_POST["uprof"];
$user_cntry = $_POST["ucntry"];
$user_cty = $_POST["ucty"];
$user_zpcd = $_POST["uzpcd"];
$user_mblno = $_POST["umblno"];
$mysql_qry = "insert into normal_user_info_table (user_first_name, user_last_name, user_email_address, user_password, user_gender, user_birthdate, user_profession, user_country, user_city, user_zipcode, user_mobileno) values ('$user_frstnm', '$user_lstnm', '$user_eml', '$user_pasd', '$user_gndr', '$user_bdate', '$user_prfsn', '$user_cntry', '$user_cty', '$user_zpcd', '$user_mblno');";
if($conn->query($mysql_qry) === TRUE) {
	echo "insert success";
}
else {
	echo "insert not success";
}
$conn->close();
?>