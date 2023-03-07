<?php

require_once "connection.php";


$id = ($data['uid']);
$password = ($data['pwd']);
$new_password = ($data['new_pwd']);


if ($id == 001)
{
 echo "failure";
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
            echo "success";
        }

    }
    else
    {
        echo  "failure";
    }


    
}



$conn->close();

?>