<?php

require_once "connection.php";


//$username = $_POST['USERNAME'];
//$parameter = '*';



$value = $conn->prepare("SELECT * FROM `mst_user`");

$res1 = 0;
$res2 = 0;
$value->execute();

while ($value->fetch())
{
        $res1 = $res1 + 1;
}


$value2 = $conn->prepare("SELECT * FROM `mst_provider`");
$value2->execute();

while ($value2->fetch())
{
        $res2 = $res2 + 1;
}

echo $res1;
echo $res2;


$conn->close();

?>

