<?php
require "conn.php";
$user_email = $_POST["email"];
$user_pass = $_POST["pass"];
$mysql_qry = "select * from normal_user_info_table where user_email_address = '".$user_email."' and user_password = '".$user_pass."'";
$result = mysqli_query($conn,$mysql_qry);
$return_array = array();
		
	while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC))
	{
		$row_array['user_id'] = $row['user_id'];
		$row_array['user_first_name'] = $row['user_first_name'];
		$row_array['user_last_name'] = $row['user_last_name'];
		$row_array['user_email_address'] = $row['user_email_address'];
		$row_array['user_password'] = $row['user_password'];
		$row_array['user_gender'] = $row['user_gender'];
		$row_array['user_birthdate'] = $row['user_birthdate'];
		$row_array['user_profession'] = $row['user_profession'];
		$row_array['user_country'] = $row['user_country'];
		$row_array['user_city'] = $row['user_city'];
		$row_array['user_zipcode'] = $row['user_zipcode'];
		$row_array['user_mobileno'] = $row['user_mobileno'];
		array_push($return_array, $row_array);
	}
	echo json_encode($return_array);
$conn->close();
?>