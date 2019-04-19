<?php
require "conn.php";
$user_email = $_POST["email"];
$user_pass = $_POST["pass"];
//$user_email = 'w';
//$user_pass = 'w';
$mysql_qry = "select * from authority_info_table where authority_email_address = '".$user_email."' and authority_password = '".$user_pass."';";
$result = mysqli_query($conn,$mysql_qry);
$return_array = array();
		
	while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC))
	{
		$row_array['authority_id'] = $row['authority_id'];
		$row_array['authority_username'] = $row['authority_username'];
		$row_array['authority_email_address'] = $row['authority_email_address'];
		$row_array['authority_password'] = $row['authority_password'];
		$row_array['authority_stall_name'] = $row['authority_stall_name'];
		$row_array['authority_stall_type'] = $row['authority_stall_type'];
		$row_array['authority_fair_attending_frequency_per_year'] = $row['authority_fair_attending_frequency_per_year'];
		$row_array['authority_country'] = $row['authority_country'];
		$row_array['authority_city'] = $row['authority_city'];
		$row_array['authority_zipcode'] = $row['authority_zipcode'];
		$row_array['authority_mobileno'] = $row['authority_mobileno'];
		array_push($return_array, $row_array);
	}
	
	echo json_encode($return_array);
$conn->close();

?>