<?php

require_once "connection.php";
require_once "validate.php";

$username = validate(($_POST['USERNAME']));
$email = validate(($_POST['EMAIL']));
$phn = validate($_POST['PHONE_NO']);
$add = validate($_POST['add']);
$password = validate($_POST['us_pwd']);
$gender = validate($_POST['gen']);
$date = date("Y-m-d");



$sql = "INSERT INTO `mst_user`(`USERNAME`, `EMAIL`, `PHONE_NO`, `ADDRESS`, `GENDER`, `USER_PASSWORD`,`REGISTRATION_DATE`) VALUES ('$username','$email','$phn','$add','$gender','$password','$date')";

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

