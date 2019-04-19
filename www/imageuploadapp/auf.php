<?php
require "conn.php";
$fr_name = $_POST["uid"];
$mysql_qry = "select * from fair_authority_index_table where authority_id = '".$fr_name."'";
$result = mysqli_query($conn,$mysql_qry);
$return_array = array();
if(mysqli_num_rows($result) > 0) {		
	while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC))
	{
		$row_array['fair_name'] = $row['fair_name'];
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