<?php

require_once "connection.php";

$incoming = file_get_contents("php://input");
//$incoming = '{"uid":"2"}';
$data = json_decode($incoming,TRUE);
$id = ($data['uid']);

//echo $id;
if ($id == 001)
{
    echo "failure";
}
else
{
    $sql = "SELECT  `USERNAME`, `EMAIL`, `PHONE_NO`, `ADDRESS` FROM `mst_user` WHERE `USER_ID` = '$id' ";
    $result = $conn->query($sql);
    
    
    if ($result->num_rows > 0)
    {
        $row = $result->fetch_assoc();
        $temp['uname'] = $row['USERNAME'];
        $temp['add'] = $row['ADDRESS'];
        $temp['pno'] = $row['PHONE_NO'];
        $temp['mail'] = $row['EMAIL'];
    }
    else
    {
        $temp = "No Data";
    }
    
    echo json_encode($temp);
}



$conn->close();

?>