<?php

require_once "connection.php";

$incoming = file_get_contents("php://input");
//$incoming = '{"uid":"6","pwd":"1234","new_pwd":"hello123"}';
$data = json_decode($incoming,TRUE);
$id = ($data['uid']);
$password = ($data['pwd']);
$new_password = ($data['new_pwd']);


if ($id == 001)
{
    $temp = "failure";
}
else
{
    $sql = " SELECT * FROM `mst_user` WHERE `USER_ID` = '$id' AND `USER_PASSWORD` = '$password' ";
    $result = $conn->query($sql);
    
    
    if (!empty($result) && $result->num_rows > 0)
    {
        $sql2 = " UPDATE `mst_user` SET `USER_PASSWORD` = '$new_password' WHERE `USER_ID` = '$id' AND `USER_PASSWORD` = '$password' ";
        $result2 = $conn->query($sql2);

        if (!empty($result2))
        {
            $temp =  "success";
        }

    }
    else
    {
        $temp =  "failure";
    }

    echo json_encode($temp);
    
}



$conn->close();

?>