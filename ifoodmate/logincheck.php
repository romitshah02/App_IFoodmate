<?php
require_once "connection.php";
require_once "validate.php";


$username = validate($_POST['USERNAME']);
$password = validate($_POST['us_pwd']);


$sql = " SELECT * FROM `mst_user` WHERE `USERNAME` = '$username' AND `USER_PASSWORD` = '$password'  ";
$sql2 = "SELECT * FROM `mst_provider` WHERE `SERVICE_PROVIDER_NAME` = '$username' AND `SERVICE_PROVIDER_PASSWORD` = '$password' ";
$result = $conn->query($sql);


if($result->num_rows > 0)
{
    echo "success";
}
else if ($result = $conn->query($sql2))
{
    if ($result->num_rows > 0)
    {
        echo "sp_success";
    }
    else
    {
        echo "failure";
    }
}



$conn->close();

?>

