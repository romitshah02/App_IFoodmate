<?php

require_once "connection.php";

$incoming = file_get_contents("php://input");
//$incoming = '{"catname":"AshokCaterers"}';
$data = json_decode($incoming,TRUE);

foreach ($data as $item)
{
    $catname = $item['catname'];
}



$qr = $conn->query("SELECT SERVICE_PROVIDER_ID FROM `mst_provider` WHERE SERVICE_PROVIDER_NAME = '$catname'");
$row = $qr->fetch_row();

$id = $row[0];


$qr->close();


$result = $conn->prepare("SELECT ITEM_NAME,ITEM_PRICE,IMG FROM `menu_table` JOIN `mst_provider` ON `menu_table`.`SERVICE_PROVIDER_ID` = `mst_provider`.`SERVICE_PROVIDER_ID` WHERE `mst_provider`.`SERVICE_PROVIDER_ID` = '$id' ;");
$result->execute();
$result->bind_result($name,$price,$img);




$users = array();



while ($result->fetch())
{
    $temp = array();
    
    $temp['name'] = $name;
    $temp['price'] = $price;
    $temp['img'] = $img;
    array_push($users,$temp);
}
echo json_encode($users);

$conn->close();

?>