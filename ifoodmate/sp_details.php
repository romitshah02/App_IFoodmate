<?php

require_once "connection.php";

$incoming = file_get_contents("php://input");
//$incoming = '{"uid":"001"}';
$data = json_decode($incoming,TRUE);
$id = ($data['uid']);

//echo $id;
if ($id == 001)
{
    echo "failure";
}
else
{
    $sql = "SELECT  `SERVICE_PROVIDER_NAME`,`ADDRESS`,`PHONE_NO`,`EMAIL`,`GST_NO` FROM `mst_provider` WHERE `SERVICE_PROVIDER_ID` = '$id' ";
    $result = $conn->query($sql);
    
    
    if ($result->num_rows > 0)
    {
        $row = $result->fetch_assoc();
        $temp['name'] = $row['SERVICE_PROVIDER_NAME'];
        $temp['add'] = $row['ADDRESS'];
        $temp['pno'] = $row['PHONE_NO'];
        $temp['mail'] = $row['EMAIL'];
        $temp['gst'] = $row['GST_NO'];
    }
    else
    {
        $temp = "No Data";
    }
    
    echo json_encode($temp);
}



$conn->close();

?>