<?php
require "conn.php";
$user_id = $_POST["uid"];
$mysql_qry = "select * from fair_info_table where host_id_no = '".$user_id."'";
$result = mysqli_query($conn,$mysql_qry);
$return_array = array();
if(mysqli_num_rows($result) > 0) {		
	while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC))
	{
		$row_array['fair_id'] = $row['fair_id'];
		$row_array['fair_name'] = $row['fair_name'];
		$row_array['fair_category'] = $row['fair_category'];
		$row_array['fair_city'] = $row['fair_city'];
		$row_array['fair_area'] = $row['fair_area'];
		$row_array['fair_starting_date'] = $row['fair_starting_date'];
		$row_array['fair_ending_date'] = $row['fair_ending_date'];
		$row_array['fair_daily_starting_time'] = $row['fair_daily_starting_time'];
		$row_array['fair_daily_ending_time'] = $row['fair_daily_ending_time'];
		$row_array['fair_number_of_stalls'] = $row['fair_number_of_stalls'];
		$row_array['fair_sponsor'] = $row['fair_sponsor'];
		$row_array['fair_contact_no'] = $row['fair_contact_no'];
		$row_array['fair_image_name'] = $row['fair_image_name'];
		array_push($return_array, $row_array);
	}
}
else
{
		$row_array['fair_name'] = '-1';
		array_push($return_array, $row_array);
}
	
	echo json_encode($return_array);
$conn->close();

?>