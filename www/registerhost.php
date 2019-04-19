<?php
require "conn.php";
$host_usrnm = $_POST["husrname"];
$host_eml = $_POST["hemailid"];
$host_pasd = $_POST["hpassw"];
$host_cntry = $_POST["hcntry"];
$host_cty = $_POST["hcty"];
$host_mblno = $_POST["hmblno"];
$mysql_qry = "insert into host_info_table (host_user_name, host_email_address, host_password, host_country, host_city, host_mobile_no) values ('$host_usrnm ', '$host_eml', '$host_pasd', '$host_cntry', '$host_cty', '$host_mblno');";
if($conn->query($mysql_qry) === TRUE) {
	echo "insert success";
}
else {
	echo "insert not success";
}
$conn->close();
?>