<?php
require "conn.php";
$searching_key = $_POST["key"];
$mysql_qry = "select * from product_information_table where product_id = '".$searching_key."' or product_name = '".$searching_key."' or product_image_name = '".$searching_key."' or product_category = '".$searching_key."' or product_price = '".$searching_key."' or current_fair = '".$searching_key."' or fair_location = '".$searching_key."' or stall_no = '".$searching_key."' or stall_name = '".$searching_key."' or product_amount = '".$searching_key."' or product_demand = '".$searching_key."' or product_review = '".$searching_key."'";
$result = mysqli_query($conn,$mysql_qry);
$return_array = array();
if(mysqli_num_rows($result) > 0) {
	while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC))
	{
		$row_array['product_id'] = $row['product_id'];
		$row_array['product_name'] = $row['product_name'];
		$row_array['product_image_name'] = $row['product_image_name'];
		$row_array['product_category'] = $row['product_category'];
		$row_array['product_price'] = $row['product_price'];
		$row_array['current_fair'] = $row['current_fair'];
		$row_array['fair_location'] = $row['fair_location'];
		$row_array['stall_no'] = $row['stall_no'];
		$row_array['stall_name'] = $row['stall_name'];
		$row_array['product_amount'] = $row['product_amount'];
		$row_array['product_demand'] = $row['product_demand'];
		$row_array['product_review'] = $row['product_review'];
		array_push($return_array, $row_array);
	}
	
}	
else
{
		$row_array['product_id'] = '-1';
		$row_array['product_name'] = '-1';
		$row_array['product_image_name'] = '-1';
		$row_array['product_category'] = '-1';
		$row_array['product_price'] = '-1';
		$row_array['current_fair'] = '-1';
		$row_array['fair_location'] = '-1';
		$row_array['stall_no'] = '-1';
		$row_array['stall_name'] = '-1';
		$row_array['product_amount'] = '-1';
		$row_array['product_demand'] = '-1';
		$row_array['product_review'] = '-1';
		array_push($return_array, $row_array);
}

echo json_encode($return_array);

$conn->close();
?>