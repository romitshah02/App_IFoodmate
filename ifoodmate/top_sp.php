<?php

require_once "connection.php";




$result = $conn->prepare("SELECT `SERVICE_PROVIDER_NAME`,`SP_IMG` FROM `mst_provider` ");
$result->execute();
$result->bind_result($name,$img);

$users = array();

while ($result->fetch())
{
    $temp = array();
    
    $temp['name'] = $name;
    $temp['img'] = $img;
    array_push($users,$temp);
}
echo json_encode($users);

$conn->close();

?>