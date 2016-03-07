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
$sql = "select * from users WHERE username = 'chris' AND password =  'mischief'";

//$sql = "select * from key_code WHERE username = ".$username." AND ".$username."";/
$result = mysqli_query($connection, $sql) or die("Error in Selecting " . mysqli_error($connection));

$emparray = array();
    while($row =mysqli_fetch_assoc($result))
    {
        $emparray[] = $row;
    }
	
echo json_encode($emparray);	
mysqli_close($connection);	

?> 