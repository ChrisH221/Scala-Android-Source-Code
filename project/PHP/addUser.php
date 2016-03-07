<?php
$servername = "127.0.0.1";
$username = "root";
$password = "";
$dbname = "scala_android";


$username = $_POST["username"];
$password = $_POST["password"];

// Create connection
$connection = new mysqli($servername, $username, $password, $dbname);

				
		$sql2 = " INSERT INTO users (`username`,`password`)VALUES ('$username','password') ";
		$result2 = mysqli_query($connection, $sql2) or die("Error in Selecting " . mysqli_error($connection));
		

mysqli_close($connection);	

?> 