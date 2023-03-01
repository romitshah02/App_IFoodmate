<?php

require_once "connection.php";


$username = trim($_POST['USERNAME']);
$email = trim($_POST['EMAIL']);
$phn = trim($_POST['PHONE_NO']);
$add = trim($_POST['add']);
$password = trim($_POST['us_pwd']);
$gst = trim($_POST['gst']);



$sql = "INSERT INTO `mst_provider`(`SERVICE_PROVIDER_NAME`, `EMAIL`, `PHONE_NO`, `ADDRESS`, `GST_NO`, `SERVICE_PROVIDER_PASSWORD`) VALUES ('$username','$email','$phn','$add','$gst','$password')";

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