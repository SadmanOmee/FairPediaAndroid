<?php
require "conn.php";
$product_nm = $_POST["pn"];
$product_img = $_POST["pimg"];
$product_imgnm = $_POST["pimgnm"];
$product_cat = $_POST["pcat"];
$product_pr = $_POST["pr"];
$product_cf = $_POST["cf"];
$product_fl = $_POST["fl"];
$product_sn = $_POST["sn"];
$product_sm = $_POST["sm"];
$product_am = $_POST["am"];
$product_dm = $_POST["dm"];
$product_re = $_POST["re"];

//$product_nm = 't';
//$product_img = 't';
//$product_imgnm = 't';
//$product_cat = 't';
//$product_pr = 't';
//$product_cf = 't';
//$product_fl = 't';
//$product_sn = 't';
//$product_sm = 't';
//$product_am = 't';
//$product_dm = 't';
//$product_re = 't';
$mysql_qry = "insert into product_information_table(product_name, product_image_name, product_category, product_price, current_fair,fair_location, stall_no, stall_name, product_amount, product_demand, product_review) values('".$product_nm."','".$product_imgnm."','".$product_cat."','".$product_pr."','".$product_cf."','".$product_fl."','".$product_sn."','".$product_sm."','".$product_am."','".$product_dm."','".$product_re."')";
$upload_path = "uploads/".$product_imgnm.".jpg";
if(mysqli_query($conn,$mysql_qry)) {
	file_put_contents($upload_path,base64_decode($product_img));
	echo json_encode(array('response'=>'Product uploaded successfully'));
}
else {
	echo json_encode(array('response'=>'Product upload failed'));
}
$conn->close();
?>