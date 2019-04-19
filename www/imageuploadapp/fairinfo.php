<?php
require "conn.php";
$fair_hostid = $_POST["uid"];
$fair_nm = $_POST["fnm"];
$fair_img = $_POST["fimg"];
$fair_imgnm = $_POST["fimgnm"];
$fair_cat = $_POST["fcat"];
$fair_ct = $_POST["fct"];
$fair_ar = $_POST["far"];
$fair_sd = $_POST["fsd"];
$fair_ed = $_POST["fed"];
$fair_dst = $_POST["fdst"];
$fair_det = $_POST["fdet"];
$fair_nos = $_POST["fnos"];
$fair_sp = $_POST["fsp"];
$fair_cnt = $_POST["fcnt"];


$mysql_qry = "insert into fair_info_table(host_id_no, fair_name, fair_category, fair_city, fair_area, fair_starting_date, fair_ending_date, fair_daily_starting_time, fair_daily_ending_time, fair_number_of_stalls, fair_sponsor, fair_contact_no, fair_image_name) values('".$fair_hostid."','".$fair_nm."','".$fair_cat."','".$fair_ct."','".$fair_ar."','".$fair_sd."','".$fair_ed."','".$fair_dst."','".$fair_det."','".$fair_nos."','".$fair_sp."','".$fair_cnt."','".$fair_imgnm."')";
$upload_path = "uploads/".$fair_imgnm.".jpg";
if(mysqli_query($conn,$mysql_qry)) {
	file_put_contents($upload_path,base64_decode($fair_img));
	echo json_encode(array('response'=>'Event uploaded successfully'));
}
else {
	echo json_encode(array('response'=>'Event upload failed'));
}
$conn->close();
?>