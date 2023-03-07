<?php

require_once "connection.php";

$incoming = file_get_contents("php://input");

$data = json_decode($incoming,TRUE);
$name = ($data['catname']);

$result = $conn->prepare("SELECT `SERVICE_PROVIDER_NAME`,`SP_IMG` FROM `mst_provider` JOIN `cat_sp` ON `mst_provider`.`SERVICE_PROVIDER_ID` = `cat_sp`.`SP_ID` JOIN `mst_category` ON `cat_sp`.`CAT_ID` = `mst_category`.`CATEGORY_ID` WHERE `mst_category`.`CATEGORY_NAME` = '$name' ");
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