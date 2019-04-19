<?php
require "conn.php";
$user_email = $_POST["emailid"];
$user_pass = $_POST["passw"];;
$mysql_qryu = "select * from normal_user_info_table where user_email_address like '$user_email' and user_password like '$user_pass';";
$mysql_qrya = "select * from authority_info_table where authority_email_address like '$user_email' and authority_password like '$user_pass';";
$mysql_qryh = "select * from host_info_table where host_email_address like '$user_email' and host_password like '$user_pass';";
$resultu = mysqli_query($conn,$mysql_qryu);
$resulta = mysqli_query($conn,$mysql_qrya);
$resulth = mysqli_query($conn,$mysql_qryh);
if(mysqli_num_rows($resultu) > 0) {
	echo "normal user login success";
}
else if(mysqli_num_rows($resulta) > 0) {
	echo "authority login success";
}
else if(mysqli_num_rows($resulth) > 0) {
	echo "host login success";
}
else {
	echo "login not success";
}
$conn->close();
?>