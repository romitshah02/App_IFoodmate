<?php

require_once "connection.php";


$result = $conn->prepare("SELECT USER_ID,USERNAME,PHONE_NO FROM `mst_user`");
$result->execute();
$uno = 0;
$result->bind_result($uno,$uname,$phn);

$users = array();

while ($result->fetch())
{
    $temp = array();
    
    $temp['uno'] = $uno;
    $temp['uname'] = $uname;
    $temp['pno'] = $phn;

    array_push($users,$temp);
}
echo json_encode($users);

$conn->close();

?>