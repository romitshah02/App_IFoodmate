<?php
require_once "connection.php";
require_once "validate.php";


//$username = json_decode($_POST['USERNAME']);
//$password = json_decode($_POST['us_pwd']);
$username = 'Alok15';
$password = 'alok15';

$sql = " SELECT `USER_ID` FROM `mst_user` WHERE `USERNAME` = '$username' AND `USER_PASSWORD` = '$password'  ";
$sql2 = "SELECT `SERVICE_PROVIDER_ID` FROM `mst_provider` WHERE `SERVICE_PROVIDER_NAME` = '$username' AND `SERVICE_PROVIDER_PASSWORD` = '$password' ";
$result = $conn->query($sql);
$return = array();

if($result->num_rows > 0)
{

    $row = $result->fetch_assoc();
    $temp['uid'] = $row['USER_ID'];
    $temp['value'] = "success";    
}
else if ($result = $conn->query($sql2))
{
    if ($result->num_rows > 0)
    {
        $row = $result->fetch_assoc();
        $temp['uid'] = $row['SERVICE_PROVIDER_ID'];
        $temp['value'] = "sp_success";
    }
    else
    {
        $temp['value'] = "failure";
    }
}


echo json_encode($temp);


$conn->close();

?>
