<?php

require_once "connection.php";

$incoming = file_get_contents("php://input");

$data = json_decode($incoming,TRUE);
$id = ($data['uid']);
$name = ($data['name']);
$add = ($data["add"]);
$pno = ($data['pno']);
$mail = ($data['mail']);
$gst = ($data['gst']);

if ($id == 001)
{
    echo "failure";
}
else
{

    $sql = "SELECT  * FROM `mst_provider` WHERE `SERVICE_PROVIDER_ID` = '$id' ";
   
    $result = $conn->query($sql);
    
    if (!empty($result) && $result->num_rows > 0)
{
    $sql2 = "UPDATE `mst_provider` SET `SERVICE_PROVIDER_NAME`='$name',`EMAIL`='$mail',`PHONE_NO`='$pno',`ADDRESS`='$add',`GST_NO`='$gst' WHERE `SERVICE_PROVIDER_ID` = '$id' ";
    $result2 = $conn->query($sql2);

    if (!empty($result2))
    {
        $sql3 = "SELECT  `SERVICE_PROVIDER_NAME`, `EMAIL`, `PHONE_NO`, `ADDRESS`,`GST_NO` FROM `mst_provider` WHERE `SERVICE_PROVIDER_ID` = '$id' ";
        $result3 = $conn->query($sql3);
        if ($result3->num_rows > 0)
        {
        $row = $result3->fetch_assoc();
        $temp['name'] = $row['SERVICE_PROVIDER_NAME'];
        $temp['add'] = $row['ADDRESS'];
        $temp['pno'] = $row['PHONE_NO'];
        $temp['mail'] = $row['EMAIL'];
        $temp['gst'] = $row['GST_NO'];
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