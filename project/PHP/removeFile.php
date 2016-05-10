<?php
$servername = "127.0.0.1";
$username = "root";
$password = "Mischief111";
$dbname = "scala_android";


$username = $_POST["username"];
$fileName = $_POST["fileName"];
$id = $_POST["id"];

// Create connection
$connection = new mysqli($servername, $username, $password, $dbname);

			
		$sql = "DELETE FROM key_code WHERE `username` = '$username' and `fileName` = '$fileName' and `id` = '$id'";
		$result = mysqli_query($connection, $sql) or die("Error in Selecting " . mysqli_error($connection));
		

mysqli_close($connection);	

?> 
