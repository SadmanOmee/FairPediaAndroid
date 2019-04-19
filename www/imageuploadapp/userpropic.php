<?php
require "conn.php";
$user_id = $_POST["uidno"];
$user_image = $_POST["uimg"];
$user_image_name = $_POST["uimgnm"];
$mysql_qry = "insert into user_profile_picture_table(user_id_no,profile_pic_name) values('$user_id','$user_image_name');";
$upload_path = "uploads/".$user_image_name.".jpg";

if(mysqli_query($conn,$mysql_qry)) {
	file_put_contents($upload_path,base64_decode($user_image));
	echo json_encode(array('response'=>'Profile Picture Changed'));
}
else {
	echo json_encode(array('response'=>'Image upload failed'));
}
$conn->close();
?>