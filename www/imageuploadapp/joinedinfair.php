<?php
require "conn.php";
$fair_fid = $_POST["huid"];
$fair_fname = $_POST["fnme"];
$fair_auid = $_POST["uid"];


$mysql_qry = "insert into fair_authority_index_table(fair_id, fair_name, authority_id, fair_stall_no) values('".$fair_fid."','".$fair_fname."','".$fair_auid."','".$fair_auid."')";
if(mysqli_query($conn,$mysql_qry)) {
	echo json_encode(array('response'=>'Booked stall successfully'));
}
else {
	echo json_encode(array('response'=>'Stall book failed'));
}
$conn->close();
?>