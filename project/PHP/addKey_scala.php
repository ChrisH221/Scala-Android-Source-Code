<?php
$servername = "127.0.0.1";
$username = "root";
$password = "Mischief111";
$dbname = "scala_android";



// Create connection
$connection = new mysqli($servername, $username, $password, $dbname);
// Check connection
$username = $_POST["username"];
$key= $_POST["key"];
$fileName= $_POST["fileName"];
$imageEncode= $_POST["imageEncode"];

$k=mysql_real_escape_string($key);


$sql = "INSERT INTO key_code (`username`, `keycode`, `fileName`,`imageEncode` )VALUES ('$username','$k','$fileName','$imageEncode')";

$result = mysqli_query($connection, $sql) or die("Error in Selecting " . mysqli_error($connection));

$emparray = array();
    while($row =mysqli_fetch_assoc($result))
    {
        $emparray[] = $row;
    }
	
echo json_encode($emparray);	
	
mysqli_close($connection);	

?> 



