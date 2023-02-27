<?php

require_once "connection.php";


$username = trim($_POST['USERNAME']);
$email = trim($_POST['EMAIL']);
$phn = trim($_POST['PHONE_NO']);
$add = trim($_POST['add']);
$password = trim($_POST['us_pwd']);
$gender = trim($_POST['gen']);
//$uid = "001";



$sql = "INSERT INTO `user_mst`(`USERNAME`, `EMAIL`, `PHONE_NO`, `ADDRESS`, `GENDER`, `USER_PASSWORD`) VALUES ('$username','$email','$phn','$add','$gender','$password')";

if ($conn->query($sql))
{
    echo "Registered";
}
else
{
    echo "Error registering " . $sql . "" . mysqli_error($conn);
}

$conn->close();

?>

