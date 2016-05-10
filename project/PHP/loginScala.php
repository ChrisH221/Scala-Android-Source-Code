<?php
$servername = "127.0.0.1";
$username = "root";
$password = "";
$dbname = "scala_android";



// Create connection
$connection = new mysqli($servername, $username, $password, $dbname);
// Check connection
$username = $_POST["username"];
$password = $_POST["password"];
$sql = "select * from users WHERE username = '$username' AND password = '$password'";

$result = mysqli_query($connection, $sql) or die("Error in Selecting " . mysqli_error($connection));
$result=mysql_query($query);
$num=mysql_numrows($result); 

if($num == 1){
	
	return "true";
	
}
else{
	
	return "false";
	
}

mysqli_close($connection);	

?> 

