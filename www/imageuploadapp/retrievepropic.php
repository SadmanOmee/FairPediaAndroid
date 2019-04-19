<?php
require "conn.php";
$user_id = $_POST["uidno"];
$mysql_qry = "select * from user_profile_picture_table where user_id_no = ".$user_id;
$result = mysqli_query($conn,$mysql_qry);
$return_array = array();
if(mysqli_num_rows($result) > 0) {
	while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC))
	{
		$row_array['user_id_no'] = $row['user_id_no'];
		$row_array['profile_pic_name'] = $row['profile_pic_name'];
		array_push($return_array, $row_array);
	}
	
}	
else
{
	$row_array['user_id_no'] = '-1';
		$row_array['profile_pic_name'] = 'no pro pic';
		array_push($return_array, $row_array);
}

echo json_encode($return_array);

$conn->close();
?>