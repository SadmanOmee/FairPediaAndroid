<?php
require "conn.php";
$user_name = $_POST["na"];
$mysql_qry = "create table '".$user_name."'(
		cidn int
);"
$conn->close();
?>