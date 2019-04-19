<?php
require "conn.php";
$user_email = $_POST["email"];
$user_pass = $_POST["pass"];
//$user_email = 'v';
//$user_pass = 'v';
$mysql_qry = "select * from host_info_table where host_email_address = '".$user_email."' and host_password = '".$user_pass."';";
$result = mysqli_query($conn,$mysql_qry);
$return_array = array();
		
	while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC))
	{
		$row_array['host_id'] = $row['host_id'];
		$row_array['host_user_name'] = $row['host_user_name'];
		$row_array['host_email_address'] = $row['host_email_address'];
		$row_array['host_password'] = $row['host_password'];
		$row_array['host_country'] = $row['host_country'];
		$row_array['host_city'] = $row['host_city'];
		$row_array['host_mobile_no'] = $row['host_mobile_no'];
		array_push($return_array, $row_array);
	}
	
	echo json_encode($return_array);
$conn->close();

?>