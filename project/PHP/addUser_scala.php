<?php
$servername = "127.0.0.1";
$username = "root";
$password = "Mischief111";
$dbname = "scala_android";

 
// Create connection
$connection = new mysqli($servername, $username, $password, $dbname);

  $username = $_POST["username"];
  $password = $_POST["password"];
   $sql1 = "SELECT COUNT(*) AS C FROM users WHERE username='$username'";	
  $result = mysqli_query($connection, $sql1)or die("Error in Selecting " . mysqli_error($connection));
  $row = $result->fetch_assoc();
 
  if ($row["C"]  > 0) {
	
	echo (string)"1"; 

                    	            } 
   else
  {
     $sql2 = "INSERT INTO users (`username`,`password`)VALUES ('$username','$password')";
    
     $result2 = mysqli_query($connection, $sql2) or die("Error in Selecting " . mysqli_error($connection));
	echo (string)"0";
	
    }

	
	mysqli_close($connection);

?> 
