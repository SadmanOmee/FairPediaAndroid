<?php
require "conn.php";
$user_id = $_POST["uidno"];
$mysql_qry = "select * from authority_profile_picture_table where authority_id_no = ".$user_id;
$result = mysqli_query($conn,$mysql_qry);
$return_array = array();
if(mysqli_num_rows($result) > 0) {
	while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC))
	{
		$row_array['authority_id_no'] = $row['authority_id_no'];
		$row_array['authority_profile_pic_name'] = $row['authority_profile_pic_name'];
		array_push($return_array, $row_array);
	}
	
}	
else
{
	$row_array['authority_id_no'] = '-1';
		$row_array['authority_profile_pic_name'] = 'no pro pic';
		array_push($return_array, $row_array);
}

echo json_encode($return_array);

$conn->close();
?>