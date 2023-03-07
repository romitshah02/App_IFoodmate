<?php

require_once "connection.php";

//$incoming = file_get_contents("php://input");

//$data = json_decode($incoming,TRUE);
$id = $_POST['uid'];
$password = $_POST['pwd'];
$new_password = $_POST['new_pwd'];


if ($id == 001)
{
    echo "failure";
}
else
{
    $sql = "SELECT * FROM `mst_provider` WHERE `SERVICE_PROVIDER_ID` = '$id' AND `SERVICE_PROVIDER_PASSWORD` = '$password' ";
    $result = $conn->query($sql);
    
    
    if (!empty($result) && $result->num_rows > 0)
    {
        $sql2 = " UPDATE `mst_provider` SET `SERVICE_PROVIDER_PASSWORD` = '$new_password' WHERE `SERVICE_PROVIDER_ID` = '$id' AND `SERVICE_PROVIDER_PASSWORD` = '$password' ";
        $result2 = $conn->query($sql2);

        if (!empty($result2))
        {
            echo  "success";
        }

    }
    else
    {
        echo "failure";
    }
    
}



$conn->close();

?>