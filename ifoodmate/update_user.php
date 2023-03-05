<?php

require_once "connection.php";

$incoming = file_get_contents("php://input");

$data = json_decode($incoming,TRUE);
$id = ($data['uid']);
$name = ($data['username']);
$add = ($data["add"]);
$pno = ($data['pno']);
$mail = ($data['mail']);

if ($id == 001)
{
    echo "failure";
}
else
{

    $sql = "SELECT  * FROM `mst_user` WHERE `USER_ID` = '$id' ";
   
    $result = $conn->query($sql);
    
    if (!empty($result) && $result->num_rows > 0)
{
    $sql2 = "UPDATE `mst_user` SET `USERNAME`='$name',`EMAIL`='$mail',`PHONE_NO`='$pno',`ADDRESS`='$add' WHERE `USER_ID` = '$id' ";
    $result2 = $conn->query($sql2);

    if (!empty($result2))
    {
        $sql3 = "SELECT  `USERNAME`, `EMAIL`, `PHONE_NO`, `ADDRESS` FROM `mst_user` WHERE `USER_ID` = '$id' ";
        $result3 = $conn->query($sql3);
        if ($result3->num_rows > 0)
        {
        $row = $result3->fetch_assoc();
        $temp['uname'] = $row['USERNAME'];
        $temp['add'] = $row['ADDRESS'];
        $temp['pno'] = $row['PHONE_NO'];
        $temp['mail'] = $row['EMAIL'];
        }
        else
        {
            echo "Failed to update";
        }
    }

    else
    {
        $temp = "Failed to update";
    }

}
    
    echo json_encode($temp);
}



$conn->close();

?>