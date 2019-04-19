<?php
require "conn.php";
$product_nm = $_POST["pn"];

$mysql_qry = "delete from product_information_table where product_name = '".$product_nm."'";
$resultu = mysqli_query($conn,$mysql_qry);
if(mysqli_num_rows($resultu) > 0) {
	echo "Product deleted successfully";
}
else {
	echo "Product deletion successful";
}
$conn->close();
?>