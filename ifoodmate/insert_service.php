<?php

require_once "connection.php";
require_once "validate.php";


$username = validate($_POST['USERNAME']);
$email = validate($_POST['EMAIL']);
$phn = validate($_POST['PHONE_NO']);
$add = validate($_POST['add']);
$password = validate($_POST['us_pwd']);
$gst = validate($_POST['gst']);
$date = date("Y-m-d");


$sql = "INSERT INTO `mst_provider`(`SERVICE_PROVIDER_NAME`, `EMAIL`, `PHONE_NO`, `ADDRESS`, `GST_NO`, `SERVICE_PROVIDER_PASSWORD`,`REGISTERATION_DATE` ) VALUES ('$username','$email','$phn','$add','$gst','$password','$date')";

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

